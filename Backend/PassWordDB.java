import java.sql.*;
import java.util.*;

public class PassWordDB{
    private Connection connect;
    private Statement state;
    private ResultSet resSet;

    public PassWordDB(){
        try{
            java.sql.Driver d=new com.mysql.jdbc.Driver();

            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "root");
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

    public boolean AddUser(String Name, String Password, ){
        boolean val = false;
        try{
            String QQ = "INSERT into inv_table (Name,ID,Qty,Cost) VALUES (\"" + Name + "\"," + ID + ",\"" + Qty + "\"," + Cost + ")";
            state.execute(QQ);
            val = true;
        }catch(SQLException exep){
            exep.printStackTrace();
        }
        return val;
    }

    public boolean RemoveItem(int ID){
        boolean val = false;
        try{
            String QQ = "DELETE FROM inv_table";
            String Q2 = "WHERE ID='"+ ID +"';";
            String Q3 = QQ + "\n" + Q2;
            state.execute(Q3);
            val = true;
        }catch(SQLException exep){
            exep.printStackTrace();
        }
        return val;
    }

    public boolean UpdateQty(int ID, int Qty){
        boolean val = false;
        try{
            String QQ = "UPDATE inv_table";
            String Q2 = "SET Qty = "+ Qty;
            String Q3 = "WHERE ID = "+ ID;
            String QF = QQ + "\n" + Q2 + "\n" + Q3;
            val = state.execute(QF);
        }catch(SQLException exep){
            exep.printStackTrace();
        }
        return val;
    }

    public boolean UpdateCost(int ID, int cost){
        boolean val = false;
        try{
            String QQ = "UPDATE inv_table";
            String Q2 = "SET Cost ="+ cost;
            String Q3 = "WHERE ID = "+ ID;
            String QF = QQ + "\n" + Q2 + "\n" + Q3;
            val = state.execute(QF);
        }catch(SQLException exep){
            exep.printStackTrace();
        }
        return val;
    }

    public boolean DecQty(int ID)
    {
        try{
            Vector v = new Vector();
            v = searchData(ID);
            int i = (int)v.get(2);
            UpdateQty(ID, i-1);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean IncQty(int ID)
    {
        try{
            Vector v = new Vector();
            v = searchData(ID);
            int i = (int)v.get(2);
            UpdateQty(ID, i+1);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public Vector searchData(int IDselect){
        Vector v = new Vector();
        try{
            Statement st = connect.createStatement();
            String getFrom = ("SELECT * FROM inv_table WHERE ID = '" + IDselect + "';");
            ResultSet rs = st.executeQuery(getFrom);
            if(rs.next() == true) {
                String name = rs.getString("Name");
                int ID = rs.getInt("ID");
                int Qty = rs.getInt("Qty");
                int Cost = rs.getInt("Cost");
                v.addElement(name);
                v.addElement(ID);
                v.addElement(Qty);
                v.addElement(Cost);
            }else{
                return null;
            }
        }catch (Exception exep){
            return null;
        }
        return v;
    }

    public Vector PullData(){
        Vector v = new Vector();
        try{
            Statement st = connect.createStatement();
            String getFrom = ("SELECT * FROM inv_table ORDER BY ID;");
            ResultSet rs = st.executeQuery(getFrom);
            while(rs.next()){
                String name = rs.getString("Name");
                int ID = rs.getInt("ID");
                int Qty = rs.getInt("Qty");
                int Cost = rs.getInt("Cost");
                Vector temp = new Vector();
                temp.addElement(name);
                temp.addElement(ID);
                temp.addElement(Qty);
                temp.addElement(Cost);
                v.addElement(temp);
            }
        }catch(Exception exep){
            exep.printStackTrace();
        }
        return v;
    }
}