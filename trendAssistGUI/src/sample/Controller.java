package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    public void logInButtonAction(ActionEvent event) throws IOException{

        Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        appStage.setScene(homePageScene);
        appStage.setTitle("Home Page");
        appStage.show();
    }

    public void createAccountButtonAction(ActionEvent event) throws IOException{

        Parent createParent = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
        Scene createScene = new Scene(createParent);
        Stage createStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        createStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        createStage.setScene(createScene);
        createStage.setTitle("Create New Account");
        createStage.show();
    }
    public void salePredictionButtonAction(ActionEvent event) throws IOException {

        Parent predParent = FXMLLoader.load(getClass().getResource("SalePrediction.fxml"));
        Scene predScene = new Scene(predParent);
        Stage predStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        predStage.getIcons().add(new Image("/icons/TrendAssist Logo2.jpg"));
        predStage.setScene(predScene);
        predStage.setTitle("Sale Prediction");
        predStage.show();
    }

    @FXML
    private TableView<UserDetails> table;
    @FXML private TableColumn<UserDetails, String> columnUser;
    @FXML private TableColumn<UserDetails, String> columnPass;


    private ObservableList<UserDetails> data;
    private DBconnection dc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dc = new DBconnection();
    }

    public void loadDataFromDatabase(ActionEvent event) throws IOException{
        try {
            Connection con = dc.makeconnection();
            data = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM userdetails");
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



}
