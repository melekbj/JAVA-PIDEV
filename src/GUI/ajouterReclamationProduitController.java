/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.Produit;
import entite.Reclamation;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.ServicePersonne;
import service.ServiceProduit;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ajouterReclamationProduitController implements Initializable {

    @FXML
    private TextArea descriptionReclamation;
    @FXML
    private Button passerReclamation;
    @FXML
    private Label nomProduit;
   
    @FXML
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Label idCommande;
    @FXML
    private Label prixTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProduit sp = new ServiceProduit();
        Produit p = sp.readById(6);
        nomProduit.setText(p.getNom_produit());
        prix.setText(Float.toString(p.getPrix_produit()));
        categorie.setText(String.valueOf(p.getCategorie().getNom_categorie()));
         descriptionReclamation.textProperty().addListener((observable, oldValue, newValue) -> {
        int wordCount = newValue.trim().split("\\s+").length;
        String style = wordCount < 3 ?
                       "-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);" :
                       "-fx-effect: dropshadow(three-pass-box, green, 10, 0, 0, 0);";
       descriptionReclamation.setStyle(style);
    });
        
    }    
   

    @FXML
    private void ajoutertest(ActionEvent event) {
          Reclamation r =new Reclamation(31,55,"le produit nest pas fonctionel",(LocalDate.now()),descriptionReclamation.getText(),5,5,"produit");
        
        ServiceReclamation sr = new ServiceReclamation();
        if(!descriptionReclamation.getText().equals(""))
        sr.insert(r);
      
           
    }
    
  

}
