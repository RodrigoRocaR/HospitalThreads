package HospitalUtil;

import InterfazGrafica.CamposVentana;
import java.awt.Color;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Hospital {

    private JTextField[] textVacPac, textVacSan, textObs;
    private JTextField textPacRecep, textAuxRecep, textAuxDosis, textDosis;
    private JTextArea textColaRecep, textSalEsp, textSalDesc;

    private ListaThreads salaVacunacionPac, salaVacunacionSan, salaDescanso,
            salaObservacionPac, salaObservacionSan;

    private Lock lockTextColaRec = new ReentrantLock();
    private Lock lockTextSalaEsp = new ReentrantLock();
    private Lock lockTextDosis = new ReentrantLock();
    private Lock lockReaccionesVac = new ReentrantLock();
    private Lock lockPuestosCerrados = new ReentrantLock();

    private Condition condPuestosCerrados = lockPuestosCerrados.newCondition();

    private AtomicInteger puestosVacunacion = new AtomicInteger(10);
    private AtomicInteger puestosObservacion = new AtomicInteger(20);
    private boolean[] reaccionesVac = new boolean[20];
    private boolean[] puestoCerrado = new boolean[10];
    private AtomicBoolean pacientePuedePasar = new AtomicBoolean();
    private AtomicBoolean sanitariosListos = new AtomicBoolean();

    private Semaphore dosisPreparadas = new Semaphore(0, true);
    private Semaphore semColaRecep = new Semaphore(0, true);
    private Semaphore semSalaEspera = new Semaphore(0, true);
    private Semaphore semIniVac[] = new Semaphore[10];
    private Semaphore semFinVac[] = new Semaphore[10];
    private Semaphore semReaccion[] = new Semaphore[20];

    private BlockingQueue colaRecep = new LinkedBlockingQueue();

    private Log log = new Log();

    // Constructor:
    public Hospital(CamposVentana cv) {
        textVacPac = cv.getTextVacPac();
        textVacSan = cv.getTextVacSan();
        textObs = cv.getTextObs();

        textPacRecep = cv.getPacRecep();
        textAuxRecep = cv.getAuxRecep();
        textAuxDosis = cv.getAuxDosis();
        textDosis = cv.getDosis();

        textColaRecep = cv.getColaRecep();
        textSalEsp = cv.getSalEsp();
        textSalDesc = cv.getSalDesc();

        // Creamos las listas de hilos con los textos para imprimir
        salaVacunacionPac = new ListaThreads(10, textVacPac);
        salaVacunacionSan = new ListaThreads(10, textVacSan);
        salaDescanso = new ListaThreads(textSalDesc);
        salaObservacionPac = new ListaThreads(20, textObs);
        salaObservacionSan = new ListaThreads(20);

        // Inicializamos los arrays de semáforos
        for (int i = 0; i < 20; i++) {
            semReaccion[i] = new Semaphore(0);
            if (i < 10) { // tienen capacidad 10 (así todo se hace en 1 for)
                semIniVac[i] = new Semaphore(0);
                semFinVac[i] = new Semaphore(0);
            }
        }
    }

    // FUNCIONES PACIENTES.....................................................
    /**
     * Dado un paciente, entra en la cola de recepción y espera hasta que un
     * auxiliar le atienda.Si no tiene cita, devuelve false; si la tiene, pasa a
     * la sala de espera y espera de nuevo a que haya hueco para vacunarse (le
     * avisa un auxiliar).Una vez puede proceder a vacunarse sale de la sala de
     * espera y devuelve true.
     *
     * @param p Paciente que tiene que esperar a vacunarse
     * @return true si el paciente puede continuar o false si tiene que salir
     * del hospital porque no tiene cita.
     */
    public boolean esperarVacunarse(Paciente p) {
        entrarColaRecep(p); // nos ponemos en la cola
        try {
            // Esperamos
            semColaRecep.acquire();
            if (pacientePuedePasar.get()) {
                // Podemos pasar, de momento esperamos en la sala de espera
                // (salir de la recepción se encarga el auxiliar)
                entrarSalaEsp(p);
                semSalaEspera.acquire();
                // Ya podemos ir a la sala de vacunacion
                salirSalaEsp();
                return true;
            }
            // No estamos citados y no podemos pasar
            log.escribirEvento(p.getIdPaciente() + " ha acudido sin cita");
            return false;
        } catch (InterruptedException ex) {
            System.out.println("Paciente interrumpido mientras esperaba"
                    + "en la cola de recepción");
        }
        return false;
    }

    /**
     * Hace que el paciente entre a la sala de vacunacion y espere hasta que
     * esté vacunado.Se supone que hay hueco en la sala de vacunación para el
     * paciente.
     *
     * @param p Paciente que se va a vacunar
     */
    public void pasarAVacunarse(Paciente p) {
        try {
            int miPuesto = salaVacunacionPac.meter(p); // entramos en la sala de vacunacion
            log.escribirEvento(p.getIdPaciente() + " ha pasado a la sala de vacunación");
            // Le avisamos al sanitario de que ya estamos listos
            semIniVac[miPuesto].release();
            // Esperamos a que el sanitario nos la ponga:
            semFinVac[miPuesto].acquire();
        } catch (InterruptedException ex) {
            System.out.println("Paciente interrumpido mientras le ponian"
                    + " la vacuna");
        }
        // Ya estamos vacunados

    }

    /**
     * Saca el paciente de la sala de vacunación (liberando su puesto) y lo
     * introduce en la sala de observación, donde esperará un tiempo.Si le da
     * reacción tendrá que ser tratado y si no le da, podrá irse del hospital.
     *
     * @param p Paciente que entra a la sala de observación
     */
    public void pasarASalaObservacion(Paciente p) {
        // Salimos de la sala de vacunacion:
        salaVacunacionPac.sacar(p);
        puestosVacunacion.incrementAndGet();
        // Y entramos en la de observacion:
        int miPuesto = salaObservacionPac.meter(p);
        log.escribirEvento(p.getIdPaciente() + " ha pasado a la sala de observación");
        try {
            // Esperamos...
            Thread.sleep(10000);
            if (Math.random() <= 0.05) {   // 5%
                // Me ha dado reacción la vacuna:              
                padecerReaccion(p, miPuesto);
            }
            // No me ha dado reacción o me han termiando de tratarla
            // Ya podemos irnos
        } catch (InterruptedException ex) {
            System.out.println("Paciente interrumpido mientras estaba esperando"
                    + " en la sala de observacion");
        }
    }

    /**
     * Los pacientes que sufran una reacción llegarán a este método.El paciente
     * debe esperar a que un Sanitario le atienda cuando salga del descanso.
     *
     * @param p Paciente que sufre la reacción.
     * @param miPuesto Puesto en la sala de observación en el que se encuentra
     * el paciente.
     */
    private void padecerReaccion(Paciente p, int miPuesto) {
        lockReaccionesVac.lock();
        try {
            reaccionesVac[miPuesto] = true; // indicamos que tenemos una reacción
        } finally {
            lockReaccionesVac.unlock();
        }

        salaObservacionPac.cambiarColorTexto(miPuesto, Color.MAGENTA);
        log.escribirEvento("A " + p.getIdPaciente() + " le ha dado una "
                + "reacción la vacuna");

        try {
            // Esperamos a que un sanitario nos atienda:
            semReaccion[miPuesto].acquire();
        } catch (InterruptedException ex) {
            System.out.println("Paciente interrumpido mientras esperaba a que"
                    + " un sanitario le atendiera la reacción a la vacuna");
        }

        salaObservacionPac.cambiarColorTexto(miPuesto, Color.BLACK);
    }

    /**
     * Saca al paciente de la sala de observación y libera un puesto.Aquellos
     * pacientes que no hayan padecido una reacción a la vacuna o aquellos que
     * ya la tengan tratada, llegarán a este método.
     *
     * @param p Paciente que va a irse del hospital
     */
    public void salirHospital(Paciente p) {
        salaObservacionPac.sacar(p);
        puestosObservacion.incrementAndGet();
        log.escribirEvento(p.getIdPaciente() + " ha salido del hospital");
    }

    // FUNCIONES AUXILIARES.....................................................
    // Auxiliares con rol de regsitrar
    /**
     * El auxiliar que se ubica en recepción realizará esta tarea.Atiende a
     * pacientes que esperan en la cola de recepción y comprueba sus datos.Luego
     * le indicará si puede continuar o no.Cada vez que atiende un paciente en
     * la recepción o tarda demasiado en venir más pacientes, el auxiliar
     * comprobará si quedan plazas libres para vacunarse, en cuyo caso indicará
     * al paciente que lleve más tiempo en la sala de espera que puede proceder
     * a vacunarse.Esta última acción se repite mientras haya plazas libres y
     * gente esperando.
     */
    public void pasarPacientesVacunacion() {
        try {
            if (textAuxRecep.getText().isEmpty()) {
                textAuxRecep.setText("A1");
            }
            // Atendemos al primer paciente de la cola
            // (si no hay nadie esperamos un poco)
            // (hecho así por si ya no vienen más pacientes pero queda gente
            //  en la sala de espera)
            Paciente p = (Paciente) colaRecep.poll(5, TimeUnit.SECONDS);
            if (p != null) {
                // Le pasamos a la recepción:
                salirColaRecep();
                textPacRecep.setText(p.getIdPaciente());
                // Comprobamos sus datos...
                Thread.sleep((long) ((500 * Math.random()) + 500)); // 0,5 a 1 seg
                // Miramos si puede pasar o no
                if (Math.random() <= 0.01) { // 1 % de posibilidades de que no esté citado
                    pacientePuedePasar.set(false);
                } else { // está citado
                    pacientePuedePasar.set(true);
                }
                // Dejamos al paciente que continúe a la sala de espera:
                semColaRecep.release();
                textPacRecep.setText("");
            }
            // Miramos a ver si podemos pasar a alguien a la sala de vacunacion
            while (puestosVacunacion.get() >= 1
                    && puestosObservacion.get() >= 1 && semSalaEspera.hasQueuedThreads()) {
                // Pasamos a alguien de la sala de espera
                puestosVacunacion.decrementAndGet();
                puestosObservacion.decrementAndGet();
                // Le decimos que ya puede ir
                semSalaEspera.release();
            }
        } catch (InterruptedException ex) {
            System.out.println("Auxiliar interrumpido mientras atendia pacientes");
        }
    }

    // Auxiliares con el otro rol (preparar dosis)
    /**
     * Prepara las dosis que administrarán los sanitarios.
     */
    public void prepararDosis() {
        if (textAuxDosis.getText().isEmpty()) {
            textAuxDosis.setText("A2");
        }
        try {
            // Preparando dosis...
            Thread.sleep((int) (500 * Math.random()) + 500); // 0,5 a 1 seg
            // Dosis preparada:
            dosisPreparadas.release(); // sumamos 1 o se la damos a un sanitario
            ponerTextoDosis();
        } catch (InterruptedException ex) {
            System.out.println("Auxiliar interrumpido mientras prepraraba dosis");
        }
    }

    /**
     * Hace que el auxiliar descanse durante un tiempo.Dependiendo de la tarea
     * que realice desacansará más o menos.Esto hace que salgan de su puesto y
     * vayan a la sala de descanso.Una vez terminen de descansar, saldrán de la
     * sala de descanso.
     *
     * @param a Auxiliar que va a descansar
     * @param rolRegistrar Tarea que realiza el auxilar: true si registra
     * pacientes en recepción y false si prepara dosis.
     */
    public void descansarSalaDescanso(Auxiliar a, boolean rolRegistrar) {
        salaDescanso.meter(a);
        log.escribirEvento("Auxiliar " + a.getIdAuxiliar() + " ha iniciado su descanso");
        try {
            // Dependiendo de lo que haga el auxiliar descansa más o menos
            if (rolRegistrar) {
                textAuxRecep.setText("");
                Thread.sleep((int) (2000 * Math.random()) + 3000); // 3 a 5 seg
            } else {
                textAuxDosis.setText("");
                Thread.sleep((int) (3000 * Math.random()) + 1000); // 1 a 4 seg
            }
        } catch (InterruptedException ex) {
            System.out.println("Auxiliar interrumpido mientras descansaba");
        }
        salaDescanso.sacar(a);
        log.escribirEvento("Auxiliar " + a.getIdAuxiliar() + " ha salido de su descanso");
    }

    // FUNCIONES SANITARIOS....................................................
    /**
     * Introduce al sanitario en un puesto: la primera vez es en el primero que
     * haya disponible y el resto de ocasiones se dirigirán directamente al a su
     * puesto asginado (puesto que fueron la primera vez).A continuación espera
     * la llegada de pacientes.Una vez llegan, le administrará la dosis y le
     * avisará la paciente una vez esté vacunado.
     *
     * El sanitario puede ser interrumpido para la limpieza de su puesto (se
     * cierra el puesto) en cualquier momento, para lo cual tendrá que cesar el
     * proceso de vacunación hasta la reapertura del puesto.
     *
     * @param s Sanitario que va a vacunar
     */
    public void vacunar(Sanitario s) {
        int miPuesto;
        boolean continuar;
        // Añadimos al sanitario a la sala de vacunacion:
        if (s.getPuestoAsociado() == -1) { // primera vez que entramos
            miPuesto = salaVacunacionSan.meter(s);
            s.setPuestoAsociado(miPuesto);
        } else { // no es la primera vez, vamos a nuestro puesto
            miPuesto = s.getPuestoAsociado();
            salaVacunacionSan.meter(s, miPuesto);
        }
        // Comprobamos si ya estamos listos todos los sanitarios
        if (!sanitariosListos.get() && (!sanitariosListos.get() && miPuesto == 9)) {
            sanitariosListos.set(true);
        }

        // Vacunamos a 15 pacientes:
        for (int i = 0; i < 15; i++) {
            descansarSiPuestoCerrado(s, miPuesto);
            continuar = false;
            while (!continuar) {
                try {
                    // Esperamos a que el paciente llegue y nos diga que ya esta listo:
                    semIniVac[miPuesto].acquire();
                    continuar = true;
                } catch (InterruptedException ex) {
                    // nos interrumpen por limpieza puesto
                    descansarSiPuestoCerrado(s, miPuesto);
                }
            }
            // Vacunamos
            ponerDosis(s, miPuesto);
            // Le decimos al paciente que ya está vacunado y puede continuar
            // a la sala de observacion
            semFinVac[miPuesto].release();
        }
        salaVacunacionSan.sacar(s);
    }

    /**
     * Administra una dosis al paciente.Si no hay dosis disponibles, espera a
     * que haya dosis disponibles.
     *
     * Si es interrumpido para la limpieza del puesto mientras administra la
     * dosis, cuando vuelva a abrir el puesto tendrá que volver a empezar a
     * administrarla (se usa la misma dosis que estábamos usando antes de que
     * cerraran el puesto, no se pide una nueva).
     *
     * @param s Sanitario que va administrar la dosis.
     * @param miPuesto Puesto en la sala de vacunación del sanitario que va a
     * administrar la dosis.
     */
    private void ponerDosis(Sanitario s, int miPuesto) {
        boolean vacunado = false;
        // Miramos a ver si hay dosis listas
        dosisPreparadas.acquireUninterruptibly();
        ponerTextoDosis();
        // Ya tenemos la dosis, ahora la administramos:
        while (!vacunado) {
            try {
                Thread.sleep((int) (2000 * Math.random()) + 3000); // 3 a 5 seg
                vacunado = true;
            } catch (InterruptedException ex) {
                descansarSiPuestoCerrado(s, miPuesto);
            }
        }
        // Registramos el evento:
        Paciente pacVacunado = (Paciente) salaVacunacionPac.mirar(miPuesto);
        log.escribirEvento(pacVacunado.getIdPaciente()
                + " ha sido vacuando por el sanitario "
                + s.getIdSanitario() + " en el puesto " + miPuesto);
        System.out.println(pacVacunado.getIdPaciente()
                + " ha sido vacuando por el sanitario "
                + s.getIdSanitario() + " en el puesto " + miPuesto);
    }

    /**
     * Hace que el sanitario entre a la sala de descanso, y descanse un tiempo
     * determinado.Este método será utilizado cuando un sanitario vacune a
     * cierto nº de pacientes (15).
     *
     * El procedimiento de sacar al sanitario de la sala en la que estaba se
     * debe de hacer antes de este método.
     *
     * @param s Sanitario que va a descansar.
     */
    public void descansarSalaDescanso(Sanitario s) {
        salaDescanso.meter(s);
        log.escribirEvento("El sanitario " + s.getIdSanitario()
                + " ha iniciado su descanso");
        try {
            Thread.sleep((int) (3000 * Math.random()) + 5000); // 5 a 8 seg
        } catch (InterruptedException ex) {
            System.out.println("Sanitario interrumpido mientras descansaba");
        }
        salaDescanso.sacar(s);
        log.escribirEvento("El sanitario " + s.getIdSanitario()
                + " ha salido de su descanso");
    }

    /**
     * En caso de que cierren un puesto de vacunación, el sanitario que estaba
     * en ese puesto dejará lo que estaba haciendo e irá a este método.
     *
     * El sanitario entra a la sala de descanso (y sale de la sala de
     * vacunación) y descansará un tiempo indefinido: hasta que no reabran su
     * puesto, permanecerá en la sala de descanso. Una vez lo reabran, saldrá de
     * dicha sala y volverá a la sala de vacunación (a su puesto
     * correspondiente).
     *
     * @param s Sanitario que va a descansar mientras limpian su puesto.
     * @param puesto Puesto que el sanitario ocupaba en la sala de vacunación.
     */
    public void descansarSiPuestoCerrado(Sanitario s, int puesto) {
        if (isPuestoCerrado(puesto)) {
            // Salimos de la sala de vacunación:
            salaVacunacionSan.sacar(s);
            salaDescanso.meter(s);
            log.escribirEvento("El sanitario " + s.getIdSanitario()
                    + " ha ido a descansar hasta que vuelvan a abrir su puesto");

            Thread.interrupted(); // limpiamos el flag ya que no siempre
            // entra a este método tras ser interrumpido y puede entrar con el
            // flag a 1 lo que puede dar problemas.

            // Vamos a descansar hasta que reabran nuestro puesto:
            lockPuestosCerrados.lock();
            try {
                while (puestoCerrado[puesto]) {
                    condPuestosCerrados.await();
                }
            } catch (InterruptedException ex) {
                System.out.println("Sanitario interrumpido mientras esperaba para"
                        + " que le abrieran el puesto");
            } finally {
                lockPuestosCerrados.unlock();
            }

            // Volvemos a la sala de vacunación:
            salaDescanso.sacar(s);
            salaVacunacionSan.meter(s, puesto);
            log.escribirEvento("El sanitario " + s.getIdSanitario()
                    + " ha salido de descansar tras la apertura de su puesto");
        }
    }

    /**
     * Comprueba si hay algún paciente que tenga reacción a la vacuna y no esté
     * siendo atendido ya por otro sanitario.En caso de que sea así, este método
     * devuelve el número del puesto de dicho paciente y en caso contrario -1.
     * En caso de que vaya a atender a algún paciente, el sanitario es
     * introducido en la sala de observacion.
     *
     * @param s Sanitario que quiere ver si hay pacientes con reaccion a la
     * vacuna
     * @return número de puesto del paciente con reacción y, si no hay, -1
     */
    public int hayAlguienConReaccionVac(Sanitario s) {
        lockReaccionesVac.lock();
        try {
            for (int i = 0; i < 20; i++) {
                if (reaccionesVac[i] && salaObservacionSan.mirar(i) == null) {
                    // hay alguien con reaccion y no está siendo atendido
                    // Vamos a atenderle:
                    salaObservacionSan.meter(s, i);
                    salaObservacionPac.cambiarColorTexto(i, Color.green);
                    return i;
                }
            }
        } finally {
            lockReaccionesVac.unlock();
        }
        return -1;
    }

    /**
     * En caso de que haya algún paciente con reacción a la vacuna sin atender,
     * lo trata y hace que el sanitario salga de la sala de observación.En caso
     * contrario este método no hace nada.
     *
     * @param s Sanitario que va a tratar la reacción a la vacuna de un paciente
     */
    public void tratarReaccionVacuna(Sanitario s) {
        int posPacConReaccion = hayAlguienConReaccionVac(s);
        if (posPacConReaccion != -1) {
            // Le atendemos...
            try {
                Thread.sleep((int) (3000 * Math.random()) + 2000); // 2 a 5 seg
                // Le avisamos que ya se puede ir:
                lockReaccionesVac.lock();
                reaccionesVac[posPacConReaccion] = false;
            } catch (InterruptedException ex) {
                System.out.println("Sanitario interrumpido mientras atendia a"
                        + "un paciente con reaccion a la vacuna");
            } finally {
                lockReaccionesVac.unlock();
            }
            // Ya está tratado, le avisamos de que ya puede irse
            Paciente pacConReaccion = (Paciente) salaObservacionPac.mirar(posPacConReaccion);
            log.escribirEvento(pacConReaccion.getIdPaciente() + " sufrio una "
                    + "reaccion pero ya ha sido atendido por el sanitario " + s.getIdSanitario());
            semReaccion[posPacConReaccion].release();
            // Nos vamos:
            salaObservacionSan.sacar(s);
        }
    }

    /**
     * Cierra el puesto de vacunación.En caso de que se encuentre el sanitario
     * en el puesto, le interrumpe.
     *
     * @param numPuesto Puesto de vacunación a cerrrar.
     */
    public void cerrarPuestoVacunacion(int numPuesto) {
        lockPuestosCerrados.lock();
        try {
            puestoCerrado[numPuesto] = true;
            Sanitario s = (Sanitario) salaVacunacionSan.mirar(numPuesto);
            // Le interummpimos para que deje de esperar a pacientes y se vaya:
            if (s != null) { // si es null significa que está en descanso
                s.interrupt();
            }
        } finally {
            lockPuestosCerrados.unlock();
        }
    }

    /**
     * Abre el puesto de vacunación, avisando a todos los sanitarios que
     * esuvieran esperando a que abran su puesto.Solo el sanitario cuyo puesto
     * es el que ha sido abierto volverá a su puesto.
     *
     * @param numPuesto Puesto de vacunación a abrir.
     */
    public void abrirPuestoVacunacion(int numPuesto) {
        lockPuestosCerrados.lock();
        try {
            puestoCerrado[numPuesto] = false;
            condPuestosCerrados.signalAll();
        } finally {
            lockPuestosCerrados.unlock();
        }
    }

    /**
     * Comprueba si un puesto de vacunación determinado está cerrado o no.
     *
     * @param numPuesto Puesto de vacunación a comprobar.
     * @return true si está cerrado y false si está abierto.
     */
    public boolean isPuestoCerrado(int numPuesto) {
        lockPuestosCerrados.lock();
        try {
            return puestoCerrado[numPuesto];
        } finally {
            lockPuestosCerrados.unlock();
        }
    }

    /**
     * Obtiene un array de booleanos que indica qué puestos está cerrados y
     * cuáles están abiertos.
     *
     * @return Array de booleanos que indica el estado de todos los puestos.
     * true indica que está cerrado y false que está abierto.
     */
    public boolean[] getPuestosCerrados() {
        lockPuestosCerrados.lock();
        try {
            return puestoCerrado;
        } finally {
            lockPuestosCerrados.unlock();
        }
    }

    /**
     * Indica si ya han llegado todos los sanitarios a su puesto de vacunación
     *
     * @return true si ya han llegado todos y false si no ha llegado nadie o
     * solo una parte del total de sanitarios esperados.
     */
    public boolean isSanitariosListos() {
        return sanitariosListos.get();
    }

    // Otros métodos...........................................................
    /**
     * Introduce un paciente en la cola de recepción.
     *
     * @param p Paciente que va a entrar a la cola.
     */
    private void entrarColaRecep(Paciente p) {
        colaRecep.add(p);
        lockTextColaRec.lock();
        try {
            if (textColaRecep.getText().isEmpty()) {
                textColaRecep.setText(p.getIdPaciente());
            } else {
                textColaRecep.setText(textColaRecep.getText() + ", " + p.getIdPaciente());
            }
        } finally {
            lockTextColaRec.unlock();
        }
    }

    /**
     * Hace los cambios necesarios cada vez que se saca un paciente de la cola
     * de recepción, pero este método no saca el paciente en sí de la cola.Se
     * supone que esta acción la debe hacer el auxiliar correspondiente.
     *
     * Se spuone que el paciente que va a salir es el que se encuentra primero
     * en la cola.
     */
    private void salirColaRecep() {
        // (el auxiliar ya saca el paciente de la cola)
        lockTextColaRec.lock();
        try {
            // Quitamos el primer paciente:
            if (textColaRecep.getText().length() < 8) { // solo quedaba este paciente
                textColaRecep.setText("");
            } else { // hay más de 1
                // quitamos el primero de la cola (el que acaba de salir)
                textColaRecep.setText(textColaRecep.getText().substring(7));
            }
        } finally {
            lockTextColaRec.unlock();
        }
    }

    /**
     * Introduce un paciente en la sala de espera.
     *
     * @param p Paciente que va a entrar en la sala de espera.
     */
    private void entrarSalaEsp(Paciente p) {
        lockTextSalaEsp.lock();
        try {
            if (textSalEsp.getText().isEmpty()) {
                textSalEsp.setText(p.getIdPaciente());
            } else {
                textSalEsp.setText(textSalEsp.getText() + ", " + p.getIdPaciente());
            }
        } finally {
            lockTextSalaEsp.unlock();
        }
        log.escribirEvento(p.getIdPaciente() + " ha pasado a la sala de espera");
    }

    /**
     * Saca un paciente de la sala de espera.
     *
     * @param p Paciente que va a salir de la sala de espera
     */
    private void salirSalaEsp() {
        lockTextSalaEsp.lock();
        try {
            if (textSalEsp.getText().length() < 8) {
                textSalEsp.setText("");
            } else {
                textSalEsp.setText(textSalEsp.getText().substring(7));
            }
        } finally {
            lockTextSalaEsp.unlock();
        }
    }

    /**
     * Actualiza el valor de las dosis disponibles en el JTextField
     * correspndiente.
     */
    private void ponerTextoDosis() {
        lockTextDosis.lock();
        try {
            textDosis.setText(Integer.toString(dosisPreparadas.availablePermits()));
        } finally {
            lockTextDosis.unlock();
        }
    }

}
