/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controller;

import Entities.Produit;
import Service.ProduitStoreService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jmili
 */
public class ListProduitController implements Initializable {

    @FXML
    private TableView<Produit> ListP;
    @FXML
    private TableColumn<Produit, String> nameP;
    @FXML
    private TableColumn<Produit, Float> prix;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private TableColumn<Produit, Integer> etat;
    @FXML
    private TableColumn<Produit, String> categorie;
    @FXML
    private AnchorPane LisCommande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameP.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        ObservableList<Produit> Produits = FXCollections.observableArrayList(new ProduitStoreService().readById(1).getProd());
        ListP.setItems(Produits);   
    }

                 

    @FXML
    private void AddProduit(ActionEvent event) {


    }

    @FXML
    private void DeleteP(ActionEvent event) {
        
    }

    @FXML
    private void UpdateProduit(ActionEvent event) {
    }
}
    //@FXML
    /*private void handleMouseAction(MouseEvent event) {
         
    Produit per = ListP.getSelectionModel().getSelectedItem();
ProduitStoreService PS=new ProduitStoreService();
PS.deleteProduitST(per);
    
}*/

