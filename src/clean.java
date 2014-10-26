import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class clean {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File del = new File("newwestbeers.txt");
		del.delete();
		
		BufferedReader beerReader = new BufferedReader(new FileReader("menuwestbeers.txt"));
		//BufferedReader typeReader = new BufferedReader(new FileReader("beertypes.txt"));
		
		FileOutputStream fileout = new FileOutputStream("newwestbeers.txt");
		PrintStream printout = new PrintStream(fileout);
		
		String currbeer = "";
		String currtype = "";
		String country = "";
		
		try {
			while((currbeer = beerReader.readLine()) != null)
			{
				if(currbeer.contains("oz"))
				{
					int prevlastIndex = 0;
					BufferedReader typeReader = new BufferedReader(new FileReader("beerTypes.txt"));
					while((currtype = typeReader.readLine()) != null)
					{
						if(currbeer.contains(currtype))
						{
							int lastIndex = 0;
							prevlastIndex = 0;
							while(lastIndex != -1)
							{
								prevlastIndex = lastIndex;

								lastIndex = currbeer.indexOf(currtype,lastIndex);

								if( lastIndex != -1)
								{
									lastIndex+=currtype.length();
								}
							}
							
							prevlastIndex -= currtype.length();
							
							break;
						}
					}
					typeReader.close();
					
					printout.println(currbeer.substring(0, prevlastIndex));
					printout.println(currbeer.substring(prevlastIndex));
				}
				else if (currbeer.length() > 0 && !Character.isUpperCase(currbeer.charAt(2)))
				{
					if(!currbeer.equals(country))
					{
						printout.println();
						printout.println(currbeer + " (Place of Origin)");
						country = currbeer;
					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printout.close();
		try {
			fileout.close();
			beerReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Finished");
		return;
	}

}
