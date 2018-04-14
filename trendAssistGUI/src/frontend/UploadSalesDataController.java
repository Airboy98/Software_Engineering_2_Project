package frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static backend.DbManager.IntoDaily;
import static backend.DbManager.MonthUpdate;
import static frontend.HomePageController.upStage;
import static frontend.LogInController.role;

public class UploadSalesDataController {

    @FXML private DatePicker datePick;
    @FXML private TextField sales;
    @FXML private Label number;

    //This method will upload sales information for an specific date after
    // the button is pressed and satisfying all conditions
    public void uploadData(ActionEvent event) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateFromUI = datePick.getValue();
        String date = dateFromUI.format(formatter);
        String dayOfWeek = dateFromUI.getDayOfWeek().toString();
        String amountAsString = sales.getText();
        try {
            //parse the string for amount in float
            float amount = Float.parseFloat(amountAsString);
            Boolean value = IntoDaily(date, dayOfWeek, amount);
            if(value) {
                MonthUpdate(date);
                upStage.close();
            }
        }
        catch (NumberFormatException e){
            number.setVisible(true);
        }
    }

    //Method assign to the back button in the upload data page so when clicked
    // it will go back to the homepage
    public void goHomePageAction(ActionEvent event) throws IOException {
        if(role.equals("Man")) {
            Parent hpParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene hpScene = new Scene(hpParent);
            Stage hpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            hpStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
            hpStage.setScene(hpScene);
            hpStage.setTitle("Home Page");
            hpStage.setResizable(false);
            hpStage.hide();
            hpStage.show();
            upStage.close();
        }
        else if(role.equals("Emp")){
            Parent hpParent = FXMLLoader.load(getClass().getResource("HomePage_employee.fxml"));
            Scene hpScene = new Scene(hpParent);
            Stage hpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            hpStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
            hpStage.setScene(hpScene);
            hpStage.setTitle("Home Page");
            hpStage.setResizable(false);
            hpStage.hide();
            hpStage.show();
            upStage.close();
        }
    }

    //Method assign to the log out button in the upload data screen so when clicked
    // it will return to the login screen.
    public void goLoginAction(ActionEvent event) throws IOException {
        Parent createParent = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Log Out");
        createStage.setResizable(false);
        createStage.hide();
        createStage.show();
    }

}
