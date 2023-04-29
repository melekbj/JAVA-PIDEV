/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers.Commande;

import controller.Service_Detail_Commande;
import entity.Commande;
import entity.Detail_Commande;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class CommandeController implements Initializable {

    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label destination;
    @FXML
    private Label prix;
    @FXML
    private Button detail;
    @FXML
    private Label etat;
    private Commande commande;
    @FXML
    private Button payer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    
    
    public void setCommande(Commande c){
        commande=c;
        id.setText(String.valueOf(c.getId()));
        date.setText(String.valueOf(c.getDate()));
        if (c.getDestination()!=null)
            destination.setText(c.getDestination());
        prix.setText(String.valueOf(c.getPrix()));
        etat.setText(c.getEtat());
        if (!commande.getEtat().equals("null"))
        { payer.setDisable(true);
            etat.setStyle("-fx-background-color: green ; -fx-text-fill: white;");
        }
    }

   
     @FXML
    private void extraDetail(ActionEvent event) {
         Service_Detail_Commande sdc=new Service_Detail_Commande();
         List<Detail_Commande> list=new ArrayList<>();
         list=sdc.readallByCommande(Integer.parseInt(id.getText()));
         HistoriqueCommandeController.getInstance().populatedetails(list);
    }

    @FXML
    private void updatedestination(ActionEvent event) {
    }

    @FXML
    private void payer(ActionEvent event) {
        
        HistoriqueCommandeController.getInstance().processpayment(commande);
    }
    
}
