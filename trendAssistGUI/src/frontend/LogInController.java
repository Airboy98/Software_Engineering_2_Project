package sample;

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
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    private DBconnection dc;

    @FXML
    private TextField userID;
    @FXML private PasswordField passID;
    @FXML
    public void logInButtonAction(ActionEvent event) throws IOException {

        String user = userID.getText();
        String pass = passID.getText();
        if (user.isEmpty() || pass.isEmpty()) {
            System.out.println("Type something dumb ass!!!!!!");
        } else {
            if (login(user, pass)) {
                Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene homePageScene = new Scene(homePageParent);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
                appStage.setScene(homePageScene);
                appStage.setTitle("Home Page");
                appStage.show();
            } else
                System.out.println("Error");
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dc = new DBconnection();
    }


    public boolean login(String user, String pass){

        String Name = new String();
        String Pass = new String();
        Connection con = dc.makeconnection();
        String query = "select * from account where User = '" + user + "' && Password='" + pass + "'";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                Name = rs.getString("User");
                Pass = rs.getString("Password");
            }
            if (Name.equals(user) && Pass.equals(pass)) {
                return true;
            }
            else
                return false;

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return true;
    }
}
