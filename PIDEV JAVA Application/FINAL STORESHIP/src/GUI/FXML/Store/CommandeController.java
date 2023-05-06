/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Store;

import controller.Service_Detail_Commande;
import entity.Detail_Commande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Jmili
 */
public class CommandeController implements Initializable {

    @FXML
    private Label prix;
    @FXML
    private Label usr;
    @FXML
    private Label destination;
    @FXML
    private Label quantite;
    @FXML
    private Label Pname;
    @FXML
    private Button update;
    @FXML
    private Label etat;
    @FXML
    private ComboBox<String> combo;
    private Detail_Commande localDetail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO         combo.getItems().addAll("Pending", "Progress", "Completed");
        combo.getItems().addAll("Pending", "Progress", "Completed");

    }

    @FXML
    private void updateDetail_Commande(ActionEvent event) {
        Service_Detail_Commande sdc=new Service_Detail_Commande();
        localDetail.setEtat(combo.getSelectionModel().getSelectedItem());
        sdc.updateEtat(localDetail);
        setDetail_Commandes(localDetail);
    }

    public void setDetail_Commandes(Detail_Commande detail_Commande) {
        localDetail=detail_Commande;

        // Set the font of the labels to Arial
        
        prix.setFont(Font.font("Arial"));
        usr.setFont(Font.font("Arial"));
        destination.setFont(Font.font("Arial"));
        quantite.setFont(Font.font("Arial"));
        Pname.setFont(Font.font("Arial"));
        etat.setFont(Font.font("Arial"));

        // Loop through the List<Detail_Commande> and set up the labels
        // Set the text for the labels
        prix.setText(String.valueOf(detail_Commande.getPrix_total()));
        usr.setText(detail_Commande.getCommande().getUser().getNom());
        destination.setText(detail_Commande.getCommande().getDestination());
        quantite.setText(String.valueOf(detail_Commande.getQuantite()));
        Pname.setText(detail_Commande.getProduit().getNom());
        etat.setText(String.valueOf(detail_Commande.getEtat()));
        if (detail_Commande.getEtat().equals("Completed") || detail_Commande.getEtat().equals("Canceled")) {
            // Set the initial value of the ComboBox
            combo.setVisible(false);
            update.setVisible(false);
        } else {
            combo.setValue(detail_Commande.getEtat());

        }
        // Set the style of the etat label based on the etat value
        switch (detail_Commande.getEtat()) {
            case "Canceled":
                etat.setStyle("-fx-background-color: #dc3545; -fx-text-fill: #fff;");
                break;
            case "Pending":
                etat.setStyle("-fx-background-color: #6c757d; -fx-text-fill: #fff;");
                break;
            case "Progress":
                etat.setStyle("-fx-background-color: #007bff; -fx-text-fill: #fff;");
                break;
            case "Completed":
                etat.setStyle("-fx-background-color: #28a745; -fx-text-fill: #fff;");
                break;
            default:
                break;
        }

    }
}
