package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SalePredictionController {

    public void salePredictionButtonAction(ActionEvent event) throws IOException {

        Parent predParent = FXMLLoader.load(getClass().getResource("SalePrediction.fxml"));
        Scene predScene = new Scene(predParent);
        Stage predStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        predStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        predStage.setScene(predScene);
        predStage.setTitle("Sale Prediction");
        predStage.show();
    }
}
