package SeguridadRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

/*
* 1. Extender UnicastRemoteObject
* 2. Implementar la interfaz remota (SeguimientoInterface o la interfaz que sea)
* 3. Crear constructor obligatoriamente
* 4. Añadir métodos de la interfaz
*/

public class RMI extends UnicastRemoteObject implements SeguridadRMI {

	protected RMI() throws RemoteException {
		PublicKey clavepub;
		String mensaje; 
		byte [] firma;
	}

	@Override
	public String firma(PublicKey clavepub, String mensaje, byte [] firma) throws RemoteException {
		
		String confirmacion = "";
		Signature verificada;
		try {
			verificada = Signature.getInstance("SHA1withDSA","SUN");
			
	        verificada.initVerify(clavepub);
	        verificada.update(mensaje.getBytes());
	        boolean check = verificada.verify(firma);
	        if(check) {
	        	confirmacion = "FIRMA CORRECTA VERIFICADA CON CLAVE PÚBLICA";
	        }
	            
	        else {
	        	confirmacion = "FIRMA ERRONEA";
	        }
	        	
	        
	        
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return confirmacion;

		

	}
}
