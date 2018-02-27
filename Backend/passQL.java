import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
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
            System.out.println(pass);
            char[] ch = bytesToChars(pass);
            String passs = ch.toString();
            System.out.println(passs);

            String QQ = "INSERT into users (username,passhash,position) VALUES ('" + Name + "','" + passs + "','" + position + "')";
            state.execute(QQ);
            val = true;
        }catch(Exception exep){//SQLException exep){
            exep.printStackTrace();
        }
        return val;
    }

    public boolean CheckPass(String uname, String pass){
        try{
            Statement st = connect.createStatement();
            String QQ = ("SELECT * FROM users WHERE username = '" + uname + "';");
            ResultSet rs = st.executeQuery(QQ);

            if(rs.next()) {
                int ID = rs.getInt("CustomerID");
                String username = rs.getString("username");
                String hash = rs.getString("passhash");
                String pos = rs.getString("position");
//                System.out.println(ID);
//                System.out.println(username);
                System.out.println(hash);
//                System.out.println(pos);

                char[] charArray = hash.toCharArray();
                System.out.println(charArray);
                byte[] finhash = charsToBytes(charArray);
                //byte[] ehash = hash.getBytes(StandardCharsets.UTF_8);

                System.out.println("finHash: " + finhash);
                byte[] decrypted = Pll.decrypt(finhash);
                System.out.println(decrypted);
                String stm = new String(decrypted, StandardCharsets.UTF_8);
//                System.out.println(stm);
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
    public byte[] charsToBytes(char[] chars){
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(CharBuffer.wrap(chars));
        return Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
    }

    public char[] bytesToChars(byte[] bytes){
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(ByteBuffer.wrap(bytes));
        return Arrays.copyOf(charBuffer.array(), charBuffer.limit());
    }
}