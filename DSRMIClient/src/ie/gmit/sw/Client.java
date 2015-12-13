package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import ie.gmit.sw.Constants;
import ie.gmit.sw.VigenereBreaker;
import ie.gmit.sw.VigenereRequestManager;

public class Client {
	private long jobNumber;
	
	public static void main(String[] args) throws Exception {		
		
		Client client = new Client();
		VigenereBreaker vb = (VigenereBreaker)Naming.lookup(Constants.getID());
		System.out.println("Client Running");
		
//		Client client = new Client();
//		client.writeFile(vb.decrypt(client.getText("../EncryptedWarAndPeace.txt"), 4)); // Version outputting encoded text file and using it for decoding. Requires below methods getText and setText.
		
		VigenereRequestManager vrm = new VigenereRequestManager();
		client.jobNumber = vrm.createRequest("SBTUPWAWGTMNZQKVMQZHIJWAZUGU", 4);
		System.out.println("Added request " + client.jobNumber);
	}
	
	@SuppressWarnings("unused")
	private String getText(String file) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuffer sb = new StringBuffer();
		String line;
		
		while((line = br.readLine()) != null)
			sb.append(line);
		
		br.close();
		return sb.toString();		
	}
	
	@SuppressWarnings("unused")
	private void writeFile(String text) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("../DecryptedWarAndPeace.txt"));
		
		bw.write(text);
		
		bw.close();
	}
}
