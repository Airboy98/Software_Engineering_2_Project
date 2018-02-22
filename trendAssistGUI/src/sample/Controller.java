package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller{


    public void logInButtonAction(ActionEvent event) throws IOException{

        Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        appStage.setScene(homePageScene);
        appStage.show();
    }

    public void createAccountButtonAction(ActionEvent event) throws IOException{

        Parent createParent = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.show();
    }


}
