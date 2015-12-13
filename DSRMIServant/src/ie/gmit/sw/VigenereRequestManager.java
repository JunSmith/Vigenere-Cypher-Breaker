package ie.gmit.sw;

import java.util.Map;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class VigenereRequestManager {
	private BlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(10);
	private Map<Long, String> out = new ConcurrentHashMap<Long, String>();
	private VigenereHandler vh;
	
	public VigenereRequestManager(){
		
	}
	
	public VigenereRequestManager(Request request){
		add(request);
	}
	
	public void add(final Request r){
		try{
			queue.put(r);
			out.put(r.getJobNumber(), r.getCypherText());
			vh = new VigenereHandler(queue, out);
			out.put(r.getJobNumber(), vh.getResult(r.getJobNumber()));
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public long createRequest(String cypherText, int maxKeyLength) {
		Random random = new Random();
		Request request;
		long jobNumber;
		
		do{
			jobNumber = random.nextLong();
		}while(out.containsKey(jobNumber));
		
		request = new Request(cypherText, maxKeyLength, jobNumber);
		add(request);
		return jobNumber;
	}
	
	public String getResult(long jobnumber) {
		if(out.containsKey(jobnumber)) // and if string has changed
			return out.get(jobnumber);
		
		else
			return null;
	
	}
}