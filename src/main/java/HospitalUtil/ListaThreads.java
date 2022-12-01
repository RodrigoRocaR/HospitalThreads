package HospitalUtil;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ListaThreads {

    private Thread[] array; // capacidad limitada
    private JTextField[] campoTexto;
    
    private ArrayList<Thread> lista; // capacidad ilimitada
    private JTextArea areaTexto;
    // )Se usa uno u otro dependiendo del constructor usado)

    private int capacidad;

    // Constructor 1: capacidad limitada y tenemos un array de JTextFields para
    // representar el estado de la lista de hilos.
    public ListaThreads(int capacidad, JTextField[] campoTexto) {
        array = new Thread[capacidad];
        this.capacidad = capacidad;
        this.campoTexto = campoTexto;
    }

    // Constructor 2: capacidad ilimitada y tenemos un JTextArea para
    // representar el estado de la lista de hilos.
    public ListaThreads(JTextArea areaTexto) {
        this.areaTexto = areaTexto;
        lista = new ArrayList<>();
        capacidad = -1;
    }

    // Constructor 3: capacidad limitada y NO tenemos un array de JTextFields para
    // representar el estado de la lista de hilos.
    public ListaThreads(int capacidad) {
        array = new Thread[capacidad];
        this.capacidad = capacidad;
    }

    /**
     * Introduce un hilo en la primera posición disponible.Avisa a todos 
     * aquellos hilos que estuvieran esperando por algún elemento y todavía no
     * estaba disponible en ese momento.
     * 
     * @param t Hilo a introducir en la lista.
     * @return La posición en la que el hilo fue introducido.
     */
    public synchronized int meter(Thread t) {
        if (capacidad != -1) {
            for (int i = 0; i < capacidad; i++) {
                if (array[i] == null) {
                    array[i] = t;
                    if (campoTexto != null) {
                        imprimir();
                    }
                    notifyAll();
                    return i;
                }
            }
        } else {
            lista.add(t);
            if (areaTexto != null) {
                imprimir();
            }
            notifyAll();
            return lista.indexOf(t);
        }
        return -1;
    }

    /**
     * Introduce un hilo en la posción especificada, sobreescribiendo el contenido
     * que hubiera antes si es que lo hubo.Avisa a todos 
     * aquellos hilos que estuvieran esperando por algún elemento y todavía no
     * estaba disponible en ese momento.
     * 
     * @param t Hilo a introducir.
     * @param pos Posición en la que queremos introducir el hilo.
     */
    public synchronized void meter(Thread t, int pos) {
        if (capacidad != -1) {
            array[pos] = t;
            notifyAll();
        } else {
            lista.set(pos, t);
        }
        // Comprobamos si podemos imprimir o no
        // (debido al tercer constructor)
        if (!((campoTexto == null && capacidad != -1)
                || (areaTexto == null && capacidad == -1))) {
            imprimir();
        }
    }

    /**
     * Saca (elimina) el hilo especificado de la lista.
     * 
     * @param t Hilo que deseamos eliminar de la lista.
     */
    public synchronized void sacar(Thread t) {
        if (capacidad != -1) {
            for (int i = 0; i < capacidad; i++) {
                if (array[i] == t) {
                    array[i] = null;
                    if (campoTexto != null) {
                        imprimir();
                    }
                }
            }
        } else {
            lista.remove(t);
            if (areaTexto != null) {
                imprimir();
            }
        }
    }

    /**
     * Obtiene el hilo que se encuentra en la posición especificada.
     * 
     * @param pos Posición que queremos comprobar.
     * @return Si hay un hilo en la posición, devuelve el hilo y si no hay nada
     * devolvemos null.
     */
    public synchronized Thread mirar(int pos) {
        if (capacidad != -1) {
            return array[pos];
        } else {
            Thread res;
            try {
                res = lista.get(pos);
            } catch (IndexOutOfBoundsException ex) {
                res = null;
            }
            return res;

        }
    }

    /**
     * Obtiene el hilo que se encuentra en la posición especificada.Si no hay
     * ningún hilo en la posición, espera hasta que haya un hilo.El método
     * finaliza cuando se encuntra un hilo en la posición o es interrumpido.
     * 
     * @param pos Posición a comprobar.
     * @return El hilo si el método finaliza correctamente o null si es
     * interrumpido.
     * @throws InterruptedException Si es interrumpido mientras espera a la
     * llegada de hilos.
     */
    public synchronized Thread mirarEsperar(int pos) throws InterruptedException {
        if (capacidad != -1) {
            while (array[pos] == null) { // esta vacío, esperamos
                wait();
            }
            return array[pos];
        } else {
            Thread res = null;
            boolean continuar = false;
            while (!continuar) {
                try {
                    res = lista.get(pos);
                    continuar = true;
                } catch (IndexOutOfBoundsException ex) { // todavía no hay nada
                    wait(); // por lo que esperamos.
                }
            }
            return res;
        }
    }
    
    /**
     * Imprime la situación actual de la lista de hilos en un array de 
     * JTextFields si se utilizó el primer constructor o en un JTextArea si se
     * utilizó el segundo. Este método no debe ser llamado en caso de que se 
     * haya utilizado el tercer constructor ya que lanzará un NullPointerException
     * al no haber ninguna de las 2 alternativas anteriores (Array JTextField/
     * JTextArea).
     */
    public synchronized void imprimir() {
        Paciente p;
        Sanitario s;
        Auxiliar a;
        if (capacidad != -1) { // array JTextFields
            for (int i = 0; i < capacidad; i++) {
                if (array[i] != null) {
                    if (array[i] instanceof Paciente) { // comprobamos si es paciente
                        p = (Paciente) array[i];
                        campoTexto[i].setText(p.getIdPaciente());
                    } else { // sanitario (no hay de auxiliares)
                        s = (Sanitario) array[i];
                        campoTexto[i].setText(s.getIdSanitario());
                    }
                } else {
                    campoTexto[i].setText("");
                }
            }
        } else { // JTextArea
            areaTexto.setText("");
            for (Thread hilo : lista) {
                if (hilo instanceof Paciente) {
                    p = (Paciente) hilo;
                    if (areaTexto.getText().isEmpty()) {
                        areaTexto.setText(p.getIdPaciente());
                    } else {
                        areaTexto.setText(areaTexto.getText() + ", " + p.getIdPaciente());
                    }

                } else if (hilo instanceof Sanitario) {
                    s = (Sanitario) hilo;
                    if (areaTexto.getText().isEmpty()) {
                        areaTexto.setText(s.getIdSanitario());
                    } else {
                        areaTexto.setText(areaTexto.getText() + ", " + s.getIdSanitario());
                    }
                } else if (hilo instanceof Auxiliar) {
                    a = (Auxiliar) hilo;
                    if (areaTexto.getText().isEmpty()) {
                        areaTexto.setText(a.getIdAuxiliar());
                    } else {
                        areaTexto.setText(areaTexto.getText() + ", " + a.getIdAuxiliar());
                    }
                }
            }
        }
    }

    
    /**
     * Cambia el color del texto en la posición espeficada. Si se trata de un
     * array de JTextField se cambia solo en la posición (constructor 1), pero 
     * si utilizamos el JTextArea cambiamos el color de todo.
     * 
     * @param pos Posición a colorear (solo útil en caso de que se haya usado
     * el primer constructor).
     * @param c Color al que deseamos cambiar el texto.
     */
    public synchronized void cambiarColorTexto(int pos, Color c) {
        if (capacidad != -1) {
            campoTexto[pos].setForeground(c);
        } else {
            areaTexto.setForeground(c);
        }
    }
}
