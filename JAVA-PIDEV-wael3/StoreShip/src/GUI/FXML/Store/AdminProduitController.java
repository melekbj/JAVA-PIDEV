/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Store;

import GUI.FXML.Reclamation.*;
import controller.ServiceProduit;
import controller.ServiceReclamation;
import entity.Produit;
import entity.Reclamation;
import entity.User;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminProduitController implements Initializable {

  
    @FXML
    private Label label;
    @FXML
    private Button acceptButton;
    @FXML
    private Button refuseButton;
    public Reclamation selectedr;
    private TableColumn<Produit,Integer> IDClient;
    private TableColumn<Produit,String> Etat;
    private TableColumn<Produit,LocalDate> Date;
    private TableColumn<Produit,String> Description;
    private TableColumn<Produit,Integer> IDProduit;
    private TableColumn<Produit,Integer> Type;
    @FXML
    private TableView<Produit> TableView;
        private static User localadmin;
    @FXML
    private TableColumn<Produit, String> produitnon;
    @FXML
    private TableColumn<Produit, Integer> etat;
    @FXML
    private TableColumn<Produit, Double> prix;
    @FXML
    private TableColumn<Produit, Integer> quantite;
    @FXML
    private TableColumn<Produit, String> Categorie;
    @FXML
    private Button go_back_btn;

    /**
     * Initializes the controller class.
     */
    public void displayData(){
        produitnon.setCellValueFactory(new PropertyValueFactory<>("nom"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
       Categorie.setCellValueFactory(new PropertyValueFactory<>("type"));
       
          ServiceProduit sr = new ServiceProduit();
        List<Produit> reclamationList =   sr.readAll(); 
ObservableList<Produit> list = FXCollections.observableArrayList(reclamationList);
TableView.setItems(list);



   
  

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      displayData();


    }    

    @FXML
    private void acceptproduit(ActionEvent event)  throws UnsupportedEncodingException {
          Produit us = TableView.getSelectionModel().getSelectedItem();
         
        ServiceProduit sr = new ServiceProduit();
        sr.accepter(us);
        displayData();
    }

    @FXML
    private void refuseproduit(ActionEvent event) throws UnsupportedEncodingException {
         Produit us = TableView.getSelectionModel().getSelectedItem();
         
        ServiceProduit sr = new ServiceProduit();
        sr.refuser(us)   ;
        displayData();
    }

  
    public void setUser(User user){
        localadmin=user;
    }
    public User getUser() {
        return localadmin;
    }
    
     @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Admin.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//        
        
    }
}
