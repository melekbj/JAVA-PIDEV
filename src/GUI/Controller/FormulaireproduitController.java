/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controller;

import Entities.Categorie;
import Entities.Produit;
import Entities.Store;
import Entities.User;
import Service.ProduitStoreService;
import Service.ServiceProduit;
import Service.StoreService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jmili
 */
public class FormulaireproduitController implements Initializable {

    @FXML
    private TextField quantite;
    @FXML
    private TextField price;
    @FXML
    private TextField productname;
    @FXML
    private TextField etat;
    @FXML
    private SplitMenuButton choixCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutproduit(ActionEvent event) {
                ServiceProduit SS=new ServiceProduit();
        User u=new User();
        Categorie CT=new Categorie();
        Produit st;
        st = new Produit(productname.getText(),CT,Integer.parseInt(price.getText()),Integer.parseInt(quantite.getText()),etat.getText());
        SS.insert(st);
    }
    
}
