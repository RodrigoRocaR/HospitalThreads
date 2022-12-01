package ClienteServidor;


import InterfazGrafica.VentanaV2;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VentServer extends UnicastRemoteObject implements InterfaceVentServer {
    
    // Nada más iniciar el servidor, comienza el programa:
    private VentanaV2 vent2 = new VentanaV2();
    
    // Constructor:
    public VentServer() throws RemoteException {
    }

    /**
     * Obtiene el estado actual del hospital.
     * 
     * @return Array genérico de tipo Object con: los campos de la ventana y
     * los puestos que están cerrados.
     * @throws RemoteException 
     */
    @Override
    public Object[] getEstadoVentana() throws RemoteException {
        Object[] obj = {vent2.getCamposVentana(), vent2.getPuestosCerrados()};
        return obj;
    }

    /**
     * Abre/Cierra un puesto de vacunación determinado.
     * 
     * @param nPuesto Nº puesto que queremos cerrar (0 al 9)
     * @throws RemoteException 
     */
    @Override
    public void abrirCerrarPuesto(int nPuesto) throws RemoteException {
        vent2.abrirCerrarPuesto(nPuesto);
    }  
}
