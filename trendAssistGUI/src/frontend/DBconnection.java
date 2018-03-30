package frontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DBconnection {


    static private Connection con;

    // Makes the connection and returns the statement
    public Connection makeconnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "0000");
            return con;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return null;
    }


    public Connection makeconnection1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdata", "root", "0000");
            return con;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return null;
    }

    //this class hands the inventory table in the database
}
