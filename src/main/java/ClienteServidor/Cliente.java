package ClienteServidor;


import InterfazGrafica.VentanaV2Forma;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {

    // Para un correcto funcionamiento, ejecutar "Servidor" antes.
    public static void main(String args[]) {
        try {
            // Obtenemos la interfaz del objeto remoto registrada en el servidor:
            InterfaceVentServer ventanaRemota = (InterfaceVentServer) Naming.lookup("//127.0.0.1/Ventana");
            
            // Creamos nuestra propia ventana vacía (solo tiene la forma):
            VentanaV2Forma v2f = new VentanaV2Forma(ventanaRemota);
            Object[] estado;
            v2f.setVisible(true);
            
            //Para finalizar ejecución cerrar ventana "v2f".
            while (true) {
                // Obtenemos el estado actual del hospital desde el servidor:
                estado = ventanaRemota.getEstadoVentana();
                // La mostramos en nuestra ventana local:
                v2f.rellenarInfoVentana(estado);
                // Paramos un poco para no pedir demasiado frecuente al servidor:
                Thread.sleep(1000);
            }
            
        } catch (NotBoundException | MalformedURLException
                | RemoteException | InterruptedException ex) {
            System.out.println("Algo salió mal en el cliente: ");
            System.out.println(ex.getMessage());
        }
    }
}
