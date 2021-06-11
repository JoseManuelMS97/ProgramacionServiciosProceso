import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class RMIClient {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		InterfaceRemota conector = (InterfaceRemota) Naming.lookup("//localhost/Server");
				
		Double respuesta = conector.cuotaMensual(1000000,4,20);
		
		JOptionPane.showMessageDialog(null, respuesta);
	}
}
