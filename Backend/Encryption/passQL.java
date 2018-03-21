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
        boolean val = false;
        try{
            byte[] pass;
            pass = Pll.encryptPass(Password);
            System.out.println(pass);
            String holder = "";
            for (int i = 0; i < pass.length; i++) //to int array in string form
            {
                holder = holder + (int) pass[i] + " ";
            }
            String QQ = "INSERT into users (username,passhash,position) VALUES ('" + Name + "','" + holder + "','" + position + "')";
            state.execute(QQ);
            val = true;
        }catch(Exception exep){//SQLException exep){
            exep.printStackTrace();
        }
        return val;
    }

    public String[] CheckPass(String uname, String pass){
        String[] ret = new String[2];
        try{
            Statement st = connect.createStatement();
            String QQ = ("SELECT * FROM users WHERE username = '" + uname + "';");
            ResultSet rs = st.executeQuery(QQ);

            if(rs.next()) {
                int ID = rs.getInt("CustomerID");
                String username = rs.getString("username");
                String hash = rs.getString("passhash");
                String pos = rs.getString("position");

                String[] data = hash.split(" ");

                int[] comp = new int[data.length];
                for (int i = 0; i < data.length; i++) {
                    comp[i] = Integer.parseInt(data[i]);
                }

                byte[] almost = new byte[comp.length];
                for (int i = 0; i < comp.length; i++) {
                    almost[i] = (byte) comp[i];
                }

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