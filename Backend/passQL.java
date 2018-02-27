import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

public class passQL{
    private Connection connect;
    private Statement state;
    private ResultSet resSet;

    PasswordENC Pll = new PasswordENC();

    public passQL(){
        try{
            java.sql.Driver d = new com.mysql.jdbc.Driver();

            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts?useSSL=false", "root", "0000");
            state = connect.createStatement();
        }catch(Exception exep){
            exep.printStackTrace();
            System.out.println("Error: " + exep);
        }
    }
    //close
    public void close(){
        try{
            connect.close();
        }catch(Exception E){

        }
    }

    public boolean AddUser(String Name, String Password, String position){
        boolean val = false;
        try{
            byte[] pass;
            pass = Pll.encryptPass(Password);
            String passs = asHex(pass);
            String QQ = "INSERT into users (username,passhash,position) VALUES ('" + Name + "','" + passs + "','" + position + "')";
            state.execute(QQ);
            val = true;
        }catch(SQLException exep){
            exep.printStackTrace();
        }
        return val;
    }

    public boolean CheckPass(String uname, String pass){
        try{
            Statement st = connect.createStatement();
            String QQ = ("SELECT * FROM users WHERE username LIKE '" + uname + "'");
            ResultSet rs = st.executeQuery(QQ);
            //System.out.println(rs.getString("username"));
            if(rs.next()) {
                int ID = rs.getInt("CustomerID");
                String username = rs.getString("username");
                String hash = rs.getString("passhash");
                String pos = rs.getString("position");
                byte[] ehash = hash.getBytes(StandardCharsets.UTF_8);
                byte[] decrypted = Pll.decrypt(ehash);
                System.out.println(decrypted);
                String stm = new String(decrypted, StandardCharsets.UTF_8);
                if(stm.equals(pass)){
                    return true;
                }
                return false;
            }
        }catch(SQLException exep){
            exep.printStackTrace();
        }
        return false;
    }
    public static String asHex (byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }
}