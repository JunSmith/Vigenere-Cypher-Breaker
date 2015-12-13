package ie.gmit.sw;

public class Constants {
	private static final String RMI_ID = "VigenereRMI";
	private static final int RMI_PORT = 1099;
	
	public static String getID() {
		return RMI_ID;
	}
	
	public static int getPort()	{
		return RMI_PORT;
	}
}
