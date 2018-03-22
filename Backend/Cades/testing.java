import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class testing {

	static private Statement st = makeconnection();
	static private Connection con;

	// Makes the connection and returns the statement
	private static Statement makeconnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdata", "root", "0000");
			return con.createStatement();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		return null;
	}
	
	//updates one day in the months database
	boolean UpdateOneDay(String month,float avg,String day,float grosssales) {
		try {
		String query1 = "INSERT INTO "+month+" SET DayOfMonth=?,AvgGrossSales=? ON DUPLICATE KEY UPDATE AvgGrossSales = VALUES(AvgGrossSales)";
		PreparedStatement ps = con.prepareStatement(query1);
		//dailyavg temp=hold.get((z*(y+1))-1);
		ps.setString(1, day);
		ps.setFloat(2, ((avg)*3+grosssales)/4);
		ps.executeUpdate();
		return true;
		}catch (SQLException e){
			System.out.println(e);
			return false;
		}
	}
	
	//Retrieves avg for that day of the particular month
	float GetAvg(String month,String day) {
		float total=0;
		int count=0;
		String query = "SELECT * FROM dailyinformation WHERE Date LIKE '" + month+ "%' AND DayOfMonth = '" +day+ "'";
		try {
		ResultSet rs = st.executeQuery(query);
		
		while (rs.next()) {
			total += rs.getFloat("GrossSales");
			count++;
	}
		}catch (SQLException e) {
		System.out.println(e);
		}
		if (count==0) return 0;
		return total/count;
		
	}
	
	//fills arraylist for local use
	ArrayList<dailyavg> filllist() {
		ArrayList<dailyavg> hold = new ArrayList<dailyavg>();
		for (int x = 1; x <= 5; x++)
			for (int y = 1; y <= 7; y++) {
				String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
				float total = 0;
				int count = 0;
				String query = "SELECT * FROM dailyinformation WHERE DayOfMonth = '" + x + days[y - 1] + "'";
				try {
					ResultSet rs = st.executeQuery(query);
					while (rs.next()) {
						total = rs.getFloat("GrossSales");
						count++;
					}
					dailyavg temp = new dailyavg();
					if (count != 0) {
						temp.day = x + days[y - 1];
						temp.grosssales = (total / count);
					} else {
						temp.day = x + days[y - 1];
						temp.grosssales = 0;
					}
					hold.add(temp);
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		return hold;
	}
	
	//find which day of the month it is ie: 1mon 2mon
	static int dayofmonth(int day) {
		 if(day<7) return 1;
		    if(day<14) return 2;
		    if(day<22) return 3;
		    if(day<29) return 4;
		    return 5;
		
	}
	
	//inserts into dailyinformation table
	static boolean IntoDaily(String date,String dayofweek,String dayofyear,String dayofmonth,float GrossSales) {
		try {
			String query="INSERT INTO dailyinformation SET Date=?,DayOfWeek=?,DayOfYearByWeek=?,DayOfMonth=?,GrossSales=? ON DUPLICATE KEY UPDATE DayOfWeek=?,DayOfYearByWeek=?,DayOfMonth=?,GrossSales=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, date);
			ps.setString(2, dayofweek);
			ps.setString(3, dayofyear);
			ps.setString(4, dayofmonth);
			ps.setDouble(5, GrossSales);
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
//		testing blah=new testing();
//		boolean worked = false;
//		String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
//		ArrayList<dailyavg> hold = new ArrayList<dailyavg>();
//		hold = blah.filllist();
//		String months[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
//		String monthname[]= {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","decm"};
//		Runtime r=Runtime.getRuntime();
//		float total = 0;
//		int count = 0;
//			for (int x = 0; x < 12; x++) {
//				r.gc();
//				for (int z = 1; z <= 5; z++)
//					for (int y = 0; y <= 6; y++) {
//						float avg= blah.GetAvg(months[x],z+days[y]);
//						dailyavg temp=hold.get((z*(y+1))-1);
//						worked=blah.UpdateOneDay(monthname[x],avg,z+days[y],temp.grosssales);
//					}			}
//	System.out.println(worked);
		
		  Scanner scanner;
		try {
			//array1 position are 0=date 1=grosssales 2=day of week
			String current = null;
			int DayOfYearByWeek;
			String DayOfMonth;
			scanner = new Scanner(new File("C:\\Users\\cadew\\Documents\\GitHub\\Software2project\\DataImport.csv"));
			//scanner.useDelimiter(",");
			scanner.next();
	        while(scanner.hasNext()){
	        	current=scanner.next()+",";
	        	String[] array1 = current.split(",");
	        	String[] day=array1[0].split("/");
	        	int dayval=Integer.parseInt(day[1]);
	        	DayOfMonth=dayofmonth(dayval)+array1[2];
	        	DayOfYearByWeek=dayofmonth(dayval)*Integer.parseInt(day[0]);
	        	float grossales=Float.parseFloat(array1[1]);
	        	boolean ok = IntoDaily(array1[0],array1[2],Integer.toString(DayOfYearByWeek),DayOfMonth,grossales);
	        }
	        scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
