package HospitalUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {

    public Log() {
        crearLog();
    }

    /**
     * Crea el log con el nombre "evolucionHospital.txt" en la ubicación del
     * proyecto.Si ya existía, se elimina el contenido anterior.Si no, se crea
     * uno nuevo.
     */
    public final void crearLog() {
        try {
            // la ubicacion del archivo es relativa a la del proyecto java
            File log = new File("evolucionHospital.txt");
            if (!log.createNewFile()) { // creamos el archivo txt
                // si ya existe, lo borramos para empezar de nuevo
                FileWriter fw = new FileWriter("evolucionHospital.txt");
                fw.write("");
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al crear el log");
        }
    }

    /**
     * Escribe el string especificado acompañado de la fecha y hora que tuvo lugar
     * la llamada a este método.
     * 
     * @param evento String que describe el evento a escribir en el log.
     */
    public synchronized void escribirEvento(String evento) {
        try {
            // Indicamos que queremos sumar el string y no sobreescribir:
            FileWriter fw = new FileWriter("evolucionHospital.txt", true);
            String marcaTiempo = new SimpleDateFormat("(dd/MM/yyyy HH:mm.ss) : ")
                    .format(Calendar.getInstance().getTime());
            // Escribimos el evento:
            fw.write(marcaTiempo + evento);
            // Y saltamos a la siguiente línea:
            fw.write(System.getProperty("line.separator"));
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribrir en el log");
        }
    }
}
