/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Evenement;

import GUI.Controllers.ClientMainController;
import controller.Reservation_Service;
import entity.Evenement_entite;
import entity.Reservation_entite;
import entity.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.format.DateTimeFormatter;




import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class historiquereservationController implements Initializable {

    @FXML
    private TableView<Reservation_entite> tv_Users;
    @FXML
    private TableColumn<Reservation_entite  , Integer> evenement;
    @FXML
    private TableColumn<Reservation_entite, String> Description;
    @FXML
    private TableColumn<Reservation_entite, Integer> Nb_place;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           // Set the cell value factories for the columns
    //    evenement.setCellValueFactory(data -> new PropertyValueFactory<>(data.getValue().getEv().getIdEvenement()));
       // Description.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEv().getDescriptionEvenement()));
               Description.setCellValueFactory(data -> new SimpleStringProperty("test"));

        Nb_place.setCellValueFactory(new PropertyValueFactory<>("nb_places"));

        // Load the reservations into the table
        User u=ClientMainController.getInstance().getUser();
        List<Reservation_entite> reservations=new Reservation_Service().readByUser(u);
        System.out.println("List Reservation" + reservations);
        for (Reservation_entite reservation : reservations) {
            // Load the Evenement for this Reservation
            Evenement_entite evenement = reservation.getEv();

            // Set the Evenement for this Reservation in the table
            // Add the Reservation to the table
            tv_Users.getItems().add(reservation);
        }

    }    
    
 





    
}
