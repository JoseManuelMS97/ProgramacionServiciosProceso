import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ClienteMultiService extends Thread {

	private Socket s;
	Scanner in;
	PrintWriter out;
	InetAddress IP;
	boolean finService = false;
	Semaphore lock;
	PrintWriter flujoS;
	
	public ClienteMultiService(Socket s, Semaphore lock, PrintWriter flujoS) {
		this.s=s;
		this.lock = lock;
		this.flujoS = flujoS;
		this.start();
	}
	
	public void run() {
		try {
			out = new PrintWriter(s.getOutputStream());
			in = new Scanner(s.getInputStream());
			IP = s.getInetAddress();
			out.println("Bienvenido a su Banca Online");
			out.println("----------------------------");
			out.println(">");
			out.flush();
			doService();
			out.close();
			in.close();
			s.close();
			System.out.println("El cliente "+IP+" se ha desconectado");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doService() throws InterruptedException, IOException {
		String comando = null;
		
		while (!finService) {
			//el cliente corta la conexión de forma abrupta
			if (!in.hasNext()) return;
			comando = in.nextLine();
			if (comando.contains("quit")) {
				out.println("El cliente cierrra la conexión");
				break;
			}
			if (comando.contains("halt")) {
				MultiCastServer.finalizarServidor();
				break;
			}
			procesaComando(comando);
		}//end while
	}

	private void procesaComando(String comando) throws InterruptedException {
		String mensa;
		System.out.println(comando);
		if (comando.contains("mensa")) {
			mensa = comando.substring(5);
			out.println("Enviando... "+mensa);
			out.flush();
			//controlamos el acceso concurrente al pipe y enviamos la información
		    //a implementar
		}
	}

}
