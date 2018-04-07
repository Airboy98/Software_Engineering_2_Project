package frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static backend.DbManager.IntoDaily;
import static backend.DbManager.MonthUpdate;

public class UploadSalesDataController implements Initializable{

@FXML private DatePicker datePick;
@FXML private TextField sales;

public void uploadData(ActionEvent event){

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    LocalDate dateFromUI = datePick.getValue();
    String date = dateFromUI.format(formatter);
    String dayOfWeek = dateFromUI.getDayOfWeek().toString();
    String amountAsString = sales.getText();
    float amount = Float.parseFloat(amountAsString);

    Boolean value = IntoDaily(date, dayOfWeek, amount);
    if(value){
        if(MonthUpdate(date)){
            System.out.println("good");
        }
        else{
            System.out.println("Wrong 1");
        }
    }
    else{
        System.out.println("Wrong");
    }

}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
