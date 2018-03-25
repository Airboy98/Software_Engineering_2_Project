package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class createAccountController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dc = new DBconnection();
        loadData();
    }

    public createAccountController() {
    }

    public void goHomePageAction(ActionEvent event) throws IOException {
        Parent createParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Back");
        createStage.show();
    }

    public void goLoginAction(ActionEvent event) throws IOException {
        Parent createParent = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Log Out");
        createStage.show();
    }

    private DBconnection dc;
    ObservableList list = FXCollections.observableArrayList();


    @FXML
    private TextField userID;
    @FXML private PasswordField password1;
    @FXML private PasswordField password2;
    @FXML private ChoiceBox<String> Role;
    private void loadData()
    {
        list.removeAll(list);
        String a = "Choose a Role";
        String b = "Manager";
        String c = "Employee";
        list.addAll(a,b,c);
        Role.getItems().addAll(list);
    }



    private void createAccountButtonActionX(ActionEvent event) throws IOException {

        String user = userID.getText();
        String pass1 = password1.getText();
        String pass2 = password2.getText();
        if (user.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            System.out.println("Specify ALL account credentials");
        } else {
            if (pass1.equals(pass2))
            {
                Connection con = dc.makeconnection();
                String query = "INSERT INTO account VALUES (" + user + "," + pass1 + ")";
            }
        }

    }

}
