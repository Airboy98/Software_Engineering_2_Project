package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    //Button actions so when an specific button is clicked then switch to correct screen
    public void createAccountButtonAction(ActionEvent event) throws IOException {
        Parent createParent = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.hide();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Create New Account");
        createStage.show();
    }

    public void salePredictionButtonAction(ActionEvent event) throws IOException {
        Parent predParent = FXMLLoader.load(getClass().getResource("SalePrediction.fxml"));
        Scene predScene = new Scene(predParent);
        Stage predStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        predStage.hide();
        predStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        predStage.setScene(predScene);
        predStage.setTitle("Sale Prediction");
        predStage.show();
    }

    public void accountSettingsButtonAction(ActionEvent event) throws IOException {
        Parent accParent = FXMLLoader.load(getClass().getResource("AccountSettings.fxml"));
        Scene accScene = new Scene(accParent);
        Stage accStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        accStage.hide();
        accStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        accStage.setScene(accScene);
        accStage.setTitle("Account Settings");
        accStage.show();
    }

    public void logout(ActionEvent event) throws IOException {
        Parent logoutParent = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        Scene logoutScene = new Scene(logoutParent);
        Stage logoutStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        logoutStage.hide();
        logoutStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        logoutStage.setScene(logoutScene);
        logoutStage.setTitle("Log Out");
        logoutStage.show();
    }


}
