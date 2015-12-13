package ie.gmit.sw;

import java.util.*;
import java.io.*;

public class QuadgramMap 
{
	private int nGramC;
	private static Map<String, Integer> map = new HashMap<String, Integer>(); 
	
	public QuadgramMap(String file, int n) throws Exception 
	{
		nGramC = n;
		parse(file);
		
	}
	
	public double getScore(String text)
	{
		double totalScore = 0f;
		double score = 0f;
		
		for (int i = 0; i < text.length(); i += 4) 
		{
			
			if (i + 4 > text.length()) 
				break;
			
			String next = text.substring(i, i+4);
			
			if (map.get(next) != null)
			{
				double frequency = (double)map.get(next);
				double total = (double)map.size();
				score = ((Math.log10(frequency))/total);
				totalScore += score;
			}
		}
		return totalScore;
	}
	
	private void parse(String file) throws Exception 
	{
		BufferedReader br;
		try {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		}
		catch(Exception e){
			file = "./WarAndPeace-Tolstoy.txt";
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		}
		StringBuffer sb = new StringBuffer();
		
		int j;
		while((j = br.read()) != -1)
		{
			char next = (char)j;
			
			if (next >= 'A' && next <= 'z')			
				sb.append(next);
			
			
			if(sb.length() == nGramC)
			{
				String qGram = sb.toString().toUpperCase();
				sb = new StringBuffer();
				
				int frequency = 0;
				
				if(map.containsKey(qGram))
					frequency = map.get(qGram);
				
				
				frequency++;
				map.put(qGram, frequency);
			}
		}
		br.close();
		System.out.println(map);
	}
	
//	public static void main(String[] args) throws Exception 
//	{
//		new QuadgramMap("./Text.txt", 4);
//	}
}
