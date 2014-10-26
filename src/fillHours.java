import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class fillHours {


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
		ResultSet bars = null;
		String insertHours = "INSERT INTO 336bars.Hours (BarID, MonOpen, MonClose, TuesOpen, TuesClose, WedOpen, WedClose, ThursOpen, ThursClose, FriOpen, FriClose, SatOpen, SatClose, SunOpen, SunClose)" +
				"VALUES ";
		stmt.executeUpdate("delete from 336bars.Hours");
		
		for(int barid = 10001; barid < 12501; barid++)
		{
			sql = "select BarType from 336bars.Bars where BarID = '" + barid + "'";
			bars = rsstmt.executeQuery(sql);
			bars.next();
			String barType = bars.getString("BarType");
			
			int opennum = 0;
			int closenum = 0;
			if(nightbar(barType))
			{
				opennum = randomWithRange(20, 22);
				closenum = randomWithRange(1, 4);
				if((24 + closenum) < (opennum+4))
				{
					
					closenum = opennum + 4 - 24;
				}
			}
			else
			{
				opennum = randomWithRange(11, 13);
				closenum = randomWithRange(1, 2);
			}
			
			String open = Integer.toString(opennum) + ":00";
			String close = Integer.toString(closenum) + ":00";
			
			String hours = "('" + barid + "', '"+open+"', '"+close+"', '"+open+ "', '"+close+"', '"+
					open+ "', '"+close+"', '"+open+ "', '"+close+"', '"+open+ "', '"+close+"', '"+
					open+ "', '"+close+"', '"+open+ "', '"+close + "')";
			sql = insertHours + hours;
			
			stmt.executeUpdate(sql);
			
			
			System.out.println(barid);
		}

		bars.close();
		rsstmt.close();
		stmt.close();
		conn.close();
		System.out.println("Finished");
	}

	private static boolean nightbar(String barType) {
		
		switch (barType)
		{
		case "Nightclub":
		case "Lounge":
		case "Tourist Bar":
		case "College Bar":
		case "Comedy Bar":
		case "Karoke Bar": return true;
		}
		return false;
	}
	
	private static int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
}


