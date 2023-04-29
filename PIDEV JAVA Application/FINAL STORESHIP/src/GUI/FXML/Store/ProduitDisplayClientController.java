/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Store;

import GUI.Controllers.Commande.ShopController;
import entity.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class ProduitDisplayClientController implements Initializable {

    @FXML
    private ImageView imageP;
    @FXML
    private TextField quantite;
    @FXML
    private Label nameP;
    @FXML
    private Label descP;
    @FXML
    private Label quantitedispo;
    @FXML
    private Label prix;
    @FXML
    private Label categorie;
    @FXML
    private Button btn_Stock;
        private Produit localproduit;

    
  /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterproduit(ActionEvent event) {
                ShopController shopController=new ShopController();
                if (quantite.getText().matches("\\d+")){
       shopController.ajouterproduitpanierlist(localproduit,Integer.parseInt(quantite.getText()));
                }
                
                else{
                                 quantite.setText("1");

                           shopController.ajouterproduitpanierlist(localproduit,Integer.parseInt(quantite.getText()));

                }
    }
    
    public void setDetail_Produit(Produit p){
       // ImageP.setImage("file:"+p.getImage());
          localproduit=p;
          System.out.println("local produit this is main "+p);
          System.out.println("this is the localproduit"+localproduit);
          nameP.setText(String.valueOf(p.getNom()));
        prix.setText(String.valueOf(p.getPrix()));
        quantitedispo.setText(String.valueOf(p.getQuantite()));
        try {
              imageP.setImage(new Image("file:///"+p.getPhoto()));
        } catch (Exception e) {
            System.out.println("error image");
        }
              

    }

    @FXML
    private void visitstore(ActionEvent event) {

        ShopController.getInstance().visitestore(localproduit);
    }
    
    public void setProduit(Produit p)
    {
        
        localproduit=p;
        System.out.println("dzakdzkadnkzafnoezaFNOEZomgorzqmngrqz"+localproduit);
          nameP.setText(String.valueOf(p.getNom()));
        prix.setText(String.valueOf(p.getPrix()));
        quantitedispo.setText(String.valueOf(p.getQuantite()));
        System.out.println("borkent 1");
        try {
                imageP.setImage(new Image("file:///"+p.getPhoto()));
        } catch (Exception e) {
            System.out.println("error image");
        }
    
        System.out.println("borken 2");
        btn_Stock.setVisible(false);
        quantite.setVisible(false);
        
    }

  
    
}
