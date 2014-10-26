import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class allorigins {
	
	static ArrayList<String> beerArray;
	
	static int ibeer;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ibeer = 0;
		beerArray = new ArrayList<String>();
		
		addtoArray("newannbeers.txt");
		addtoArray("newarlinbeers.txt");
		addtoArray("newbaltbeers.txt");
		addtoArray("neweastonbeers.txt");
		addtoArray("newevanbeers.txt");
		addtoArray("newlakebeers.txt");
		addtoArray("newmilbeers.txt");
		addtoArray("newnbbeers.txt");
		addtoArray("newwestbeers.txt");
		
		Collections.sort(beerArray);
		
		int ispace = -1;
		
		for(int i = 0; i < beerArray.size(); i++)
		{
			ispace = beerArray.get(i).indexOf(" ");
			String out;
			String origin = beerArray.get(i);
			
			if(ispace != -1)
			{
				out = beerArray.get(i).substring(0, ispace) + beerArray.get(i).substring(ispace + 1);
			}
			else
			{
				out = beerArray.get(i);
			}
			
			System.out.println("case \"" + origin + "\":");
			System.out.println("	if(contains(" + out + ", temp))");
			System.out.println("	{");
			System.out.println("		"+ out + ".add(temp);");
			System.out.println("	}");
			System.out.println("	break;");
			
		}
		
	}

	private static void addtoArray(String filename) throws IOException {
		BufferedReader beerReader = new BufferedReader(new FileReader(filename));
		
		String currbeer;
		String origin = null;
		
		while((currbeer = beerReader.readLine()) != null)
		{
			if(currbeer.contains("(Place of Origin)"))
			{
				origin = (currbeer.substring(0, currbeer.indexOf("(Place of Origin)")-1)).trim();
				
				if(origin.contains("United States -"))
				{
					System.out.println(filename);
				}
				
				if(!beerArray.contains(origin))
				{
					beerArray.add(origin);
				}
			}
		}
		
		beerReader.close();

	}
}
