package ie.gmit.sw;

public class KeyEnumerator // Generates a key and tries to crack the cyphertext with it
{	
	private QuadgramMap map = null;
	private double highScore;
	private String highKey;
	
	public KeyEnumerator() throws Exception
	{
		map = new QuadgramMap("../WarAndPeace-Tolstoy.txt", 4);
	}
	
	private char[] getNextKey(char[] key)
	{
		for (int i = key.length - 1; i >=0; i--)
		{
			if (key[i] =='Z')
			{
				if (i == 0) 
					return null;
				key[i] = 'A';
			}
			else 
			{
				key[i]++;
				break;
			}
		}
		return key;
	}
	
	
	public String crackCypher(String cypherText, int maxKeyLength)
	{
		System.out.println("in crackCypher");
		char[] key = null;
		
		int counter = 0;
		for (int j = 3; j <= maxKeyLength; j++)
		{
			key = new char[j];
			
			for (int k = 0; k < key.length; k++)
				key[k] = 'A';
			
			do
			{
				counter++;
				String result = new Vigenere(new String(key)).doCypher(cypherText, false);
				double score = map.getScore(result);
				
				if(score > highScore)
				{
					highKey = new String(key);
					highScore = score;
					System.out.println("New Record:\n\tKey: " + highKey + "\n\tScore: " + highScore);
				}
				
			}while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");
		String decryptedStr = new Vigenere(highKey).doCypher(cypherText,  false);
		return decryptedStr;
	}
}
