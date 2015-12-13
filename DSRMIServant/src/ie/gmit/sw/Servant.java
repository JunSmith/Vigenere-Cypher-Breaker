package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import ie.gmit.sw.Constants;

public class Servant {
	public static void main(String[] args) throws Exception {
		VigenereBreakerImpl impl = new VigenereBreakerImpl();
		
//		Registry registry = LocateRegistry.createRegistry(Constants.getPort()); //Registry method
//		registry.bind(Constants.getID(), impl);
		
		LocateRegistry.createRegistry(Constants.getPort());
		Naming.bind(Constants.getID(), impl);
		
		System.out.println("Server started");
	}
}
