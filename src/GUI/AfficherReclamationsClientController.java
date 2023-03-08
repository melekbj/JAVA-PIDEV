/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.Reclamation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherReclamationsClientController implements Initializable {

    @FXML
    private TableView<Reclamation> tableaView;
    @FXML
    private TableColumn<Reclamation, String> etat;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, LocalDate> date;
    @FXML
    private TableColumn<Reclamation, Integer> idProduit;
    @FXML
    private TableColumn<Reclamation, String> type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        description.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        idProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        
        ServiceReclamation rs = new ServiceReclamation();
        ArrayList<Reclamation> list = (ArrayList<Reclamation>) rs.readByUserId(31);
        
         ObservableList<Reclamation> ez=FXCollections.observableArrayList(list);
         tableaView.setItems(ez);

    }    
    
}
