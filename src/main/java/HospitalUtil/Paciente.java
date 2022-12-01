package HospitalUtil;

import java.text.DecimalFormat;

public class Paciente extends Thread{
    private String idPaciente;
    private Hospital hosp;

    public Paciente(int id, Hospital hosp) {
        // para que los nº tengan 0 de relleno a la izq (0001, 0011, 0111):
        DecimalFormat df = new DecimalFormat("0000");
        this.idPaciente = "P" + df.format(id);
        this.hosp = hosp;
    }
    
    @Override
    public void run(){
        boolean puedoContinuar = hosp.esperarVacunarse(this);
        if (puedoContinuar){
            hosp.pasarAVacunarse(this);
            hosp.pasarASalaObservacion(this);
            hosp.salirHospital(this);
        }
        else{
            System.out.println(idPaciente+" ha acudido sin cita");
        }
    }

    public String getIdPaciente() {
        return idPaciente;
    } 
}
