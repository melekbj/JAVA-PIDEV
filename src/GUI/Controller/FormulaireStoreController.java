/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controller;

import Entities.Store;
import Entities.User;
import Service.StoreService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jmili
 */
public class FormulaireStoreController implements Initializable {

    @FXML
    private TextField STName;
    @FXML
    private TextField STAdresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createStore(ActionEvent event) {
        StoreService SS=new StoreService();
        User u=new User();
        Store st=new Store(STName.getText(),STAdresse.getText(),u);
        SS.insert(st);
    }
    
}
