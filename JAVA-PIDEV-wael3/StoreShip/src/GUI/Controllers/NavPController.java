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
public class NavPController implements Initializable {

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
    private void loadPane1(ActionEvent event) {
         PartnerContenuController p = new PartnerContenuController();
         p.setPane1();
    }

    @FXML
    private void loadPane2(ActionEvent event) {
         PartnerContenuController p = new PartnerContenuController();
         p.setPane2();
    }
    
      @FXML
    private void loadPane3(ActionEvent event) {
        PartnerContenuController p = new PartnerContenuController();
         p.setPane3();
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
