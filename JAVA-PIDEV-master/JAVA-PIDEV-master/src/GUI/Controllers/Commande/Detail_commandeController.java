/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers.Commande;

import entity.Detail_Commande;
import entity.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class Detail_commandeController implements Initializable {

    @FXML
    private Label ProduitNom;
    @FXML
    private Label quantite;
    @FXML
    private Label prix;
    @FXML
    private Label quantite1;
    @FXML
    private Label prix1;
    @FXML
    private Label etat;
    @FXML
    private Label ProduitNom1;
    private Produit localproduit;
    @FXML
    private Button plusproduit;
    @FXML
    private Button minusproduit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setDetail_Produit(Detail_Commande p){
          localproduit=p.getProduit();
        System.out.println("Detail _ commande "+p);
          ProduitNom.setText(String.valueOf(p.getId()));
        prix.setText(String.valueOf(p.getPrix_total()));
        quantite.setText(String.valueOf(p.getQuantite()));
        etat.setText(String.valueOf(p.getEtat()));      
    }
      public void setDetail_Produit_display(Detail_Commande p){
          minusproduit.setDisable(true);
          minusproduit.setVisible(false);
          plusproduit.setDisable(true);
          plusproduit.setVisible(false);
             localproduit=p.getProduit();
        System.out.println("Detail _ commande "+p);
          ProduitNom.setText(String.valueOf(p.getId()));
        prix.setText(String.valueOf(p.getPrix_total()));
        quantite.setText(String.valueOf(p.getQuantite()));
        etat.setText(String.valueOf(p.getEtat()));   
      }
      

    @FXML
    private void addamount(ActionEvent event) {
        ShopController shopcontroller=new ShopController();
        shopcontroller.ajouterproduitpanierlist(localproduit, 1);
                }

    @FXML
    private void reduceammount(ActionEvent event) {
        ShopController shopcontroller=new ShopController();
        shopcontroller.ajouterproduitpanierlist(localproduit, -1);
    }
    
    
      
    
}
