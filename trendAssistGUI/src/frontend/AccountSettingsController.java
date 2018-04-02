package frontend;

import Encryption.PasswordENC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountSettingsController implements Initializable {

    PasswordENC Pll = new PasswordENC();

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

    static String tempUsername;

    private ObservableList<UserDetails> data;
    private DBconnection dc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dc = new DBconnection();
    }

    public void loadDataFromDatabase() throws IOException {
        List<String> passwords = new ArrayList<>();

        try {
            Connection con = dc.makeconnection();
            data = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");
            Integer i = 0;
            while (rs.next()) {
                byte[] almost = Pll.stringToByte(rs.getString(3));

                passwords.add(Pll.decrypt(almost));
                data.add(new UserDetails(rs.getString(2), passwords.get(i), rs.getString(4)));
                i++;
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

    public void selectRowItems(){
        if(table.getSelectionModel().getSelectedItem() != null){
            UserDetails selectedRow = table.getSelectionModel().getSelectedItem();
            tempUsername = selectedRow.getUser();
            uname.setText(selectedRow.getUser());
            pass.setText(selectedRow.getPass());
            pos.setText(selectedRow.getPos());
        }
    }

    public void deleteAccount() throws IOException {
        if(table.getSelectionModel().getSelectedItem() != null){
            UserDetails selectedRow = table.getSelectionModel().getSelectedItem();
            Connection con = dc.makeconnection();
            try {
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM users WHERE Username = ?");
                pstmt.setString(1, selectedRow.getUser());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            loadDataFromDatabase();
            uname.clear();
            pass.clear();
            pos.clear();
        }
    }

    public void updateAccount() throws IOException {

        Connection con = dc.makeconnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE users SET Username = ?, Passhash = ?, Position = ? WHERE Username = '" + tempUsername + "'");
            String Password = pass.getText();
            byte[] pass;
            pass = Pll.encryptPass(Password);
            String holder = Pll.byteToString(pass);
            pstmt.setString(1, uname.getText());
            pstmt.setString(2, holder);
            pstmt.setString(3, pos.getText());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadDataFromDatabase();


    }



}