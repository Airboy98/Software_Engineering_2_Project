package frontend;
import backend.testing;
import backend.testing.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class GenSalePredController implements Initializable {
    @FXML private TableView<UserDetails> table;
    @FXML private TableColumn<UserDetails, String> columnUser;
    @FXML private TableColumn<UserDetails, String> columnPass;
    @FXML private BarChart<String, Float> barChart;
    @FXML private DatePicker datePicker;
    @FXML private DatePicker datePicker1;
    @FXML private TextField Average;

    private ObservableList<UserDetails> data;
    private DBconnection dc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dc = new DBconnection();
    }

    public void loadDataFromDatabase() throws IOException {
        try {
            Connection con = dc.makeconnection();
            data = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM account");
            while (rs.next()) {
                data.add(new UserDetails(rs.getString(1), rs.getString(2)));
            }
        }catch (SQLException ex){
            System.err.println("Error"+ex);
        }

        columnUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        columnPass.setCellValueFactory(new PropertyValueFactory<>("pass"));

        table.setItems(null);
        table.setItems(data);
    }


    //Method that will populate a bar graph based on user selection of dates he/she
    // wants to check
    @FXML
    public void populateBarGraph(ActionEvent event) throws IOException {

        //Retrieve values from datepickers field in the GUI, and from the date
        // retrieved get the day of the week for that specific date
        LocalDate ld = datePicker.getValue();
        String dayOfWeek = ld.getDayOfWeek().toString();
        Integer d1 = ld.getDayOfYear();

        LocalDate ld1 = datePicker1.getValue();
        String dayOfWeek1 = ld1.getDayOfWeek().toString();
        Integer d2 = ld1.getDayOfYear();

        //A DateTimeFormatter instance to format the date to the way its stored
        // in the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = ld.format(formatter);
        String date1 = ld1.format(formatter);

        LocalDate temp[] = new LocalDate[(d2-d1)+1];
        String x[] = new String[(d2-d1)+1];
        String y[] = new String[(d2-d1)+1];
        String dow[] = new String[(d2-d1)+1];
        String z[] = new String[(d2-d1)+1];
        float w[] = new float[(d2-d1)+1];

        for(int i = 0; i <=(d2-d1); i++) {
            temp[i] = ld.plusDays(i);
            dow[i] = temp[i].getDayOfWeek().toString();
            x[i] = temp[i].format(formatter);
            y[i] = testing.WhatMonth(x[i]);
            z[i] = testing.getNumDay(x[i], dow[i]);
            w[i] = testing.frontGetAvg(x[i], dow[i]);
        }



        //Method call from the backend package to retrieve the average gross sales
        // for a specific date, the convert the float value to string and set the
        // the value of the Textfield in the GUI using the average
        float avg = testing.frontGetAvg(date, dayOfWeek);


        //Get the correct month name so we can use it to access the correct table
        // in the database
        String month = testing.WhatMonth(date);


        String day = testing.getNumDay(date, dayOfWeek);
        String day2 = testing.getNumDay(date1, dayOfWeek1);

        XYChart.Series<String, Float> series = new XYChart.Series<>();
        float sum = 0;
        try {
            Connection con = dc.makeconnection1();
            Integer p = 0;

            while(p <= (d2-d1)) {
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + y[p] + " WHERE DayOfMonth = '" + z[p] + "'");
                System.out.println(z[p] + " " + y[p] + " " + w[p]);
                ResultSet rs = pstmt.executeQuery();
                //Populate graph using database values

                    series.getData().add(new XYChart.Data<>(x[p], w[p]));
                    sum += w[p];

                p++;
            }

            String value = String.valueOf(sum/p);
            Average.setText(value);
            barChart.getData().add(series);

            loadDataFromDatabase();
        }catch (SQLException ex){
            System.err.println("Error"+ex);
        }

    }

    public void populateGraph() throws IOException {


        //New instance of XYChart

    }
}
