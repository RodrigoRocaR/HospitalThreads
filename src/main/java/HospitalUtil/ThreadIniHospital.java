package HospitalUtil;



public class ThreadIniHospital extends Thread{
    
    private Hospital hospital;

    public ThreadIniHospital(Hospital hospital) {
        this.hospital = hospital;
    }
    
    @Override
    public void run(){
        inicializarHospital(); // nuestra única tarea es lanzar hilos
    }
    
    public void inicializarHospital() {
        // Lanzamos sanitarios y auxiliares
        Sanitario s;
        Auxiliar a;
        boolean rolAuxiliar = true;
        for (int i = 0; i < 10; i++) {
            if (i <= 1) { // 2 auxiliares
                a = new Auxiliar(i + 1, rolAuxiliar, hospital); // i+1 para que sea auxilar 1 y 2
                a.start();
                rolAuxiliar = false;
            }
            s = new Sanitario(i, hospital);
            s.start();
        }
        // Lanzamos 2000 pacientes:
        Paciente paciente;
        for (int i = 0; i < 2000; i++) {
            paciente = new Paciente(i, hospital);
            paciente.start();
            // Intervalo entre la llegada de cada paciente al hospital:
            try {
                Thread.sleep((int) (2000 * Math.random()) + 1000); // 1 a 3 seg
            } catch (InterruptedException ex) {
                System.out.println("Se ha interrumpido la llegada de pacientes");
            }
        }
    }

}
