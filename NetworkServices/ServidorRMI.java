package SeguridadRMI;

import java.rmi.Remote;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

/*
 * 1. Crear registro
 * 2. Rebind registro (nombre inventado y objeto instanciado de RMI)
 * 3. Crear mensaje para avisar de servidor conectado
 * 4. Catch excepción por si falla
 */

public class ServidorRMI {

	public static void main(String[] args) {

		try {
			
			Registry registro = java.rmi.registry.LocateRegistry.createRegistry(9998);
			
			registro.rebind("SeguridadRMI", (Remote) new RMI());
			
			System.out.println("Servidor conectado");
		}
		catch (Exception e) {

			System.out.println("Servidor desconectado");
			System.out.println(e);
		}
	}
}
