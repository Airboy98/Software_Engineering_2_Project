package sample;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class createAccountController {

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
}
