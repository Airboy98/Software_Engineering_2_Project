package frontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DBconnection {

    static private Connection conac;
    static private Connection consa;
    static private Statement stac = makeconnectionac();
    static private Statement stsa = makeconnectionsa();

    // Makes the connection and returns the statement
    static private Statement makeconnectionac() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conac = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "0000");
            return conac.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return null;
    }

    static private Statement makeconnectionsa() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            consa = DriverManager.getConnection("jdbc:mysql://localhost:3306/salesdata", "root", "0000");
            return consa.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        return null;
    }

    public static Statement getstac() {
        return stac;
    }
    public static Connection getconac() {
        return conac;
    }

    public static Statement getstsa() {
        return stsa;
    }
    public static Connection getconsa() {
        return consa;
    }
}