package ClienteServidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String args[]) {
        try {
            VentServer obj = new VentServer(); 
            Registry registry = LocateRegistry.createRegistry(1099); 
            Naming.rebind("//localhost/Ventana", obj); 
        } catch (MalformedURLException | RemoteException e){
            System.out.println("Algo salió mal al registrar el objeto remoto: ");
            System.out.println(e.getMessage());
        }

    }
}
