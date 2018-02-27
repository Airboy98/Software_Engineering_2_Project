import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		ArrayList<dailyavg> hold = new ArrayList<dailyavg>();
		for (int x = 1; x <= 5; x++)
			for (int y = 1; y <= 7; y++) {
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

		String months[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		String monthname[]= {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
		// String day[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
		// "11", "12", "13", "14", "15", "16","17", "18", "19", "20", "21", "22", "23",
		// "24", "25", "26", "27", "28", "29", "30", "31" };
		Runtime r=Runtime.getRuntime();
		float total = 0;
		int count = 0;
		// String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		try {
			for (int x = 0; x < 12; x++) {
				r.gc();
				for (int z = 1; z <= 5; z++)
					for (int y = 0; y <= 6; y++) {
						String query = "SELECT * FROM dailyinformation WHERE Date LIKE '" + months[x]
								+ "%' AND DayOfMonth = '" + z + days[y] + "'";
						ResultSet rs = st.executeQuery(query);
						while (rs.next()) {
							total += rs.getFloat("GrossSales");
							count++;
						}
						if(count==0) count++;
			String query1 = "INSERT INTO "+monthname[x]+" SET DayOfMonth=?,AvgGrossSales=? ON DUPLICATE KEY UPDATE AvgGrossSales = VALUES(AvgGrossSales)";
			PreparedStatement ps = con.prepareStatement(query1);
			dailyavg temp=hold.get((z*(y+1))-1);
			ps.setString(1, z+days[y]);
			ps.setFloat(2, ((total/count)*3+temp.grosssales)/4);
			ps.executeUpdate();
					}			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		

		//for (int x = 0; x < 35; x++)
			//System.out.println(hold.get(x).day + " " + hold.get(x).grosssales);
	}

}
