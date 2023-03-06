/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class NavController implements Initializable {

    @FXML
    private Button go_back_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void load1(ActionEvent event) {
        ClientContenuController c = new ClientContenuController();
        c.setPane1();
    }

    @FXML
    private void load2(ActionEvent event) {
        ClientContenuController c = new ClientContenuController();
        c.setPane2();
    }

    @FXML
    private void load3(ActionEvent event) {
        ClientContenuController c = new ClientContenuController();
        c.setPane3();
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Auth.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
  
    }
    
}
