import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class randomTuple {

	/**
	 * @param args
	 */
	String BarID;
	
	int PersonID;
	
	String barType;
	
	String ageRange;
	
	String city;
	
	String demo;
	
	int expense;
	
	double staffNum;
	
	int size;
	
	double CustomerRating;
	
	Boolean kitchen;
	
	String[] adj;
	
	String[] noun;
	
	String[] listOfStreets = {"Court", "Street", "Place", "Avenue", "Square", "Road", "Terrace", "Drive", 
			"Parkway", "Lane", "Loop", "Plaza", "Path", "Walk", "Boulevard", "Alley", "Hwy", "Courtland", 
			"Circle"};
	
	String[] listOfCities = {"New York, New York","Los Angeles, California","Chicago, Illinois",
			"Houston, Texas", "Philadelphia, Pennsylvania","Phoenix, Arizona","San Antonio, Texas",
			"San Diego, California","Dallas, Texas","San Jose, California","Austin, Texas",
			"Jacksonville, Florida","Indianapolis, Indiana","San Francisco, California","Columbus, Ohio",
			"Fort Worth, Texas","Charlotte, North Carolina","Detroit, Michigan","El Paso, Texas",
			"Memphis, Tennessee","Boston, Massachusetts","Seattle, Washington","Denver, Colorado",
			"Washington, District of Columbia", "Nashville, Tennessee","Baltimore, Maryland",
			"Louisville, Kentucky","Portland, Oregon", "Oklahoma City, Oklahoma","Milwaukee, Wisconsin",
			"Las Vegas, Nevada","Albuquerque, New Mexico","Tucson, Arizona","Fresno, California",
			"Sacramento, California","Long Beach, California","Kansas City, Missouri","Mesa, Arizona",
			"Virginia Beach, Virginia","Atlanta, Georgia","Colorado Springs, Colorado",
			"Raleigh, North Carolina","Omaha, Nebraska","Miami, Florida","Oakland, California",
			"Tulsa, Oklahoma","Minneapolis, Minnesota","Cleveland, Ohio","Wichita, Kansas",
			"Arlington, Texas","New Orleans, Louisiana","Bakersfield, California","Tampa, Florida",
			"Honolulu, Hawaii","Anaheim, California","Aurora, Colorado","Santa Ana, California",
			"St. Louis, Missouri","Riverside, California","Corpus Christi, Texas",
			"Pittsburgh, Pennsylvania","Lexington, Kentucky","Anchorage, Alaska","Stockton, California",
			"Cincinnati, Ohio","Saint Paul, Minnesota","Toledo, Ohio","Newark, New Jersey",
			"Greensboro, North Carolina","Plano, Texas","Henderson, Nevada","Lincoln, Nebraska",
			"Buffalo, New York","Fort Wayne, Indiana","Jersey City, New Jersey","Chula Vista, California",
			"Orlando, Florida","St. Petersburg, Florida","Norfolk, Virginia","Chandler, Arizona",
			"Laredo, Texas","Madison, Wisconsin","Durham, North Carolina","Lubbock, Texas",
			"Winston–Salem, North Carolina","Garland, Texas","Glendale, Arizona","Hialeah, Florida",
			"Reno, Nevada","Baton Rouge, Louisiana","Irvine, California","Chesapeake, Virginia",
			"Irving, Texas","Scottsdale, Arizona","North Las Vegas, Nevada","Fremont, California",
			"Gilbert, Arizona","San Bernardino, California","Boise, Idaho","Birmingham, Alabama"};
	
	String[] word;
	
	String[] firstname;
	
	String[] lastname;
	
	int skillTemp;
	
	public randomTuple() {
		this.adj = new String[100];
		this.noun = new String[100];
		this.word = new String[42972];
		this.firstname = new String[5494];
		this.lastname = new String[88799];
		
		try {
			fillArray(adj, "barAdj.txt");
			fillArray(noun, "barNoun.txt");
			fillArray(word, "capWord.txt");
			fillArray(firstname, "firstnames.txt");
			fillArray(lastname, "lastnames.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("fillArray fail");
			e.printStackTrace();
		}
		
		this.BarID = "10000";
		this.PersonID = 10000;
	}
	
	public String returnBarsTuple() 
	{
		String out = "('" + this.getID() + "', '" + this.getName(this.adj, this.noun) + "', '" + 
				this.getType() + "', '" + this.getAddress() + "', '" + 
				this.getCityState()  + "', '" + this.getNumber() + "', " + this.getKitchen() + 
				", '" + this.getYear() + "')";
		return out;
	}
	
	public String returnBarStatisticsTuple()
	{
		String out = "('" + this.BarID + "', '" + this.getCustomerRating() + "', '" + 
				this.getSize() + "', '" + this.getExpenseRating() + "', '" + 
				this.getStaffNum()  + "', '" + this.getAgeRange() + "', '" + this.getDemographic() +
				"')";
		return out;
	}
	
	public String returnStaff()
	{
		String out = "('" + this.getPersonID() + "', '" + this.getFirstName() + "', '" + 
				this.getLastName() + "', '" + this.getAge() + "', '" + 
				this.getSex()  + "', '" + this.getNumber() + "', '" + this.BarID + "', '" + 
				this.getAddress() + "', '" + this.getSkillRating() + "', ";
		return out;
	}

	public double getRevenue(double skillmulti) {
		double revenue = 0;
		
		if(this.city.contains("Vegas"))
		{
			revenue += 10;
		}
		else
		{
			switch (this.city)
			{
			case "New York, New York":
			case "Los Angeles, California":
			case "Chicago, Illinois":
			case "New Orleans, Louisiana":
			case "San Francisco, California":
			case "Miami, Florida": revenue +=10; break;
			}
		}
		
		switch (this.ageRange)
		{
		case "18-35": revenue += 10; break;
		case "35-50": revenue += 5; break;
		default: revenue += 2.5; break;
		}
		
		if(this.demo.equals("Tourists") || this.demo.equals("Upscale"))
		{
			if(this.expense > 3)
			{
				revenue += 10;
			}
			else if(this.expense == 3)
			{
				revenue += 5;
			}
		}
		else if(this.demo.equals("White Collar") || this.demo.equals("Middle Class"))
		{
			if(this.expense == 2 || this.expense == 3)
			{
				revenue += 5;
			}
			else if(this.expense == 4)
			{
				revenue += 2.5;
			}
		}
		else
		{
			if(this.expense < 3)
			{
				revenue += 2.5;
			}
			else if(this.expense == 3)
			{
				revenue += 1.25;
			}
		}
		
		if(this.CustomerRating == 5.0){revenue += 10;}
		else if(this.CustomerRating == 4.5){revenue += 8;}
		else if(this.CustomerRating == 4.0){revenue += 6;}
		else if(this.CustomerRating == 3.5){revenue += 4;}
		else if(this.CustomerRating == 3.0){revenue += 2;}
		else if(this.CustomerRating == 2.0){revenue -= 2;}
		else if(this.CustomerRating == 1.5){revenue -= 4;}
		else if(this.CustomerRating == 1.0){revenue -= 6;}
		else if(this.CustomerRating == 0.5){revenue -= 8;}
		else if(this.CustomerRating == 0.0){revenue -= 10;}
		
		if(!this.barType.equals("Nightclub") && !this.barType.equals("Lounge"))
		{
			revenue = (revenue*2)/3;
		}
		
		revenue *= (skillmulti/5);
			
		return revenue;
	}
	
	public String getJob()
	{
		int joblotto = this.randomWithRange(1, 7);
		
		switch (joblotto)
		{
		case 1: return "Owner";
		case 2: return "Manager";
		case 3: return "Bartender";
		case 4: return "Cook";
		case 5: return "Server";
		case 6: return "Barback";
		default: return "Bouncer";
		}
	}
	
	public double getWage()
	{
		switch (this.skillTemp)
		{
		case 10: return (15+(this.randomWithRange(0, 199)/100));
		case 9: return (13+(this.randomWithRange(0, 199)/100));
		case 8: return (11+(this.randomWithRange(0, 199)/100));
		case 7: return (9+(this.randomWithRange(0, 199)/100));
		case 6: return (7+(this.randomWithRange(0, 199)/100));
		default: return (5+(this.randomWithRange(0, 199)/100));
		}
	}
	
	public int getSkillRating()
	{
		this.skillTemp = (this.randomWithRange(1, 6) + this.randomWithRange(1, 6) - 2);
		
		while(this.skillTemp == 0)
		{
			this.skillTemp = (this.randomWithRange(1, 6) + this.randomWithRange(1, 6) - 2);
		}
		return this.skillTemp;
	}
	
	public char getSex()
	{
		if(this.randomWithRange(0, 1) == 0)
		{
			return 'F';
		}
		else
		{
			return 'M';
		}
	}
	
	public int getAge()
	{
		if(this.ageRange.equals("18-35"))
		{
			return this.randomWithRange(18, 30);
		}
		else if(this.ageRange.equals("35-50"))
		{
			return this.randomWithRange(30, 45);
		}
		else
		{
			return this.randomWithRange(25, 45);
		}
	}
	
	public String getLastName()
	{
		return this.lastname[this.randomWithRange(0, this.lastname.length-1)];
	}
	
	public String getFirstName()
	{
		return this.firstname[this.randomWithRange(0, this.firstname.length-1)];
	}
	
	public int getPersonID()
	{
		this.PersonID++;
		return this.PersonID;
	}
	
	public double getCustomerRating()
	{
		int randRating = this.randomWithRange(1, 11);
		
		switch(randRating)
		{
		case 1: this.CustomerRating = 0.0; break;
		case 2: this.CustomerRating = 0.5; break;
		case 3: this.CustomerRating = 1.0; break;
		case 4: this.CustomerRating = 1.5; break;
		case 5: this.CustomerRating = 2.0; break;
		case 6: this.CustomerRating = 2.5; break;
		case 7: this.CustomerRating = 3.0; break;
		case 8: this.CustomerRating = 3.5; break;
		case 9: this.CustomerRating = 4.0; break;
		case 10: this.CustomerRating = 4.5; break;
		default: this.CustomerRating = 5.0; break;
		}
		
		return this.CustomerRating;
	}
	
	public double getStaffNum()
	{
		this.staffNum = ((this.size/1000)*4) + this.randomWithRange(-10, 10);
		if(this.staffNum < 10)
		{
			this.staffNum = 10;
		}
		return this.staffNum;
	}
	
	public int getSize()
	{
		if(this.barType.equals("Nightclub"))
		{
			this.size = (this.randomWithRange(10, 60)) * 1000;
			return this.size;
		}
		else
		{
			this.size = (this.randomWithRange(1, 10)) * 1000;
			return this.size;
		}
	}
	
	public int getExpenseRating()
	{
		this.expense = this.randomWithRange(1, 5);
		return this.expense;
		//5 being most expensive and 1 being cheapest
	}
	
	public String getDemographic()
	{
		int randDemo = 0;
		
		if(this.ageRange.equals("18-35"))
		{
			randDemo = this.randomWithRange(1, 4);
			switch (randDemo)
			{
			case 1: this.demo = "Tourists"; break;
			case 2: this.demo = "Students"; break;
			case 3: this.demo = "Locals"; break;
			case 4: this.demo = "Niche"; break;
			}
		}
		else if (this.ageRange.equals("35-50"))
		{
			randDemo = this.randomWithRange(1, 6);
			switch (randDemo)
			{
			case 1: this.demo = "Tourists"; break;
			case 2: this.demo = "Upscale"; break;
			case 3: this.demo = "White Collar"; break;
			case 4: this.demo = "Middle Class"; break;
			case 5: this.demo = "Locals"; break;
			case 6: this.demo = "Niche"; break;
			}
		}
		else
		{
			randDemo = this.randomWithRange(1, 6);
			switch (randDemo)
			{
			case 1: this.demo = "Tourists"; break;
			case 2: this.demo = "Upscale"; break;
			case 3: this.demo = "White Collar"; break;
			case 4: this.demo = "Middle Class"; break;
			case 5: this.demo = "Locals"; break;
			case 6: this.demo = "Niche"; break;
			}
		}
		return this.demo;
	}
	
	public String getAgeRange()
	{
		int randAge = this.randomWithRange(1, 10);
		
		if(randAge == 10)
		{
			this.ageRange = "50+";
		}
		else if (7 <= randAge && randAge <= 9)
		{
			this.ageRange = "35-50";
		}
		else
		{
			this.ageRange = "18-35";
		}
		
		return this.ageRange;
	}
	
	public String getCityState() {
		this.city = listOfCities[this.randomWithRange(0, listOfCities.length-1)];
		return this.city;
	}

	public String getAddress() {
		String out = Integer.toString(this.randomWithRange(10, 9999)) + " " + 
				this.word[this.randomWithRange(0, this.word.length-1)] + " " + 
				this.listOfStreets[this.randomWithRange(0, this.listOfStreets.length-1)];
		return out;
	}

	public String getYear() {
		
		int op = randomWithRange(1, 100);
		
		if (op <= 5)
		{
			return Integer.toString(this.randomWithRange(1800, 1849));
		}
		else if (op > 5 && op <= 15)
		{
			return Integer.toString(this.randomWithRange(1850, 1900));
		}
		else if (op > 15 && op <= 35)
		{
			return Integer.toString(this.randomWithRange(1901, 1950));
		}
		
		return Integer.toString(this.randomWithRange(1951, 2010));
	}

	public Boolean getKitchen() {
		if(randomWithRange(0, 1) == 0)
		{
			this.kitchen = true;
		}
		else
		{
			this.kitchen = false;
		}
		return this.kitchen;
	}

	public String getID()
	{
		int temp = Integer.parseInt(BarID);
		temp++;
		this.BarID = Integer.toString(temp);
		return BarID;
	}
	
	public String getType() {
		int randType = this.randomWithRange(1, 100);
		
		if(1 <= randType && randType <= 25)
		{
			this.barType = "Nightclub";
		}
		else if(26 <= randType && randType <= 35)
		{
			this.barType = "Lounge";
		}
		else if(36 <= randType && randType <= 43)
		{
			this.barType = "Tourist Bar";
		}
		else if(44 <= randType && randType <= 51)
		{
			this.barType = "Hotel Bar";
		}
		else if(52 <= randType && randType <= 61)
		{
			this.barType = "Restaurant Bar";
		}
		else if(62 <= randType && randType <= 69)
		{
			this.barType = "Sports Bar";
		}
		else if(70 <= randType && randType <= 72)
		{
			this.barType = "College Bar";
		}
		else if(73 <= randType && randType <= 75)
		{
			this.barType = "Comedy Bar";
		}
		else if(76 <= randType && randType <= 85)
		{
			this.barType = "Pub";
		}
		else if(86 <= randType && randType <= 95)
		{
			this.barType = "Tavern";
		}
		else if(96 <= randType && randType <= 98)
		{
			this.barType = "Neighborhood Bar";
		}
		else if(randType == 99)
		{
			this.barType = "Billards Bar";
		}
		else if(randType == 100)
		{
			this.barType = "Karoke Bar";
		}
		
		return this.barType;
	}

	public String getName(String[] adj, String[] noun) {
		String name = adj[this.randomWithRange(0, 99)] + " " + noun[this.randomWithRange(0, 99)];
		return name;
	}
	
	public String getNumber(){
		String temp = "(";
		//(000)000-0000
		//0123456789111
		//          012
		for(int i = 0; i < 12; i++)
		{
			if(temp.length() == 4)
			{
				temp += ")";
			}
			else if(temp.length() == 8)
			{
				temp += "-";
			}
			else
			{
				temp += Integer.toString(randomWithRange(0, 9));
			}
		}
		return temp;
	}

	private void fillArray(String[] arr, String textfile) throws IOException {
		
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
	
	private int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

}
