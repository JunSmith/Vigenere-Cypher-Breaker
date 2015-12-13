package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ie.gmit.sw.KeyEnumerator;
import ie.gmit.sw.VigenereBreaker;
import ie.gmit.sw.VigenereRequestManager;

public class VigenereBreakerImpl extends UnicastRemoteObject implements VigenereBreaker {
	private static final long serialVersionUID = 777L;
	private KeyEnumerator keyEnum;
	private VigenereRequestManager vrm;
	private long jobNumber = 0L;

	public VigenereBreakerImpl() throws Exception {
		super();
		keyEnum = new KeyEnumerator();
	}

	public String decrypt(String cypherText, int maxKeyLength) throws RemoteException {
		StringBuffer sb = new StringBuffer();
		sb.append(cypherText);
		
		return keyEnum.crackCypher(sb.toString(), maxKeyLength).toString();
	}

}
