package ie.gmit.sw;

import java.rmi.Naming;
import java.util.Map;
import java.util.concurrent.*;

public class VigenereHandler implements Runnable {
	private BlockingQueue<Request> queue;
	private Map<Long, String> out = new ConcurrentHashMap<Long, String>();
	private KeyEnumerator keyEnum;
	
	public VigenereHandler(BlockingQueue<Request> q, Map<Long, String> out) {
		this.queue = q;
		this.out = out;
		run();
	}
	
	public void run() {
		try {
			Request req = queue.take();
			VigenereBreaker vb = (VigenereBreaker) Naming.lookup(Constants.getID());
			String result = vb.decrypt(req.getCypherText(), req.getMaxKeyLength());
			System.out.println(result);
			out.put(req.getJobNumber(), result);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getResult(long jobNumber){
		return out.get(jobNumber);
	}

}