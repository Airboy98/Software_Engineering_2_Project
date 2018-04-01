import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connectionmanagment {
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

	public static Statement getst(){
		return st;
	}
	public static Connection getcon(){
		return con;
	}
}
