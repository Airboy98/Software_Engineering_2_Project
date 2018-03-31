package frontend;
import Encryption.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    private DBconnection dc;

    @FXML
    private TextField userID;
    @FXML
    private PasswordField passID;

    //Action method assigned to the sign in button, so when clicked will
    // check username and password with the database using an decryption
    // method, and if the check is successful then the homepage screen
    // will be opened
    public void logInButtonAction(ActionEvent event) throws IOException {

        //Get text from the text field in the GUI
        String user = userID.getText();
        String pass = passID.getText();

        //New instance object for accessing encryption methods
        passQL encrypt = new passQL();

        //Method call for checking password from user input that will return
        // a String array that will contain True if successful, and False
        // otherwise, and the array will also contain the position of the user
        // and based on the position it will open the home page screen.
        String[] checkResult = encrypt.CheckPass(user, pass);

        if(checkResult[0].equals("True") && checkResult[1].equals("Manager")){
            Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene homePageScene = new Scene(homePageParent);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.hide();
                appStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
                appStage.setScene(homePageScene);
                appStage.setTitle("Home Page");
                appStage.show();
        }
        else if(checkResult[0].equals("True") && checkResult[1].equals("Employee")){
            Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage_employee.fxml"));
            Scene homePageScene = new Scene(homePageParent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            appStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
            appStage.setScene(homePageScene);
            appStage.setTitle("Home Page");
            appStage.show();
        }
        else{
            System.out.println("Your are wrong!");
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dc = new DBconnection();
    }


//    public boolean login(String user, String pass){
//
//        String Name = new String();
//        String Pass = new String();
//        Connection con = dc.makeconnection();
//        String query = "select * from account where User = '" + user + "' && Password='" + pass + "'";
//        try {
//            ResultSet rs = con.createStatement().executeQuery(query);
//            while (rs.next()) {
//                Name = rs.getString("User");
//                Pass = rs.getString("Password");
//            }
//            if (Name.equals(user) && Pass.equals(pass)) {
//                return true;
//            }
//            else
//                return false;
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        return true;
//    }
}
