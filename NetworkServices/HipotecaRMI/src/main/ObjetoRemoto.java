package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjetoRemoto extends UnicastRemoteObject implements InterfaceRemota{
    
    // Construye una instancia de ObjetoRemoto
    public ObjetoRemoto() throws RemoteException{
        super();
    } // Fin Constructor
    public static void main(String[] args) throws RemoteException, MalformedURLException {
		Naming.rebind("rmi://localhost/Server",new ObjetoRemoto());
	}
    // Métodos
    // Calcula la cuota mensual de un prestamo dado capital, interes anual y plazo anual
    public double cuotaMensual(double capital, double interes, double plazo){
        System.out.println("Calculando cuota...");
        double plazoMes = plazo * 12.00;
        double interesMes = interes / 12.00;
        return (capital * interes) / (100.00 * (1 - (Math.pow(interesMes / 100.00, 0-plazoMes))));
    } // Fin cuotaMensual
} // Fin ObjetoRemoto