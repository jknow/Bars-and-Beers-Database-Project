import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class fillSells {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException {
		String username = "csuser";
		String password = "csdaed1b";
		Connection conn = DriverManager.getConnection("jdbc:mysql://cs336-21.cs.rutgers.edu:3306/336bars", username, password);
		//jdbc:sqlserver://server:port;DatabaseName=dbname
		Statement stmt = conn.createStatement();
		Statement rsstmt = conn.createStatement();
		String sql = "";
		ResultSet beers = null;
		stmt.executeUpdate("delete from 336bars.Sells");
		
		for(int barid = 10001; barid < 12501; barid++)
		{
			sql = "select Size, ExpenseRating from 336bars.BarStatistics where BarID = '" + barid + "'";
			beers = stmt.executeQuery(sql);
			beers.next();
			int barsize  = beers.getInt("Size");
			int expense = beers.getInt("ExpenseRating");
			int beernum = barsize/1000;
			if(beernum < 10)
			{
				beernum = 10;
			}
			
			
			beers = rsstmt.executeQuery("SELECT * FROM Beers ORDER BY RAND() LIMIT " + beernum);
			beers.next();
			for(int i = 0; i < beernum; i++)
			{
				String beername = beers.getString("BeerName");
				if(beername.contains("'"))
				{
					beername = formatapostrophies(beername);
				}
				double beerRating = beers.getDouble("QualityRating");
				double price = beerRating + expense;
				price = price*2;
				if(price < 1)
				{
					price = 1;
				}
				String insertsells = "INSERT INTO 336bars.Sells (BarID, Beer, Price)" +
						"VALUES ";
				String goingPrice = Double.toString(price) + "0";
				sql = insertsells + "('" + barid + "', '" + beername + "', '" + goingPrice + "')";
				stmt.executeUpdate(sql);
				beers.next();
			}

			System.out.println(barid);
		}

		beers.close();
		rsstmt.close();
		stmt.close();
		conn.close();
		System.out.println("Finished");
	}

	private static String formatapostrophies(String beername) {

		int lastIndex = 0;
		while(lastIndex != -1)
		{
			lastIndex = beername.indexOf("'", lastIndex);

			if( lastIndex != -1)
			{
				beername = beername.substring(0, lastIndex) + 
						"'" + beername.substring(lastIndex);
				lastIndex += 2;
			}
		}
		return beername;
	}

}
