import java.rmi.Remote;
import java.rmi.RemoteException;

/**
* Interface Remota con métodos para llamada en remoto
*/

public interface InterfaceRemota extends Remote{
		public double cuotaMensual(double capital, double interes, double plazo) throws RemoteException;
} // Fin InterfaceRemota
