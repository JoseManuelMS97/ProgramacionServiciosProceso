package SeguridadRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
import java.util.Scanner;

import javax.swing.JOptionPane;

/*
 * 1. Buscar registro al servidor (proporcionando host y puerto)
 * 2. Crear objeto y buscar el programa calculadora (casteando al objeto)
 * 3. Crear funcionalidad que llame a RMI
 */

public class ClienteRMI {

	public static void main(String[] args) throws MalformedURLException, NotBoundException {

		try {

			Registry miRegistro = LocateRegistry.getRegistry("localhost", 9998);
			Remote remote = miRegistro.lookup("SeguridadRMI");

			System.out.println("Generando claves...");
			
			// Creando objeto generador de claves
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
			SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
			keyGen.initialize(1024, numero);

			// Crea clave privada y pública
			KeyPair parClaves = keyGen.generateKeyPair();
			PrivateKey clavepriv = parClaves.getPrivate();
			PublicKey clavepub = parClaves.getPublic();

			// Firma el mensaje con clave privada
			Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
			dsa.initSign(clavepriv);
			String mensaje = "Este mensaje va a ser firmado";
			dsa.update(mensaje.getBytes());

			byte[] firma = dsa.sign(); // Firma el mensaje
			System.out.println("Texto a firmar :" + mensaje);
			System.out.println("Firma " + Hexadecimal(firma));
			System.out.println("Clave Publica : " + clavepub.toString());
			System.out.println("Clave Privada : " + clavepub.toString());
			
			System.out.println("");
			System.out.println(((SeguridadRMI) remote).firma(clavepub, mensaje, firma));
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {

			System.out.println("Servidor desconectado");
		}

	}// end-main

	public static String Hexadecimal(byte[] resumen) {

		String hex = "";
		for (int i = 0; i < resumen.length; i++) {

			String h = Integer.toHexString(resumen[i] & 0xFF);
			if (h.length() == 1)
				hex += "0";
			hex += h;
		} // Fin de for

		return hex.toUpperCase();

	}
}
