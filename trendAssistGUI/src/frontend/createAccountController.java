package frontend;

import Encryption.passQL;
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
import java.util.ResourceBundle;

import static frontend.DBconnection.getconac;

public class createAccountController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    //Declaration of variables in the GUI, in order to be able to access them
    @FXML private TextField userID;
    @FXML private PasswordField password1;
    @FXML private PasswordField password2;
    @FXML private ChoiceBox<String> Role;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    //Method assign to the back button in the create account page so when clicked
    // it will go back to the homepage
    public void goHomePageAction(ActionEvent event) throws IOException {
        Parent createParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Back");
        createStage.hide();
        createStage.show();
    }

    //Method assign to the log out button in the create account screen so when clicked
    // it will return to the login screen.
    public void goLoginAction(ActionEvent event) throws IOException {
        Parent createParent = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Log Out");
        createStage.hide();
        createStage.show();
    }

    //Method to load option in the choice
    private void loadData() {
        list.removeAll(list);
        String a = "Choose a Role";
        String b = "Manager";
        String c = "Employee";
        list.addAll(a, b, c);
        Role.getItems().addAll(list);
        Role.setValue("Choose a Role");
    }

    //Action method assigned to the create account button, so when clicked it will
    // get username, password, and position from the user input and call a method,
    // from the encryption package to encrypt the new password and store the info
    // in the database.
    public void createAccountButtonActionX(ActionEvent event) throws IOException {

        //New instance object for accessing encryption methods
        passQL create = new passQL();

        //Get user input from the GUI
        String user = userID.getText();
        String pass1 = password1.getText();
        String pass2 = password2.getText();
        String pos = Role.getValue();

        //Check if any of the fields is empty and if it is prompt a message to the
        // user telling them to fill all the fields
        if (user.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            System.out.println("Specify ALL account credentials");
        } else {
            //Check if both password are the same
            if (pass1.equals(pass2)) {

                //Encryption method call
                create.AddUser(user, pass1, pos);
            }
        }
    }
}
