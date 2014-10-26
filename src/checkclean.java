import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class checkclean {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader beerReader = new BufferedReader(new FileReader("menuwestbeers.txt"));
		//BufferedReader typeReader = new BufferedReader(new FileReader("beertypes.txt"));
		
		String currbeer = "";
		String currtype = "";
		int linenumber = 0;
		
		boolean containstype = false;
		
		try {
			while((currbeer = beerReader.readLine()) != null)
			{
				linenumber++;
				BufferedReader typeReader = new BufferedReader(new FileReader("beerTypes.txt"));
				while((currtype = typeReader.readLine()) != null)
				{
					if(currbeer.contains(currtype))
					{
						containstype = true;
					}
				}
				
				if(!containstype && currbeer.contains("oz"))
				{
					System.out.println(linenumber +": " + currbeer);
				}
				if(currbeer.length() > 0)
				{
				if(!currbeer.contains("oz") && !Character.isUpperCase(currbeer.charAt(2)))
				{
					System.out.println("ERROR: " + currbeer);
				}
				}
				containstype = false;
				typeReader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			beerReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return;
	}

}
