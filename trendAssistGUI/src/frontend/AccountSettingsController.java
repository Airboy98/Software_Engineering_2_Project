package frontend;

import Encryption.PasswordENC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

    static private Connection AccCon = DBconnection.getconac();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadDataFromDatabase() throws IOException {
        List<String> passwords = new ArrayList<>();

        try {
            data = FXCollections.observableArrayList();

            ResultSet rs = AccCon.createStatement().executeQuery("SELECT * FROM users");
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
            try {
                PreparedStatement pstmt = AccCon.prepareStatement("DELETE FROM users WHERE Username = ?");
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


        try {
            PreparedStatement pstmt = AccCon.prepareStatement("UPDATE users SET Username = ?, Passhash = ?, Position = ? WHERE Username = '" + tempUsername + "'");
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