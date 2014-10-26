

public class beer {

	String beername;
	
	String beertype;
	
	int beersizeoz;
	
	double beerabvpercent;
	
	String placeoforigin;
	
	public beer(String name, String type, int size, double abv, String origin)
	{
		if(name.indexOf("'") != -1)
		{
			this.beername = name.substring(0, name.indexOf("'")) + "'" + name.substring(name.indexOf("'"));
		}
		else
		{
			this.beername = name;
		}
		this.beertype = type;
		this.beersizeoz = size;
		this.beerabvpercent = abv;
		this.placeoforigin = origin;
	}
	
	public double getRating()
	{
		double out = 0;
		
		int randRating = this.randomWithRange(1, 11);
		
		switch(randRating)
		{
		case 1: out = 0.0; break;
		case 2: out = 0.5; break;
		case 3: out = 1.0; break;
		case 4: out = 1.5; break;
		case 5: out = 2.0; break;
		case 6: out = 2.5; break;
		case 7: out = 3.0; break;
		case 8: out = 3.5; break;
		case 9: out = 4.0; break;
		case 10: out = 4.5; break;
		default: out = 5.0; break;
		}
		
		return out;
	}
	
	public String testprint()
	{
		String out ="('" + this.beername + "', '" + this.beertype + "', '" + this.beersizeoz + 
				"', '" + this.beerabvpercent + "', '" + this.placeoforigin + "')";
		return out;
	}
	
	public String getBeerTuple()
	{
		String out ="('" + this.beername + "', '" + this.beertype + "', '" + this.beersizeoz + 
				"', '" + this.beerabvpercent + "', '" + this.placeoforigin + "', '" + this.getRating() + "')";
		return out;
	}
	
	private int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
}
