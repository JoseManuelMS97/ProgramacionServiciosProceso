package SeguridadRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.PublicKey;

/*
 * 1. Importar Remote (extends remote)
 * 2. Crear método de la interfaz que se llamará desde cliente (genera RemoteException)
 */

public interface SeguridadRMI extends Remote {

	public String firma(PublicKey clavepub, String mensaje, byte [] firma) throws RemoteException;
}