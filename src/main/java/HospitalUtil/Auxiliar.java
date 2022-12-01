package HospitalUtil;

public class Auxiliar extends Thread {

    private int cansancio = 0;
    private String idAuxiliar;
    private boolean rolRegistrar;
    private Hospital hosp;

    public Auxiliar(int id, boolean rolRegistrar, Hospital hosp) {
        this.idAuxiliar = "A" + Integer.toString(id);
        this.rolRegistrar = rolRegistrar;
        this.hosp = hosp;
    }

    @Override
    public void run() {
        if (rolRegistrar) {
            // Pasa pacientes a la sala de espera y vacunacion
            while (true) {
                hosp.pasarPacientesVacunacion();
                cansancio++;
                if (cansancio == 10) {
                    // descansa un rato 
                    hosp.descansarSalaDescanso(this, rolRegistrar);
                    cansancio = 0;
                }
            }
        } else {
            // Prepara dosis para los sanitarios
            while (true) {
                hosp.prepararDosis();
                cansancio++;
                if (cansancio == 20){
                   // descansa un rato 
                   hosp.descansarSalaDescanso(this, rolRegistrar);
                   cansancio = 0;
                }
            }
        }
    }

    public String getIdAuxiliar() {
        return idAuxiliar;
    }

}
