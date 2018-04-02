package Encryption;

import java.sql.*;

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
        try{
            byte[] pass;
            pass = Pll.encryptPass(Password);
            String holder = Pll.byteToString(pass);
            String QQ = "INSERT into users (Username,Passhash,Position) VALUES ('" + Name + "','" + holder + "','" + position + "')";
            state.execute(QQ);
            return true;
        }catch(Exception exep){//SQLException exep
            return false;
        }
    }

    public String[] CheckPass(String uname, String pass){
        String[] ret = new String[2];
        try{
            Statement st = connect.createStatement();
            String QQ = ("SELECT * FROM users WHERE Username = '" + uname + "';");
            ResultSet rs = st.executeQuery(QQ);

            if(rs.next()) {
                int ID = rs.getInt("AccountID");
                String username = rs.getString("Username");
                String hash = rs.getString("Passhash");
                String pos = rs.getString("Position");

                byte[] almost = Pll.stringToByte(hash);

                String dec = Pll.decrypt(almost);

                if(dec.equals(pass)){
                    ret[0] = "True";
                    ret[1] = pos;
                    return ret;
                }
                ret[0] = null;
                ret[1] = null;
                return ret;
            }
        }catch(SQLException exep){
            exep.printStackTrace();
        }
        ret[0] = null;
        ret[1] = null;
        return ret;
    }
}