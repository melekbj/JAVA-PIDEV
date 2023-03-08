/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.Reclamation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterReclamationReservationController implements Initializable {

    @FXML
    private TextArea descriptionReclamation;
    @FXML
    private Button passerReclamation;
    @FXML
    private TextField nomEvenement;
    @FXML
    private TextField IdEvenement;
    @FXML
    private TextField Date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomEvenement.setText("event");   
        IdEvenement.setText("9");
        Date.setText("2023-9-11");

}  

    @FXML
    private void ajoutertest(ActionEvent event) {
          Reclamation r =new Reclamation(30,55,"le produit nest pas fonctionel",(LocalDate.now()),descriptionReclamation.getText(),5,5,"evenement");
        
        ServiceReclamation sr = new ServiceReclamation();
        sr.insert(r);
    }
    
}
