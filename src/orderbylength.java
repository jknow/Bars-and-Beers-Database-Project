import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class orderbylength {

	static String typesArray[];
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader typeReader = new BufferedReader(new FileReader("beerTypes.txt"));
		typesArray = new String[55];
		fillArray(typesArray, "beerTypes.txt");
		String printArray[] = new String[55];
		String currtype;
		String printtemp = "";
		int ktemp = 54;
		
		int i = 0;
		while((currtype = typeReader.readLine()) != null)
		{
			typesArray[i] = currtype;
			i++;
		}
		
		for(int j = 0; j < printArray.length; j++)
		{
			for(int k = 0; k < typesArray.length; k++)
			{
				//System.out.println("typesArray[k]: " + typesArray[k]);
				if(typesArray[k].length() > printtemp.length())
				{
					printtemp = typesArray[k];
					ktemp = k;
				}
			}
			printArray[j] = printtemp;
			typesArray[ktemp] = "";
			ktemp = 54;
			printtemp = "";
		}
		
		for(int j = 0; j < printArray.length; j++)
		{
			System.out.println(printArray[j]);
		}
		
		
		typeReader.close();

	}
	
	private static void fillArray(String[] arr, String textfile) throws IOException {
		
		BufferedReader barReader = new BufferedReader(new FileReader(textfile));
		String curr = "";
		int i = 0;
		while((curr = barReader.readLine()) != null && i < arr.length)
		{
			curr = curr.substring(curr.indexOf(' ') + 1);
			arr[i] = curr;
			i++;
		}
		barReader.close();
	}

}
