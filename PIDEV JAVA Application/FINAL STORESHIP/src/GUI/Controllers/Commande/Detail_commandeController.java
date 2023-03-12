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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private Label etat;
    private Produit localproduit;
    @FXML
    private Button plusproduit;
    @FXML
    private Button minusproduit;
    @FXML
    private ImageView image;
    @FXML
    private Button reclamerd;
    private Detail_Commande localDetail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setDetail_Produit(Detail_Commande p){
          localproduit=p.getProduit();
          localDetail=p;
                    reclamerd.setVisible(false);

        System.out.println("Detail _ commande "+p);
          ProduitNom.setText(String.valueOf(p.getProduit().getNom()));
        prix.setText(String.valueOf(p.getPrix_total()));
        quantite.setText(String.valueOf(p.getQuantite()));
        etat.setText(String.valueOf(p.getEtat()));      
        image.setImage(new Image(p.getProduit().getPhoto()));
    }
      public void setDetail_Produit_display(Detail_Commande p){
          minusproduit.setDisable(true);
          minusproduit.setVisible(false);
          plusproduit.setDisable(true);
          plusproduit.setVisible(false);
          reclamerd.setVisible(true);
          
          System.out.println("Detail commande card   where Product === "+p);
                    localDetail=p;

             localproduit=p.getProduit();
        System.out.println("Detail _ commande "+p);
          ProduitNom.setText(String.valueOf(p.getProduit().getNom()));
        prix.setText(String.valueOf(p.getPrix_total()));
        quantite.setText(String.valueOf(p.getQuantite()));
        etat.setText(String.valueOf(p.getEtat()));   
         image.setImage(new Image(p.getProduit().getPhoto()));
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

    @FXML
    private void reclamerdetail(ActionEvent event) {
        System.out.println("sending this detail_commande "+localDetail);
        HistoriqueCommandeController.getInstance().reclamerDetail(localDetail);
    }
    
    
      
    
}
