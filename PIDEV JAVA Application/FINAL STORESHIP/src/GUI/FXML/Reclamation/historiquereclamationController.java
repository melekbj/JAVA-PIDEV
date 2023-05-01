/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Reclamation;

import GUI.Controllers.*;
import controller.ServiceReclamation;
import controller.UserController;
import entity.Reclamation;
import entity.Util.EmailService;
import entity.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class historiquereclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tv_Users;
   
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

    
  
    @FXML
    private TableColumn<Reclamation, String> commande;
    @FXML
    private TableColumn<Reclamation, String> produit;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, Integer> etat;
    @FXML
    private TableColumn<Reclamation,String> date;
    

    
    public void showAllUsers(){
       
        User u=ClientMainController.getInstance().getUser();
        ServiceReclamation sr=new ServiceReclamation();
        ObservableList<Reclamation> reclamationList =FXCollections.observableArrayList(sr.readByUserId(u.getId()));
        

tv_Users.setItems(reclamationList);

        
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  commande.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdCommande().getId()));
     //   produit.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdProduit().getId())));
//description.setCellValueFactory(cellData -> new SimpleStringProperty("Contenu"));
//etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
commande.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdCommande().getId()).asString());
produit.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdProduit().getId())));
description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContenu()));
date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
etat.setCellValueFactory(new PropertyValueFactory<>("etat"));


        showAllUsers();
    }    

  
    
}
