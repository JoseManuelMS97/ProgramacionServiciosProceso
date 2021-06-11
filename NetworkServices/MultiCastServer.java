import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class MultiCastServer {
	
	public static boolean finServer = false;
	final static int puerto = 9999;
	static ServerSocket miServer;
	
	public static void main(String[] args) throws IOException {
		
		String mNet = "224.0.0.1";
		int mPort = 3333;
		miServer = new ServerSocket(puerto);
		
		Semaphore lock = new Semaphore(1);
		PipedWriter emisor = new PipedWriter();
		PipedReader receptor = new PipedReader(emisor);
		PrintWriter flujoS = new PrintWriter(emisor);
		BufferedReader flujoE = new BufferedReader(receptor);
		
		System.out.println("Servidor funcionando....");
		MultiCastEmisor multiService = new MultiCastEmisor(flujoE,mNet,mPort);
		while (!finServer) {
			try {
			//Aceptamos conexiones
			Socket s = miServer.accept();
			//Iniciamos el servicio que gestiona la conexión
			ClienteMultiService service = new ClienteMultiService(s,lock,flujoS);
			} catch (IOException e) {
				System.out.println("Cerrando el servidor");
			}
		}
		multiService.finMulticastEmisor();
	}
	
	public static void finalizarServidor(){
		finServer=true;
		try {
			miServer.close();
		} catch (IOException e) {
			System.out.println("Cerrando el servidor");
		}
		
	}

}
