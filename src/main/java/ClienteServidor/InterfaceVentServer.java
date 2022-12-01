
package ClienteServidor;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface InterfaceVentServer extends Remote{
    
    Object[] getEstadoVentana() throws RemoteException;
    void abrirCerrarPuesto(int nPuesto) throws RemoteException ;
}
