/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.Reclamation;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminReclamationController implements Initializable {

  
    @FXML
    private Label label;
    @FXML
    private Button acceptButton;
    @FXML
    private Button refuseButton;
    @FXML
    private Button deleteButton;
    public Reclamation selectedr;
    @FXML
    private TableColumn<Reclamation,Integer> idReclamation;
    @FXML
    private TableColumn<Reclamation,Integer> IDClient;
    @FXML
    private TableColumn<Reclamation,Integer> IDCommande;
    @FXML
    private TableColumn<Reclamation,String> Etat;
    @FXML
    private TableColumn<Reclamation,LocalDate> Date;
    @FXML
    private TableColumn<Reclamation,String> Description;
    @FXML
    private TableColumn<Reclamation,Integer> IDProduit;
    @FXML
    private TableColumn<Reclamation,Integer> Type;
    @FXML
    private TableView<Reclamation> TableView;

    /**
     * Initializes the controller class.
     */
    public void displayData(){
        idReclamation.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        IDClient.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        IDCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
          Description.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        IDProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
       Type.setCellValueFactory(new PropertyValueFactory<>("type"));
       
          ServiceReclamation sr = new ServiceReclamation();
        ArrayList<Reclamation> reclamationList =  (ArrayList<Reclamation>) sr.readAll(); 
ObservableList<Reclamation> list = FXCollections.observableArrayList(reclamationList);
TableView.setItems(list);



   
   TableView.setOnMouseClicked(event -> {
    Reclamation selectedReclamation = TableView.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
        System.out.println(selectedReclamation);
        selectedr=selectedReclamation;
    }
});

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      displayData();


    }    

    @FXML
    private void acceptReclamation(ActionEvent event) {
        System.out.println(selectedr);
        ServiceReclamation sr = new ServiceReclamation();
        sr.accepter(selectedr.getIdReclamation());
        displayData();
    }

    @FXML
    private void refuseReclamation(ActionEvent event) {
          System.out.println(selectedr);
        ServiceReclamation sr = new ServiceReclamation();
        sr.refuser(selectedr.getIdReclamation());
        displayData();
    }

    @FXML
    private void deleteReclamation(ActionEvent event) {
          System.out.println(selectedr);
        ServiceReclamation sr = new ServiceReclamation();
        sr.delete(selectedr.getIdReclamation());
        displayData();
    }
    
}
