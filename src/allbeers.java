import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class allbeers {
	
	static ArrayList<beer> beerArray;
	
	static ArrayList<beer> Alaska;
	static ArrayList<beer> Argentina;
	static ArrayList<beer> Arizona;
	static ArrayList<beer> Australia;
	static ArrayList<beer> Austria;
	static ArrayList<beer> Bahamas;
	static ArrayList<beer> Barbados;
	static ArrayList<beer> Belgium;
	static ArrayList<beer> Brazil;
	static ArrayList<beer> California;
	static ArrayList<beer> Canada;
	static ArrayList<beer> China;
	static ArrayList<beer> Colombia;
	static ArrayList<beer> Colorado;
	static ArrayList<beer> CostaRica;
	static ArrayList<beer> Croatia;
	static ArrayList<beer> Cyprus;
	static ArrayList<beer> CzechRepublic;
	static ArrayList<beer> Delaware;
	static ArrayList<beer> Denmark;
	static ArrayList<beer> DistrictofColumbia;
	static ArrayList<beer> Dominican;
	static ArrayList<beer> ElSalvador;
	static ArrayList<beer> England;
	static ArrayList<beer> Ethiopia;
	static ArrayList<beer> Finland;
	static ArrayList<beer> Florida;
	static ArrayList<beer> France;
	static ArrayList<beer> Georgia;
	static ArrayList<beer> Germany;
	static ArrayList<beer> Greece;
	static ArrayList<beer> Guatemala;
	static ArrayList<beer> Hawaii;
	static ArrayList<beer> Idaho;
	static ArrayList<beer> Illinois;
	static ArrayList<beer> India;
	static ArrayList<beer> Indiana;
	static ArrayList<beer> Ireland;
	static ArrayList<beer> Israel;
	static ArrayList<beer> Italy;
	static ArrayList<beer> Jamaica;
	static ArrayList<beer> Japan;
	static ArrayList<beer> Kansas;
	static ArrayList<beer> Kentucky;
	static ArrayList<beer> Kenya;
	static ArrayList<beer> Laos;
	static ArrayList<beer> Latvia;
	static ArrayList<beer> Lebanon;
	static ArrayList<beer> Lithuania;
	static ArrayList<beer> Louisiana;
	static ArrayList<beer> Maine;
	static ArrayList<beer> Maryland;
	static ArrayList<beer> Massachusetts;
	static ArrayList<beer> Mexico;
	static ArrayList<beer> Michigan;
	static ArrayList<beer> Minnesota;
	static ArrayList<beer> Mississippi;
	static ArrayList<beer> Missouri;
	static ArrayList<beer> Montana;
	static ArrayList<beer> Morocco;
	static ArrayList<beer> Netherlands;
	static ArrayList<beer> NewHampshire;
	static ArrayList<beer> NewJersey;
	static ArrayList<beer> NewYork;
	static ArrayList<beer> NewZealand;
	static ArrayList<beer> Nicaragua;
	static ArrayList<beer> NorthCarolina;
	static ArrayList<beer> Norway;
	static ArrayList<beer> Ohio;
	static ArrayList<beer> Oregon;
	static ArrayList<beer> Pennsylvania;
	static ArrayList<beer> Peru;
	static ArrayList<beer> Philippines;
	static ArrayList<beer> Poland;
	static ArrayList<beer> PuertoRico;
	static ArrayList<beer> Russia;
	static ArrayList<beer> Scotland;
	static ArrayList<beer> Serbia;
	static ArrayList<beer> Singapore;
	static ArrayList<beer> SlovakRepublic;
	static ArrayList<beer> SouthCarolina;
	static ArrayList<beer> Spain;
	static ArrayList<beer> SriLanka;
	static ArrayList<beer> Texas;
	static ArrayList<beer> Thailand;
	static ArrayList<beer> Trinidad;
	static ArrayList<beer> Turkey;
	static ArrayList<beer> Ukraine;
	static ArrayList<beer> Utah;
	static ArrayList<beer> Venezuela;
	static ArrayList<beer> Vermont;
	static ArrayList<beer> Vietnam;
	static ArrayList<beer> VirginIslands;
	static ArrayList<beer> Virginia;
	static ArrayList<beer> Washington;
	static ArrayList<beer> Wisconsin;

	
	static int ibeer;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ibeer = 0;
		beerArray = new ArrayList<beer>();
		
		initializeArrays();
		
		addtoArray("newannbeers.txt");
		addtoArray("newarlinbeers.txt");
		addtoArray("newbaltbeers.txt");
		addtoArray("neweastonbeers.txt");
		addtoArray("newevanbeers.txt");
		addtoArray("newlakebeers.txt");
		addtoArray("newmilbeers.txt");
		addtoArray("newnbbeers.txt");
		addtoArray("newwestbeers.txt");
		
		sortArrays();
		
		AddTobeerArray();
		
		beerArray.get(0).testprint();
		System.out.println(beerArray.size());
		
		outtosql();
		
		System.out.println("Finished");
		
	}

	private static void outtosql() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader beerReader = new BufferedReader(new FileReader("allbeers.txt"));
		
		String currbeer;
		String addRating;
		
		
		
		Class.forName("com.mysql.jdbc.Driver");
		String username = "csuser";
		String password = "csdaed1b";
		Connection conn = DriverManager.getConnection("jdbc:mysql://cs336-21.cs.rutgers.edu:3306/336bars", username, password);
		//jdbc:sqlserver://server:port;DatabaseName=dbname
		Statement stmt = conn.createStatement();
		String sql = "";
		String insertbeer = "INSERT INTO 336bars.Beers (BeerName, BeerType, Size, AlcoholPercentage, Origin, QualityRating)" +
					"VALUES ";
		stmt.executeUpdate("delete from 336bars.Beers");
		while((currbeer = beerReader.readLine()) != null)
		{
			if(currbeer.contains("Poland"))
			{
				addRating = currbeer.substring(0, currbeer.length()-1) + ", '" + "5" + "')";
			}
			else
			{
				addRating = currbeer.substring(0, currbeer.length()-1) + ", '" + beerArray.get(0).getRating() + "')";
			}
			sql = insertbeer + addRating;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		}
		
		stmt.close();
		conn.close();
		beerReader.close();
	}

	private static void outtofile() throws IOException {

		FileOutputStream fileout = new FileOutputStream("allbeers.txt");
		PrintStream printout = new PrintStream(fileout);
		
		for(int i = 0; i < beerArray.size(); i++)
		{
			printout.println(beerArray.get(i).testprint());
		}
		printout.close();
		fileout.close();
	}

	private static void addtoArray(String filename) throws IOException {
		BufferedReader beerReader = new BufferedReader(new FileReader(filename));
		
		String currbeer;
		String origin = null;
		
		while((currbeer = beerReader.readLine()) != null)
		{
			if(currbeer.contains("(Place of Origin)"))
			{
				origin = currbeer.substring(0, currbeer.indexOf("(Place of Origin)")-1);
			}
			else if((!currbeer.contains("%")) && currbeer.length() > 0)
			{
				String beername = currbeer.trim();
				String beerInfo = beerReader.readLine();
				String beerInfoTok[] = beerInfo.split(" ");
				String beerType = "";
				int size;
				double abv = 0;
				
				int i;
				for (i = 0; !beerInfoTok[i].contains("oz"); i++)
				{
					beerType = beerType + beerInfoTok[i] + " ";
				}
				
				beerType = beerType.trim();
				
				size = Integer.parseInt(beerInfoTok[i].substring(0, beerInfoTok[i].indexOf("oz")));
				
				if(beerInfoTok[i+1].equals("Can"))
				{
					abv = Double.parseDouble(beerInfoTok[i+2].substring(0, beerInfoTok[i+2].indexOf("%")));
				}
				else
				{
					abv = Double.parseDouble(beerInfoTok[i+1].substring(0, beerInfoTok[i+1].indexOf("%")));
				}
				
				beer temp = new beer(beername, beerType, size, abv, origin);
				
				addToOrigin(origin, temp);
			}
		}
		
		beerReader.close();
		
	}

	private static void AddTobeerArray() {
		beerArray.addAll(Alaska);
		beerArray.addAll(Argentina);
		beerArray.addAll(Arizona);
		beerArray.addAll(Australia);
		beerArray.addAll(Austria);
		beerArray.addAll(Bahamas);
		beerArray.addAll(Barbados);
		beerArray.addAll(Belgium);
		beerArray.addAll(Brazil);
		beerArray.addAll(California);
		beerArray.addAll(Canada);
		beerArray.addAll(China);
		beerArray.addAll(Colombia);
		beerArray.addAll(Colorado);
		beerArray.addAll(CostaRica);
		beerArray.addAll(Croatia);
		beerArray.addAll(Cyprus);
		beerArray.addAll(CzechRepublic);
		beerArray.addAll(Delaware);
		beerArray.addAll(Denmark);
		beerArray.addAll(DistrictofColumbia);
		beerArray.addAll(Dominican);
		beerArray.addAll(ElSalvador);
		beerArray.addAll(England);
		beerArray.addAll(Ethiopia);
		beerArray.addAll(Finland);
		beerArray.addAll(Florida);
		beerArray.addAll(France);
		beerArray.addAll(Georgia);
		beerArray.addAll(Germany);
		beerArray.addAll(Greece);
		beerArray.addAll(Guatemala);
		beerArray.addAll(Hawaii);
		beerArray.addAll(Idaho);
		beerArray.addAll(Illinois);
		beerArray.addAll(India);
		beerArray.addAll(Indiana);
		beerArray.addAll(Ireland);
		beerArray.addAll(Israel);
		beerArray.addAll(Italy);
		beerArray.addAll(Jamaica);
		beerArray.addAll(Japan);
		beerArray.addAll(Kansas);
		beerArray.addAll(Kentucky);
		beerArray.addAll(Kenya);
		beerArray.addAll(Laos);
		beerArray.addAll(Latvia);
		beerArray.addAll(Lebanon);
		beerArray.addAll(Lithuania);
		beerArray.addAll(Louisiana);
		beerArray.addAll(Maine);
		beerArray.addAll(Maryland);
		beerArray.addAll(Massachusetts);
		beerArray.addAll(Mexico);
		beerArray.addAll(Michigan);
		beerArray.addAll(Minnesota);
		beerArray.addAll(Mississippi);
		beerArray.addAll(Missouri);
		beerArray.addAll(Montana);
		beerArray.addAll(Morocco);
		beerArray.addAll(Netherlands);
		beerArray.addAll(NewHampshire);
		beerArray.addAll(NewJersey);
		beerArray.addAll(NewYork);
		beerArray.addAll(NewZealand);
		beerArray.addAll(Nicaragua);
		beerArray.addAll(NorthCarolina);
		beerArray.addAll(Norway);
		beerArray.addAll(Ohio);
		beerArray.addAll(Oregon);
		beerArray.addAll(Pennsylvania);
		beerArray.addAll(Peru);
		beerArray.addAll(Philippines);
		beerArray.addAll(Poland);
		beerArray.addAll(PuertoRico);
		beerArray.addAll(Russia);
		beerArray.addAll(Scotland);
		beerArray.addAll(Serbia);
		beerArray.addAll(Singapore);
		beerArray.addAll(SlovakRepublic);
		beerArray.addAll(SouthCarolina);
		beerArray.addAll(Spain);
		beerArray.addAll(SriLanka);
		beerArray.addAll(Texas);
		beerArray.addAll(Thailand);
		beerArray.addAll(Trinidad);
		beerArray.addAll(Turkey);
		beerArray.addAll(Ukraine);
		beerArray.addAll(Utah);
		beerArray.addAll(Venezuela);
		beerArray.addAll(Vermont);
		beerArray.addAll(Vietnam);
		beerArray.addAll(VirginIslands);
		beerArray.addAll(Virginia);
		beerArray.addAll(Washington);
		beerArray.addAll(Wisconsin);
		
	}
	
	private static void sortArrays() {
		Collections.sort(Alaska, new beercomparer());
		Collections.sort(Argentina, new beercomparer());
		Collections.sort(Arizona, new beercomparer());
		Collections.sort(Australia, new beercomparer());
		Collections.sort(Austria, new beercomparer());
		Collections.sort(Bahamas, new beercomparer());
		Collections.sort(Barbados, new beercomparer());
		Collections.sort(Belgium, new beercomparer());
		Collections.sort(Brazil, new beercomparer());
		Collections.sort(California, new beercomparer());
		Collections.sort(Canada, new beercomparer());
		Collections.sort(China, new beercomparer());
		Collections.sort(Colombia, new beercomparer());
		Collections.sort(Colorado, new beercomparer());
		Collections.sort(CostaRica, new beercomparer());
		Collections.sort(Croatia, new beercomparer());
		Collections.sort(Cyprus, new beercomparer());
		Collections.sort(CzechRepublic, new beercomparer());
		Collections.sort(Delaware, new beercomparer());
		Collections.sort(Denmark, new beercomparer());
		Collections.sort(DistrictofColumbia, new beercomparer());
		Collections.sort(Dominican, new beercomparer());
		Collections.sort(ElSalvador, new beercomparer());
		Collections.sort(England, new beercomparer());
		Collections.sort(Ethiopia, new beercomparer());
		Collections.sort(Finland, new beercomparer());
		Collections.sort(Florida, new beercomparer());
		Collections.sort(France, new beercomparer());
		Collections.sort(Georgia, new beercomparer());
		Collections.sort(Germany, new beercomparer());
		Collections.sort(Greece, new beercomparer());
		Collections.sort(Guatemala, new beercomparer());
		Collections.sort(Hawaii, new beercomparer());
		Collections.sort(Idaho, new beercomparer());
		Collections.sort(Illinois, new beercomparer());
		Collections.sort(India, new beercomparer());
		Collections.sort(Indiana, new beercomparer());
		Collections.sort(Ireland, new beercomparer());
		Collections.sort(Israel, new beercomparer());
		Collections.sort(Italy, new beercomparer());
		Collections.sort(Jamaica, new beercomparer());
		Collections.sort(Japan, new beercomparer());
		Collections.sort(Kansas, new beercomparer());
		Collections.sort(Kentucky, new beercomparer());
		Collections.sort(Kenya, new beercomparer());
		Collections.sort(Laos, new beercomparer());
		Collections.sort(Latvia, new beercomparer());
		Collections.sort(Lebanon, new beercomparer());
		Collections.sort(Lithuania, new beercomparer());
		Collections.sort(Louisiana, new beercomparer());
		Collections.sort(Maine, new beercomparer());
		Collections.sort(Maryland, new beercomparer());
		Collections.sort(Massachusetts, new beercomparer());
		Collections.sort(Mexico, new beercomparer());
		Collections.sort(Michigan, new beercomparer());
		Collections.sort(Minnesota, new beercomparer());
		Collections.sort(Mississippi, new beercomparer());
		Collections.sort(Missouri, new beercomparer());
		Collections.sort(Montana, new beercomparer());
		Collections.sort(Morocco, new beercomparer());
		Collections.sort(Netherlands, new beercomparer());
		Collections.sort(NewHampshire, new beercomparer());
		Collections.sort(NewJersey, new beercomparer());
		Collections.sort(NewYork, new beercomparer());
		Collections.sort(NewZealand, new beercomparer());
		Collections.sort(Nicaragua, new beercomparer());
		Collections.sort(NorthCarolina, new beercomparer());
		Collections.sort(Norway, new beercomparer());
		Collections.sort(Ohio, new beercomparer());
		Collections.sort(Oregon, new beercomparer());
		Collections.sort(Pennsylvania, new beercomparer());
		Collections.sort(Peru, new beercomparer());
		Collections.sort(Philippines, new beercomparer());
		Collections.sort(Poland, new beercomparer());
		Collections.sort(PuertoRico, new beercomparer());
		Collections.sort(Russia, new beercomparer());
		Collections.sort(Scotland, new beercomparer());
		Collections.sort(Serbia, new beercomparer());
		Collections.sort(Singapore, new beercomparer());
		Collections.sort(SlovakRepublic, new beercomparer());
		Collections.sort(SouthCarolina, new beercomparer());
		Collections.sort(Spain, new beercomparer());
		Collections.sort(SriLanka, new beercomparer());
		Collections.sort(Texas, new beercomparer());
		Collections.sort(Thailand, new beercomparer());
		Collections.sort(Trinidad, new beercomparer());
		Collections.sort(Turkey, new beercomparer());
		Collections.sort(Ukraine, new beercomparer());
		Collections.sort(Utah, new beercomparer());
		Collections.sort(Venezuela, new beercomparer());
		Collections.sort(Vermont, new beercomparer());
		Collections.sort(Vietnam, new beercomparer());
		Collections.sort(VirginIslands, new beercomparer());
		Collections.sort(Virginia, new beercomparer());
		Collections.sort(Washington, new beercomparer());
		Collections.sort(Wisconsin, new beercomparer());
	}

	private static void addToOrigin(String origin, beer temp) {
		
		switch (origin)
		{
		case "Alaska":
			if(contains(Alaska, temp))
			{
				Alaska.add(temp);
			}
			break;
		case "Argentina":
			if(contains(Argentina, temp))
			{
				Argentina.add(temp);
			}
			break;
		case "Arizona":
			if(contains(Arizona, temp))
			{
				Arizona.add(temp);
			}
			break;
		case "Australia":
			if(contains(Australia, temp))
			{
				Australia.add(temp);
			}
			break;
		case "Austria":
			if(contains(Austria, temp))
			{
				Austria.add(temp);
			}
			break;
		case "Bahamas":
			if(contains(Bahamas, temp))
			{
				Bahamas.add(temp);
			}
			break;
		case "Barbados":
			if(contains(Barbados, temp))
			{
				Barbados.add(temp);
			}
			break;
		case "Belgium":
			if(contains(Belgium, temp))
			{
				Belgium.add(temp);
			}
			break;
		case "Brazil":
			if(contains(Brazil, temp))
			{
				Brazil.add(temp);
			}
			break;
		case "California":
			if(contains(California, temp))
			{
				California.add(temp);
			}
			break;
		case "Canada":
			if(contains(Canada, temp))
			{
				Canada.add(temp);
			}
			break;
		case "China":
			if(contains(China, temp))
			{
				China.add(temp);
			}
			break;
		case "Colombia":
			if(contains(Colombia, temp))
			{
				Colombia.add(temp);
			}
			break;
		case "Colorado":
			if(contains(Colorado, temp))
			{
				Colorado.add(temp);
			}
			break;
		case "Costa Rica":
			if(contains(CostaRica, temp))
			{
				CostaRica.add(temp);
			}
			break;
		case "Croatia":
			if(contains(Croatia, temp))
			{
				Croatia.add(temp);
			}
			break;
		case "Cyprus":
			if(contains(Cyprus, temp))
			{
				Cyprus.add(temp);
			}
			break;
		case "Czech Republic":
			if(contains(CzechRepublic, temp))
			{
				CzechRepublic.add(temp);
			}
			break;
		case "Delaware":
			if(contains(Delaware, temp))
			{
				Delaware.add(temp);
			}
			break;
		case "Denmark":
			if(contains(Denmark, temp))
			{
				Denmark.add(temp);
			}
			break;
		case "District of Columbia":
			if(contains(DistrictofColumbia, temp))
			{
				DistrictofColumbia.add(temp);
			}
			break;
		case "Dominican":
			if(contains(Dominican, temp))
			{
				Dominican.add(temp);
			}
			break;
		case "El Salvador":
			if(contains(ElSalvador, temp))
			{
				ElSalvador.add(temp);
			}
			break;
		case "England":
			if(contains(England, temp))
			{
				England.add(temp);
			}
			break;
		case "Ethiopia":
			if(contains(Ethiopia, temp))
			{
				Ethiopia.add(temp);
			}
			break;
		case "Finland":
			if(contains(Finland, temp))
			{
				Finland.add(temp);
			}
			break;
		case "Florida":
			if(contains(Florida, temp))
			{
				Florida.add(temp);
			}
			break;
		case "France":
			if(contains(France, temp))
			{
				France.add(temp);
			}
			break;
		case "Georgia":
			if(contains(Georgia, temp))
			{
				Georgia.add(temp);
			}
			break;
		case "Germany":
			if(contains(Germany, temp))
			{
				Germany.add(temp);
			}
			break;
		case "Greece":
			if(contains(Greece, temp))
			{
				Greece.add(temp);
			}
			break;
		case "Guatemala":
			if(contains(Guatemala, temp))
			{
				Guatemala.add(temp);
			}
			break;
		case "Hawaii":
			if(contains(Hawaii, temp))
			{
				Hawaii.add(temp);
			}
			break;
		case "Idaho":
			if(contains(Idaho, temp))
			{
				Idaho.add(temp);
			}
			break;
		case "Illinois":
			if(contains(Illinois, temp))
			{
				Illinois.add(temp);
			}
			break;
		case "India":
			if(contains(India, temp))
			{
				India.add(temp);
			}
			break;
		case "Indiana":
			if(contains(Indiana, temp))
			{
				Indiana.add(temp);
			}
			break;
		case "Ireland":
			if(contains(Ireland, temp))
			{
				Ireland.add(temp);
			}
			break;
		case "Israel":
			if(contains(Israel, temp))
			{
				Israel.add(temp);
			}
			break;
		case "Italy":
			if(contains(Italy, temp))
			{
				Italy.add(temp);
			}
			break;
		case "Jamaica":
			if(contains(Jamaica, temp))
			{
				Jamaica.add(temp);
			}
			break;
		case "Japan":
			if(contains(Japan, temp))
			{
				Japan.add(temp);
			}
			break;
		case "Kansas":
			if(contains(Kansas, temp))
			{
				Kansas.add(temp);
			}
			break;
		case "Kentucky":
			if(contains(Kentucky, temp))
			{
				Kentucky.add(temp);
			}
			break;
		case "Kenya":
			if(contains(Kenya, temp))
			{
				Kenya.add(temp);
			}
			break;
		case "Laos":
			if(contains(Laos, temp))
			{
				Laos.add(temp);
			}
			break;
		case "Latvia":
			if(contains(Latvia, temp))
			{
				Latvia.add(temp);
			}
			break;
		case "Lebanon":
			if(contains(Lebanon, temp))
			{
				Lebanon.add(temp);
			}
			break;
		case "Lithuania":
			if(contains(Lithuania, temp))
			{
				Lithuania.add(temp);
			}
			break;
		case "Louisiana":
			if(contains(Louisiana, temp))
			{
				Louisiana.add(temp);
			}
			break;
		case "Maine":
			if(contains(Maine, temp))
			{
				Maine.add(temp);
			}
			break;
		case "Maryland":
			if(contains(Maryland, temp))
			{
				Maryland.add(temp);
			}
			break;
		case "Massachusetts":
			if(contains(Massachusetts, temp))
			{
				Massachusetts.add(temp);
			}
			break;
		case "Mexico":
			if(contains(Mexico, temp))
			{
				Mexico.add(temp);
			}
			break;
		case "Michigan":
			if(contains(Michigan, temp))
			{
				Michigan.add(temp);
			}
			break;
		case "Minnesota":
			if(contains(Minnesota, temp))
			{
				Minnesota.add(temp);
			}
			break;
		case "Mississippi":
			if(contains(Mississippi, temp))
			{
				Mississippi.add(temp);
			}
			break;
		case "Missouri":
			if(contains(Missouri, temp))
			{
				Missouri.add(temp);
			}
			break;
		case "Montana":
			if(contains(Montana, temp))
			{
				Montana.add(temp);
			}
			break;
		case "Morocco":
			if(contains(Morocco, temp))
			{
				Morocco.add(temp);
			}
			break;
		case "Netherlands":
			if(contains(Netherlands, temp))
			{
				Netherlands.add(temp);
			}
			break;
		case "New Hampshire":
			if(contains(NewHampshire, temp))
			{
				NewHampshire.add(temp);
			}
			break;
		case "New Jersey":
			if(contains(NewJersey, temp))
			{
				NewJersey.add(temp);
			}
			break;
		case "New York":
			if(contains(NewYork, temp))
			{
				NewYork.add(temp);
			}
			break;
		case "New Zealand":
			if(contains(NewZealand, temp))
			{
				NewZealand.add(temp);
			}
			break;
		case "Nicaragua":
			if(contains(Nicaragua, temp))
			{
				Nicaragua.add(temp);
			}
			break;
		case "North Carolina":
			if(contains(NorthCarolina, temp))
			{
				NorthCarolina.add(temp);
			}
			break;
		case "Norway":
			if(contains(Norway, temp))
			{
				Norway.add(temp);
			}
			break;
		case "Ohio":
			if(contains(Ohio, temp))
			{
				Ohio.add(temp);
			}
			break;
		case "Oregon":
			if(contains(Oregon, temp))
			{
				Oregon.add(temp);
			}
			break;
		case "Pennsylvania":
			if(contains(Pennsylvania, temp))
			{
				Pennsylvania.add(temp);
			}
			break;
		case "Peru":
			if(contains(Peru, temp))
			{
				Peru.add(temp);
			}
			break;
		case "Philippines":
			if(contains(Philippines, temp))
			{
				Philippines.add(temp);
			}
			break;
		case "Poland":
			if(contains(Poland, temp))
			{
				Poland.add(temp);
			}
			break;
		case "Puerto Rico":
			if(contains(PuertoRico, temp))
			{
				PuertoRico.add(temp);
			}
			break;
		case "Russia":
			if(contains(Russia, temp))
			{
				Russia.add(temp);
			}
			break;
		case "Scotland":
			if(contains(Scotland, temp))
			{
				Scotland.add(temp);
			}
			break;
		case "Serbia":
			if(contains(Serbia, temp))
			{
				Serbia.add(temp);
			}
			break;
		case "Singapore":
			if(contains(Singapore, temp))
			{
				Singapore.add(temp);
			}
			break;
		case "Slovak Republic":
			if(contains(SlovakRepublic, temp))
			{
				SlovakRepublic.add(temp);
			}
			break;
		case "South Carolina":
			if(contains(SouthCarolina, temp))
			{
				SouthCarolina.add(temp);
			}
			break;
		case "Spain":
			if(contains(Spain, temp))
			{
				Spain.add(temp);
			}
			break;
		case "Sri Lanka":
			if(contains(SriLanka, temp))
			{
				SriLanka.add(temp);
			}
			break;
		case "Texas":
			if(contains(Texas, temp))
			{
				Texas.add(temp);
			}
			break;
		case "Thailand":
			if(contains(Thailand, temp))
			{
				Thailand.add(temp);
			}
			break;
		case "Trinidad":
			if(contains(Trinidad, temp))
			{
				Trinidad.add(temp);
			}
			break;
		case "Turkey":
			if(contains(Turkey, temp))
			{
				Turkey.add(temp);
			}
			break;
		case "Ukraine":
			if(contains(Ukraine, temp))
			{
				Ukraine.add(temp);
			}
			break;
		case "Utah":
			if(contains(Utah, temp))
			{
				Utah.add(temp);
			}
			break;
		case "Venezuela":
			if(contains(Venezuela, temp))
			{
				Venezuela.add(temp);
			}
			break;
		case "Vermont":
			if(contains(Vermont, temp))
			{
				Vermont.add(temp);
			}
			break;
		case "Vietnam":
			if(contains(Vietnam, temp))
			{
				Vietnam.add(temp);
			}
			break;
		case "Virgin Islands":
			if(contains(VirginIslands, temp))
			{
				VirginIslands.add(temp);
			}
			break;
		case "Virginia":
			if(contains(Virginia, temp))
			{
				Virginia.add(temp);
			}
			break;
		case "Washington":
			if(contains(Washington, temp))
			{
				Washington.add(temp);
			}
			break;
		case "Wisconsin":
			if(contains(Wisconsin, temp))
			{
				Wisconsin.add(temp);
			}
			break;
		}
		
	}

	private static boolean contains(ArrayList<beer> place, beer temp) {

		for(int i = 0; i < place.size(); i++)
		{
			if(place.get(i).beername.equals(temp.beername))
			{
				return false;
			}
		}
		
		return true;
		
	}

	private static void initializeArrays() {
		Alaska = new ArrayList<beer>();
		Argentina = new ArrayList<beer>();
		Arizona = new ArrayList<beer>();
		Australia = new ArrayList<beer>();
		Austria = new ArrayList<beer>();
		Bahamas = new ArrayList<beer>();
		Barbados = new ArrayList<beer>();
		Belgium = new ArrayList<beer>();
		Brazil = new ArrayList<beer>();
		California = new ArrayList<beer>();
		Canada = new ArrayList<beer>();
		China = new ArrayList<beer>();
		Colombia = new ArrayList<beer>();
		Colorado = new ArrayList<beer>();
		CostaRica = new ArrayList<beer>();
		Croatia = new ArrayList<beer>();
		Cyprus = new ArrayList<beer>();
		CzechRepublic = new ArrayList<beer>();
		Delaware = new ArrayList<beer>();
		Denmark = new ArrayList<beer>();
		DistrictofColumbia = new ArrayList<beer>();
		Dominican = new ArrayList<beer>();
		ElSalvador = new ArrayList<beer>();
		England = new ArrayList<beer>();
		Ethiopia = new ArrayList<beer>();
		Finland = new ArrayList<beer>();
		Florida = new ArrayList<beer>();
		France = new ArrayList<beer>();
		Georgia = new ArrayList<beer>();
		Germany = new ArrayList<beer>();
		Greece = new ArrayList<beer>();
		Guatemala = new ArrayList<beer>();
		Hawaii = new ArrayList<beer>();
		Idaho = new ArrayList<beer>();
		Illinois = new ArrayList<beer>();
		India = new ArrayList<beer>();
		Indiana = new ArrayList<beer>();
		Ireland = new ArrayList<beer>();
		Israel = new ArrayList<beer>();
		Italy = new ArrayList<beer>();
		Jamaica = new ArrayList<beer>();
		Japan = new ArrayList<beer>();
		Kansas = new ArrayList<beer>();
		Kentucky = new ArrayList<beer>();
		Kenya = new ArrayList<beer>();
		Laos = new ArrayList<beer>();
		Latvia = new ArrayList<beer>();
		Lebanon = new ArrayList<beer>();
		Lithuania = new ArrayList<beer>();
		Louisiana = new ArrayList<beer>();
		Maine = new ArrayList<beer>();
		Maryland = new ArrayList<beer>();
		Massachusetts = new ArrayList<beer>();
		Mexico = new ArrayList<beer>();
		Michigan = new ArrayList<beer>();
		Minnesota = new ArrayList<beer>();
		Mississippi = new ArrayList<beer>();
		Missouri = new ArrayList<beer>();
		Montana = new ArrayList<beer>();
		Morocco = new ArrayList<beer>();
		Netherlands = new ArrayList<beer>();
		NewHampshire = new ArrayList<beer>();
		NewJersey = new ArrayList<beer>();
		NewYork = new ArrayList<beer>();
		NewZealand = new ArrayList<beer>();
		Nicaragua = new ArrayList<beer>();
		NorthCarolina = new ArrayList<beer>();
		Norway = new ArrayList<beer>();
		Ohio = new ArrayList<beer>();
		Oregon = new ArrayList<beer>();
		Pennsylvania = new ArrayList<beer>();
		Peru = new ArrayList<beer>();
		Philippines = new ArrayList<beer>();
		Poland = new ArrayList<beer>();
		PuertoRico = new ArrayList<beer>();
		Russia = new ArrayList<beer>();
		Scotland = new ArrayList<beer>();
		Serbia = new ArrayList<beer>();
		Singapore = new ArrayList<beer>();
		SlovakRepublic = new ArrayList<beer>();
		SouthCarolina = new ArrayList<beer>();
		Spain = new ArrayList<beer>();
		SriLanka = new ArrayList<beer>();
		Texas = new ArrayList<beer>();
		Thailand = new ArrayList<beer>();
		Trinidad = new ArrayList<beer>();
		Turkey = new ArrayList<beer>();
		Ukraine = new ArrayList<beer>();
		Utah = new ArrayList<beer>();
		Venezuela = new ArrayList<beer>();
		Vermont = new ArrayList<beer>();
		Vietnam = new ArrayList<beer>();
		VirginIslands = new ArrayList<beer>();
		Virginia = new ArrayList<beer>();
		Washington = new ArrayList<beer>();
		Wisconsin = new ArrayList<beer>();
	}
	
}
