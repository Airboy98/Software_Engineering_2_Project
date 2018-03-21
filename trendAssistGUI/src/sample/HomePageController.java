package sample;

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

    public void createAccountButtonAction(ActionEvent event) throws IOException {

        Parent createParent = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Create New Account");
        createStage.show();
    }
    public void salePredictionButtonAction(ActionEvent event) throws IOException {

        Parent predParent = FXMLLoader.load(getClass().getResource("SalePrediction.fxml"));
        Scene predScene = new Scene(predParent);
        Stage predStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        predStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        predStage.setScene(predScene);
        predStage.setTitle("Sale Prediction");
        predStage.show();
    }

    public void logout(ActionEvent event) throws IOException {

        Parent predParent = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        Scene predScene = new Scene(predParent);
        Stage predStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        predStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        predStage.setScene(predScene);
        predStage.setTitle("Log Out");
        predStage.show();
    }


}
