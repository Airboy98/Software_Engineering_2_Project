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
    @FXML private BarChart<String, Double> barChart;
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



    @FXML
    public void populateBarGraph(ActionEvent event) throws IOException {


        XYChart.Series<String, Double> series = new XYChart.Series<>();
        LocalDate ld = datePicker.getValue();
        String dayOfWeek = ld.getDayOfWeek().toString();

        LocalDate ld1 = datePicker1.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = ld.format(formatter);
        String date1 = ld1.format(formatter);
        System.out.println(date);
        System.out.println(date1);

        float avg = testing.frontGetAvg(date, dayOfWeek);
        String value = String.valueOf(avg);
        Average.setText(value);

        try {
            Connection con = dc.makeconnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT date, salescol FROM sales WHERE date >= ? AND date <= ?");
            pstmt.setString(1, date);
            pstmt.setString(2, date1);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            barChart.getData().add(series);
            loadDataFromDatabase();
        }catch (SQLException ex){
            System.err.println("Error"+ex);
        }

    }
}