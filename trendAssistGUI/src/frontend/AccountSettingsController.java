package frontend;

import Encryption.PasswordENC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccountSettingsController {

    PasswordENC Pll = new PasswordENC();

    @FXML private TableView<UserDetails> table;
    @FXML private TableColumn<UserDetails, String> columnUser;
    @FXML private TableColumn<UserDetails, String> columnPass;
    @FXML private TableColumn<UserDetails, String> columnPos;
    @FXML private TextField uname;
    @FXML private TextField pass;
    @FXML private TextField pos;
    @FXML private Label error;
    static String tempUsername;
    private ObservableList<UserDetails> data;
    static private Connection AccCon = DBconnection.getconac();

    //This method when called will load a table view with all the accounts in the database
    public void loadDataFromDatabase() {
        List<String> passwords = new ArrayList<>();
        try {
            data = FXCollections.observableArrayList();

            //SQL query to that returns all accounts in the database
            ResultSet rs = AccCon.createStatement().executeQuery("SELECT * FROM users");
            Integer i = 0;
            while (rs.next()) {
                byte[] almost = Pll.stringToByte(rs.getString(3));
                passwords.add(Pll.decrypt(almost));
                data.add(new UserDetails(rs.getString(2), passwords.get(i), rs.getString(4)));
                i++;
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        columnUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        columnPass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        columnPos.setCellValueFactory(new PropertyValueFactory<>("pos"));

        table.setItems(null);
        table.setItems(data);
    }

    //This method will field text fields when a row is selected in the table view
    public void selectRowItems() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            UserDetails selectedRow = table.getSelectionModel().getSelectedItem();
            tempUsername = selectedRow.getUser();
            uname.setText(selectedRow.getUser());
            pass.setText(selectedRow.getPass());
            pos.setText(selectedRow.getPos());
        }
    }

    //This method will delete an account from the database as long as it is
    // selected by the user
    public void deleteAccount() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            UserDetails selectedRow = table.getSelectionModel().getSelectedItem();
            try {
                //Query to delete all the account information in the database
                PreparedStatement pstmt = AccCon.prepareStatement("DELETE FROM users WHERE Username = ?");
                pstmt.setString(1, selectedRow.getUser());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //Reload table view and clear text fields
            loadDataFromDatabase();
            uname.clear();
            pass.clear();
            pos.clear();
        }
    }

    //This method will update an account in the database as long as the user make any
    // changes to the username or password or the role
    public void updateAccount() {
        try {
            //Query to update account in the database
            PreparedStatement pstmt = AccCon.prepareStatement("UPDATE users SET Username = ?, Passhash = ?, Position = ? WHERE Username = '" + tempUsername + "'");
            String Password = pass.getText();
            String Username = uname.getText();
            String Position = pos.getText();
            if(Password.isEmpty() || Username.isEmpty() || Position.isEmpty()){
                error.setVisible(true);
            }
            else {
                byte[] pass;
                pass = Pll.encryptPass(Password);
                String holder = Pll.byteToString(pass);
                pstmt.setString(1, Username);
                pstmt.setString(2, holder);
                pstmt.setString(3, Position);
                pstmt.executeUpdate();
                error.setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Reload table view
        loadDataFromDatabase();
    }

    //Method assign to the back button in the account settings page so when clicked
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

    //Method assign to the log out button in the account settings screen so when clicked
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