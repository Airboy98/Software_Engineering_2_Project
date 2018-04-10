package frontend;

import backend.DbManager;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class GenSalePredController {

    @FXML private BarChart<String, Float> barChart;
    @FXML private DatePicker datePicker;
    @FXML private DatePicker datePicker1;
    @FXML private TextField Average;
    @FXML private TableView<SalesDetails> table;
    @FXML private TableColumn<SalesDetails, String> columnDate;
    @FXML private TableColumn<SalesDetails, Float> columnAvgSales;


    //Method that will populate a bar graph based on user selection of dates he/she
    // wants to check
    public void populateBarGraph(ActionEvent event) throws IOException {

        //Retrieve values from datepickers field in the GUI, and from the date
        // retrieved get the day of the week for that specific date
        LocalDate ld = datePicker.getValue();
        Integer d1 = ld.getDayOfYear();
        Integer y1 = ld.getYear();

        LocalDate ld1 = datePicker1.getValue();
        Integer d2 = ld1.getDayOfYear();
        Integer y2 = ld1.getYear();

        //A DateTimeFormatter instance to format the date to the way its stored
        // in the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        if (y2 - y1 < 0) {
            Alert errorMessage = new Alert(Alert.AlertType.ERROR);
            errorMessage.setTitle("Incorrect Date");
            errorMessage.setContentText("Please input correct dates!");
            errorMessage.setHeaderText(null);
            errorMessage.showAndWait();
        } else {
            LocalDate temp[] = new LocalDate[(d2 - d1) + 1];
            String x[] = new String[(d2 - d1) + 1];
            String y[] = new String[(d2 - d1) + 1];
            String dow[] = new String[(d2 - d1) + 1];
            String z[] = new String[(d2 - d1) + 1];
            float w[] = new float[(d2 - d1) + 1];

            for (int i = 0; i <= (d2 - d1); i++) {
                temp[i] = ld.plusDays(i);
                dow[i] = temp[i].getDayOfWeek().toString();
                x[i] = temp[i].format(formatter);
                y[i] = DbManager.WhatMonth(x[i]);
                z[i] = DbManager.getNumDay(x[i], dow[i]);
                w[i] = DbManager.frontGetAvg(x[i], dow[i]);
            }

            XYChart.Series<String, Float> series = new XYChart.Series<>();
            float sum = 0;

            ObservableList<SalesDetails> data = FXCollections.observableArrayList();
            Integer p = 0;
            barChart.getData().clear();
            while (p <= (d2 - d1)) {
                series.getData().add(new XYChart.Data<>(x[p], w[p]));
                data.add(new SalesDetails(x[p], w[p]));
                sum += w[p];
                p++;
            }

            String value = String.valueOf(sum / p);
            Average.setText(value);
            barChart.getData().add(series);

            columnDate.setCellValueFactory(new PropertyValueFactory<SalesDetails, String>("Date"));
            columnAvgSales.setCellValueFactory(c ->
                    new ReadOnlyObjectWrapper<Float>(c.getValue().getAvgSales()));
            table.setItems(null);
            table.setItems(data);
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
        hpStage.setResizable(false);
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
        createStage.setResizable(false);
        createStage.hide();
        createStage.show();
    }
}
