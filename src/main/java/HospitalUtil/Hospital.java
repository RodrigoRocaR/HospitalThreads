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

        // Inicializamos los arrays de sem??foros
        for (int i = 0; i < 20; i++) {
            semReaccion[i] = new Semaphore(0);
            if (i < 10) { // tienen capacidad 10 (as?? todo se hace en 1 for)
                semIniVac[i] = new Semaphore(0);
                semFinVac[i] = new Semaphore(0);
            }
        }
    }

    // FUNCIONES PACIENTES.....................................................
    /**
     * Dado un paciente, entra en la cola de recepci??n y espera hasta que un
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
                // (salir de la recepci??n se encarga el auxiliar)
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
                    + "en la cola de recepci??n");
        }
        return false;
    }

    /**
     * Hace que el paciente entre a la sala de vacunacion y espere hasta que
     * est?? vacunado.Se supone que hay hueco en la sala de vacunaci??n para el
     * paciente.
     *
     * @param p Paciente que se va a vacunar
     */
    public void pasarAVacunarse(Paciente p) {
        try {
            int miPuesto = salaVacunacionPac.meter(p); // entramos en la sala de vacunacion
            log.escribirEvento(p.getIdPaciente() + " ha pasado a la sala de vacunaci??n");
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
     * Saca el paciente de la sala de vacunaci??n (liberando su puesto) y lo
     * introduce en la sala de observaci??n, donde esperar?? un tiempo.Si le da
     * reacci??n tendr?? que ser tratado y si no le da, podr?? irse del hospital.
     *
     * @param p Paciente que entra a la sala de observaci??n
     */
    public void pasarASalaObservacion(Paciente p) {
        // Salimos de la sala de vacunacion:
        salaVacunacionPac.sacar(p);
        puestosVacunacion.incrementAndGet();
        // Y entramos en la de observacion:
        int miPuesto = salaObservacionPac.meter(p);
        log.escribirEvento(p.getIdPaciente() + " ha pasado a la sala de observaci??n");
        try {
            // Esperamos...
            Thread.sleep(10000);
            if (Math.random() <= 0.05) {   // 5%
                // Me ha dado reacci??n la vacuna:              
                padecerReaccion(p, miPuesto);
            }
            // No me ha dado reacci??n o me han termiando de tratarla
            // Ya podemos irnos
        } catch (InterruptedException ex) {
            System.out.println("Paciente interrumpido mientras estaba esperando"
                    + " en la sala de observacion");
        }
    }

    /**
     * Los pacientes que sufran una reacci??n llegar??n a este m??todo.El paciente
     * debe esperar a que un Sanitario le atienda cuando salga del descanso.
     *
     * @param p Paciente que sufre la reacci??n.
     * @param miPuesto Puesto en la sala de observaci??n en el que se encuentra
     * el paciente.
     */
    private void padecerReaccion(Paciente p, int miPuesto) {
        lockReaccionesVac.lock();
        try {
            reaccionesVac[miPuesto] = true; // indicamos que tenemos una reacci??n
        } finally {
            lockReaccionesVac.unlock();
        }

        salaObservacionPac.cambiarColorTexto(miPuesto, Color.MAGENTA);
        log.escribirEvento("A " + p.getIdPaciente() + " le ha dado una "
                + "reacci??n la vacuna");

        try {
            // Esperamos a que un sanitario nos atienda:
            semReaccion[miPuesto].acquire();
        } catch (InterruptedException ex) {
            System.out.println("Paciente interrumpido mientras esperaba a que"
                    + " un sanitario le atendiera la reacci??n a la vacuna");
        }

        salaObservacionPac.cambiarColorTexto(miPuesto, Color.BLACK);
    }

    /**
     * Saca al paciente de la sala de observaci??n y libera un puesto.Aquellos
     * pacientes que no hayan padecido una reacci??n a la vacuna o aquellos que
     * ya la tengan tratada, llegar??n a este m??todo.
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
     * El auxiliar que se ubica en recepci??n realizar?? esta tarea.Atiende a
     * pacientes que esperan en la cola de recepci??n y comprueba sus datos.Luego
     * le indicar?? si puede continuar o no.Cada vez que atiende un paciente en
     * la recepci??n o tarda demasiado en venir m??s pacientes, el auxiliar
     * comprobar?? si quedan plazas libres para vacunarse, en cuyo caso indicar??
     * al paciente que lleve m??s tiempo en la sala de espera que puede proceder
     * a vacunarse.Esta ??ltima acci??n se repite mientras haya plazas libres y
     * gente esperando.
     */
    public void pasarPacientesVacunacion() {
        try {
            if (textAuxRecep.getText().isEmpty()) {
                textAuxRecep.setText("A1");
            }
            // Atendemos al primer paciente de la cola
            // (si no hay nadie esperamos un poco)
            // (hecho as?? por si ya no vienen m??s pacientes pero queda gente
            //  en la sala de espera)
            Paciente p = (Paciente) colaRecep.poll(5, TimeUnit.SECONDS);
            if (p != null) {
                // Le pasamos a la recepci??n:
                salirColaRecep();
                textPacRecep.setText(p.getIdPaciente());
                // Comprobamos sus datos...
                Thread.sleep((long) ((500 * Math.random()) + 500)); // 0,5 a 1 seg
                // Miramos si puede pasar o no
                if (Math.random() <= 0.01) { // 1 % de posibilidades de que no est?? citado
                    pacientePuedePasar.set(false);
                } else { // est?? citado
                    pacientePuedePasar.set(true);
                }
                // Dejamos al paciente que contin??e a la sala de espera:
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
     * Prepara las dosis que administrar??n los sanitarios.
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
     * que realice desacansar?? m??s o menos.Esto hace que salgan de su puesto y
     * vayan a la sala de descanso.Una vez terminen de descansar, saldr??n de la
     * sala de descanso.
     *
     * @param a Auxiliar que va a descansar
     * @param rolRegistrar Tarea que realiza el auxilar: true si registra
     * pacientes en recepci??n y false si prepara dosis.
     */
    public void descansarSalaDescanso(Auxiliar a, boolean rolRegistrar) {
        salaDescanso.meter(a);
        log.escribirEvento("Auxiliar " + a.getIdAuxiliar() + " ha iniciado su descanso");
        try {
            // Dependiendo de lo que haga el auxiliar descansa m??s o menos
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
     * haya disponible y el resto de ocasiones se dirigir??n directamente al a su
     * puesto asginado (puesto que fueron la primera vez).A continuaci??n espera
     * la llegada de pacientes.Una vez llegan, le administrar?? la dosis y le
     * avisar?? la paciente una vez est?? vacunado.
     *
     * El sanitario puede ser interrumpido para la limpieza de su puesto (se
     * cierra el puesto) en cualquier momento, para lo cual tendr?? que cesar el
     * proceso de vacunaci??n hasta la reapertura del puesto.
     *
     * @param s Sanitario que va a vacunar
     */
    public void vacunar(Sanitario s) {
        int miPuesto;
        boolean continuar;
        // A??adimos al sanitario a la sala de vacunacion:
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
            // Le decimos al paciente que ya est?? vacunado y puede continuar
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
     * dosis, cuando vuelva a abrir el puesto tendr?? que volver a empezar a
     * administrarla (se usa la misma dosis que est??bamos usando antes de que
     * cerraran el puesto, no se pide una nueva).
     *
     * @param s Sanitario que va administrar la dosis.
     * @param miPuesto Puesto en la sala de vacunaci??n del sanitario que va a
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
     * determinado.Este m??todo ser?? utilizado cuando un sanitario vacune a
     * cierto n?? de pacientes (15).
     *
     * El procedimiento de sacar al sanitario de la sala en la que estaba se
     * debe de hacer antes de este m??todo.
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
     * En caso de que cierren un puesto de vacunaci??n, el sanitario que estaba
     * en ese puesto dejar?? lo que estaba haciendo e ir?? a este m??todo.
     *
     * El sanitario entra a la sala de descanso (y sale de la sala de
     * vacunaci??n) y descansar?? un tiempo indefinido: hasta que no reabran su
     * puesto, permanecer?? en la sala de descanso. Una vez lo reabran, saldr?? de
     * dicha sala y volver?? a la sala de vacunaci??n (a su puesto
     * correspondiente).
     *
     * @param s Sanitario que va a descansar mientras limpian su puesto.
     * @param puesto Puesto que el sanitario ocupaba en la sala de vacunaci??n.
     */
    public void descansarSiPuestoCerrado(Sanitario s, int puesto) {
        if (isPuestoCerrado(puesto)) {
            // Salimos de la sala de vacunaci??n:
            salaVacunacionSan.sacar(s);
            salaDescanso.meter(s);
            log.escribirEvento("El sanitario " + s.getIdSanitario()
                    + " ha ido a descansar hasta que vuelvan a abrir su puesto");

            Thread.interrupted(); // limpiamos el flag ya que no siempre
            // entra a este m??todo tras ser interrumpido y puede entrar con el
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

            // Volvemos a la sala de vacunaci??n:
            salaDescanso.sacar(s);
            salaVacunacionSan.meter(s, puesto);
            log.escribirEvento("El sanitario " + s.getIdSanitario()
                    + " ha salido de descansar tras la apertura de su puesto");
        }
    }

    /**
     * Comprueba si hay alg??n paciente que tenga reacci??n a la vacuna y no est??
     * siendo atendido ya por otro sanitario.En caso de que sea as??, este m??todo
     * devuelve el n??mero del puesto de dicho paciente y en caso contrario -1.
     * En caso de que vaya a atender a alg??n paciente, el sanitario es
     * introducido en la sala de observacion.
     *
     * @param s Sanitario que quiere ver si hay pacientes con reaccion a la
     * vacuna
     * @return n??mero de puesto del paciente con reacci??n y, si no hay, -1
     */
    public int hayAlguienConReaccionVac(Sanitario s) {
        lockReaccionesVac.lock();
        try {
            for (int i = 0; i < 20; i++) {
                if (reaccionesVac[i] && salaObservacionSan.mirar(i) == null) {
                    // hay alguien con reaccion y no est?? siendo atendido
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
     * En caso de que haya alg??n paciente con reacci??n a la vacuna sin atender,
     * lo trata y hace que el sanitario salga de la sala de observaci??n.En caso
     * contrario este m??todo no hace nada.
     *
     * @param s Sanitario que va a tratar la reacci??n a la vacuna de un paciente
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
            // Ya est?? tratado, le avisamos de que ya puede irse
            Paciente pacConReaccion = (Paciente) salaObservacionPac.mirar(posPacConReaccion);
            log.escribirEvento(pacConReaccion.getIdPaciente() + " sufrio una "
                    + "reaccion pero ya ha sido atendido por el sanitario " + s.getIdSanitario());
            semReaccion[posPacConReaccion].release();
            // Nos vamos:
            salaObservacionSan.sacar(s);
        }
    }

    /**
     * Cierra el puesto de vacunaci??n.En caso de que se encuentre el sanitario
     * en el puesto, le interrumpe.
     *
     * @param numPuesto Puesto de vacunaci??n a cerrrar.
     */
    public void cerrarPuestoVacunacion(int numPuesto) {
        lockPuestosCerrados.lock();
        try {
            puestoCerrado[numPuesto] = true;
            Sanitario s = (Sanitario) salaVacunacionSan.mirar(numPuesto);
            // Le interummpimos para que deje de esperar a pacientes y se vaya:
            if (s != null) { // si es null significa que est?? en descanso
                s.interrupt();
            }
        } finally {
            lockPuestosCerrados.unlock();
        }
    }

    /**
     * Abre el puesto de vacunaci??n, avisando a todos los sanitarios que
     * esuvieran esperando a que abran su puesto.Solo el sanitario cuyo puesto
     * es el que ha sido abierto volver?? a su puesto.
     *
     * @param numPuesto Puesto de vacunaci??n a abrir.
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
     * Comprueba si un puesto de vacunaci??n determinado est?? cerrado o no.
     *
     * @param numPuesto Puesto de vacunaci??n a comprobar.
     * @return true si est?? cerrado y false si est?? abierto.
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
     * Obtiene un array de booleanos que indica qu?? puestos est?? cerrados y
     * cu??les est??n abiertos.
     *
     * @return Array de booleanos que indica el estado de todos los puestos.
     * true indica que est?? cerrado y false que est?? abierto.
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
     * Indica si ya han llegado todos los sanitarios a su puesto de vacunaci??n
     *
     * @return true si ya han llegado todos y false si no ha llegado nadie o
     * solo una parte del total de sanitarios esperados.
     */
    public boolean isSanitariosListos() {
        return sanitariosListos.get();
    }

    // Otros m??todos...........................................................
    /**
     * Introduce un paciente en la cola de recepci??n.
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
     * de recepci??n, pero este m??todo no saca el paciente en s?? de la cola.Se
     * supone que esta acci??n la debe hacer el auxiliar correspondiente.
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
            } else { // hay m??s de 1
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
