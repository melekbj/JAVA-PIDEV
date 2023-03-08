/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Reclamation;

import GUI.Controllers.ClientMainController;
import GUI.Controllers.Commande.HistoriqueCommandeController;
import controller.ServiceReclamation;
import controller.ServiceTypeReclamation;
import controller.Service_Detail_Commande;
import entity.Detail_Commande;
import entity.Produit;
import entity.Reclamation;
import entity.TypeReclamation;
import entity.User;
import entity.Util.State;
import entity.Util.TunisieMap;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


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
    private User localuser;
    private Detail_Commande localdetail;
    @FXML
    private ComboBox<String> state;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }    
   
    
    @FXML
    private void ajoutertest(ActionEvent event) {
        ServiceTypeReclamation str=new ServiceTypeReclamation();
       Reclamation re=new Reclamation(null
                ,localuser
                ,localdetail.getProduit()
                ,localdetail.getCommande()
                ,descriptionReclamation.getText()
                ,LocalDate.now()
                ,"Pending"
                ,str.readByName(state.getValue().toString()));
                
        ServiceReclamation sr = new ServiceReclamation();
        if(!descriptionReclamation.getText().equals(""))
        sr.insert(re); 
      
           
    }
    
  public void reclamerproduit(Detail_Commande p)
  { 
      ServiceTypeReclamation str=new ServiceTypeReclamation();
      List<TypeReclamation> list=str.readAll();
      for (TypeReclamation s:list)
        {
            state.getItems().add(s.getTypeName());
        }
      
      System.out.println("detailCOmmande pppppppppppppppppp"+ p);
      localuser = ClientMainController.getInstance().getUser();
  localdetail=p;
        nomProduit.setText(localdetail.getProduit().getNom());
        System.out.println("Nom Produit------------------- "+ localdetail.getProduit().getNom());
        prix.setText(Double.toString(localdetail.getProduit().getPrix()));
        System.out.println("Prix Produit-----------------"+localdetail.getProduit().getPrix());
       // categorie.setText(String.valueOf(p.getProduit().getCategorie().getNom()));
         descriptionReclamation.textProperty().addListener((observable, oldValue, newValue) -> {
        int wordCount = newValue.trim().split("\\s+").length;
        String style = wordCount < 3 ?
                       "-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);" :
                       "-fx-effect: dropshadow(three-pass-box, green, 10, 0, 0, 0);";
       descriptionReclamation.setStyle(style);
    });
  }

    @FXML
    private void cancelreclamation(ActionEvent event) {
        HistoriqueCommandeController.getInstance().closereclamation();
    }

  
  
}
