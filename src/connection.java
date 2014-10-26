import java.sql.*;


public class connection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String username = "csuser";
			String password = "csdaed1b";
			Connection conn = DriverManager.getConnection("jdbc:mysql://cs336-21.cs.rutgers.edu:3306/336bars", username, password);
			//jdbc:sqlserver://server:port;DatabaseName=dbname
			Statement stmt = conn.createStatement();
			String BarsInsert = "INSERT INTO 336bars.Bars (BarID, BarName, BarType, Address, CityState, BarPhoneNumber, Kitchen, OpeningYear)" +
					"VALUES ";
			String BarStatisticsInsert = "INSERT INTO 336bars.BarStatistics (BarID, CustomerRating, Size, ExpenseRating, NumberofStaff, AgeRange, Demographic)" +
					"VALUES ";
			String sql = "";
			double skillmulti = 1;
			randomTuple fillBars = new randomTuple();
			String holder = null;
			double revenueperyear = 0;
			
			for (int i = 0; i < 2500; i++)
			{
				holder = fillBars.returnBarsTuple();
				sql = BarsInsert + holder;
				System.out.println(fillBars.BarID);
				stmt.executeUpdate(sql);
				
				holder = fillBars.returnBarStatisticsTuple();
				sql = BarStatisticsInsert + holder;
				stmt.executeUpdate(sql);
				
				skillmulti = (staffSQL(fillBars, stmt, sql, holder)/(fillBars.staffNum));
				
				revenueperyear = fillBars.getRevenue(skillmulti);
				
				revenueperyear = Math.pow(revenueperyear,3)/800;
				
				stmt.executeUpdate("UPDATE 336bars.BarStatistics SET RevenuePerYear=" + revenueperyear +
						" WHERE BarID=" + fillBars.BarID);
			}
			
			stmt.close();
			conn.close();
			System.out.println("Finished");
		} catch (ClassNotFoundException e1) {
			System.err.println("Class.forName(com.mysql.jdbc.Driver) failure");
			e1.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Database Error");
			e.printStackTrace();
		}
		
	}

	private static double staffSQL(randomTuple fillBars, Statement stmt, String sql, String holder) {
		int totalskill = 0;
		double sizemulti = 1;
		String StaffInsert = "INSERT INTO 336bars.Staff (PersonID, FirstName, LastName, Age, Sex, PhoneNumber, BarID, Address, SkillRating, Wage, Job)" +
				"VALUES ";
		double lstaffnum = fillBars.staffNum;
		
		if(fillBars.kitchen)
		{
			if(lstaffnum <= 50)
			{
				//entering owner
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'saleried', 'Owner')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				//entering manager
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'saleried', 'Manager')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				//entering 3 bartenders
				for(int i = 0; i < 3; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bartender')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering cook
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Cook')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				//entering servers
				for(int i = 0; i < 2; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Servers')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering barback
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Barback')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				//entering bouncer
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bouncer')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				while(lstaffnum > 0)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', '" + 
							fillBars.getJob() +"')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
			}
			else
			{
				if(lstaffnum == 250)
				{
					sizemulti = 4;
				}
				else
				{
					sizemulti = lstaffnum/50;
				}
				
				//entering owner
				for(int i = 0; i < 1*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'saleried', 'Owner')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering manager
				for(int i = 0; i < 3*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'saleried', 'Manager')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering 3 bartenders
				for(int i = 0; i < 12*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bartender')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering cook
				for(int i = 0; i < 4*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Cook')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering servers
				for(int i = 0; i < 14*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Servers')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering barback
				for(int i = 0; i < 4*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Barback')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering bouncer
				for(int i = 0; i < 12*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bouncer')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				while(lstaffnum > 0)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', '" + 
							fillBars.getJob() +"')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
			}
		}
		else //no kitchen means no cooks
		{
			if(lstaffnum <= 50)
			{
				//entering owner
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'saleried', 'Owner')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				//entering manager
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'saleried', 'Manager')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				//entering 3 bartenders
				for(int i = 0; i < 3; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bartender')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering servers
				for(int i = 0; i < 2; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Servers')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering barback
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Barback')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				//entering bouncer
				holder = fillBars.returnStaff();
				sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bouncer')";
				totalskill += fillBars.skillTemp;
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lstaffnum--;
				
				while(lstaffnum > 0)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', '" + 
							fillBars.getJob() +"')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
			}
			else
			{
				if(lstaffnum == 250)
				{
					sizemulti = 4;
				}
				else
				{
					sizemulti = lstaffnum/50;
				}
				
				//entering owner
				for(int i = 0; i < 1*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'saleried', 'Owner')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering manager
				for(int i = 0; i < 3*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'saleried', 'Manager')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering 3 bartenders
				for(int i = 0; i < 12*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bartender')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering servers
				for(int i = 0; i < 14*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Servers')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering barback
				for(int i = 0; i < 4*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Barback')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				//entering bouncer
				for(int i = 0; i < 12*sizemulti; i++)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', 'Bouncer')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
				while(lstaffnum > 0)
				{
					holder = fillBars.returnStaff();
					sql = StaffInsert + holder + "'" + fillBars.getWage() + "', '" + 
							fillBars.getJob() +"')";
					totalskill += fillBars.skillTemp;
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lstaffnum--;
				}
				
			}
		}
			
		
		
		return totalskill;
	}

}
