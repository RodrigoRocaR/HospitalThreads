package HospitalUtil;

import java.text.DecimalFormat;

public class Sanitario extends Thread {

    private String idSanitario;
    private Hospital hosp;
    private int puestoAsociado = -1; // puesto que tendrá asignado el sanitario

    public Sanitario(int id, Hospital hosp) {
        DecimalFormat df = new DecimalFormat("00");
        this.idSanitario = "S" + df.format(id);
        this.hosp = hosp;
    }

    @Override
    public void run() {
        // Van a cambiarse
        cambiarse(); 
        while (true) {
            // Se ponen a vacunar 15 pacientes
            hosp.vacunar(this);
            // Descansamos un poco
            hosp.descansarSalaDescanso(this);
            // Cuando salimos miramos a ver si hay pacientes con reaccion a la vac
            hosp.tratarReaccionVacuna(this);
        }
    }

    private void cambiarse() {
        try {
            Thread.sleep((int) (2000 * Math.random()) + 1000); // 1 a 3 seg
        } catch (InterruptedException ex) {
            System.out.println("Sanitario interrumpido mientras descansaba");
        }
    }

    public String getIdSanitario() {
        return idSanitario;
    }

    public int getPuestoAsociado() {
        return puestoAsociado;
    }

    public void setPuestoAsociado(int puestoAsociado) {
        this.puestoAsociado = puestoAsociado;
    }
    
}
