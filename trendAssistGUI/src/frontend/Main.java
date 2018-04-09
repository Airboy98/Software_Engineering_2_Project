package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    //Method that produces the first screen of the application
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        primaryStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        primaryStage.setTitle("trendAssist");
        primaryStage.setScene(new Scene(root));
        primaryStage.hide();
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
