package frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static backend.DbManager.IntoDaily;
import static backend.DbManager.MonthUpdate;

public class UploadSalesDataController {

    @FXML private DatePicker datePick;
    @FXML private TextField sales;

    public void uploadData(ActionEvent event) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateFromUI = datePick.getValue();
        String date = dateFromUI.format(formatter);
        String dayOfWeek = dateFromUI.getDayOfWeek().toString();
        String amountAsString = sales.getText();
        float amount = Float.parseFloat(amountAsString);

        Boolean value = IntoDaily(date, dayOfWeek, amount);
        if (value) {
            if (MonthUpdate(date)) {
                System.out.println("good");
            } else {
                System.out.println("Wrong 1");
            }
        } else {
            System.out.println("Wrong");
        }
    }

    //Method assign to the back button in the create account page so when clicked
    // it will go back to the homepage
    public void goHomePageAction(ActionEvent event) throws IOException {
        Parent hpParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene hpScene = new Scene(hpParent);
        Stage hpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        hpStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        hpStage.setScene(hpScene);
        hpStage.setTitle("Back");
        hpStage.hide();
        hpStage.show();
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

}
