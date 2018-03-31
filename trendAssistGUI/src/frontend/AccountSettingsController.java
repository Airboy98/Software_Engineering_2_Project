package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountSettingsController implements Initializable {
    @FXML private TableView<UserDetails> table;
    @FXML private TableColumn<UserDetails, String> columnUser;
    @FXML private TableColumn<UserDetails, String> columnPass;
    @FXML private TableColumn<UserDetails, String> columnPos;
    @FXML
    private TextField uname;
    @FXML
    private TextField pass;
    @FXML
    private TextField pos;

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

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");
            while (rs.next()) {
                data.add(new UserDetails(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }catch (SQLException ex){
            System.err.println("Error"+ex);
        }

        columnUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        columnPass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        columnPos.setCellValueFactory(new PropertyValueFactory<>("pos"));


        table.setItems(null);
        table.setItems(data);



    }

    public void test(){


        if(table.getSelectionModel().getSelectedItem() != null){
            UserDetails selectedRow = table.getSelectionModel().getSelectedItem();
            uname.setText(selectedRow.getUser());
            pass.setText(selectedRow.getPass());
            pos.setText(selectedRow.getPos());
        }
    }



}
