/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import controller.Evenement_Service;
import controller.Reservation_Service;
import entity.Evenement_entite;
import entity.Reservation_entite;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class PPanel2Controller implements Initializable {

    @FXML
    private TextField btnidres;
    @FXML
    private TextField btnnbr_place;
    @FXML
    private TableView<Reservation_entite> tableRes;
    @FXML
    private TableColumn<Reservation_entite, Integer> id_resCol;
    @FXML
    private TableColumn<Reservation_entite, Integer> nbr_placeCol;
    @FXML
    private TableColumn<Reservation_entite, LocalDate> dateCol;
    @FXML
    private Button btnReadAll;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          id_resCol .setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        nbr_placeCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));

    }    

    @FXML
    private void ReadAll(ActionEvent event) {
         
          List<Reservation_entite>  ReservationList=new ArrayList<>();
          Reservation_Service es=new Reservation_Service();
          ReservationList=es.readAll();
           

        
            ObservableList<Reservation_entite> ez=FXCollections.observableArrayList(ReservationList);
            System.out.println(ez);
               tableRes.setItems(ez);
               
              
            }

    @FXML
    private void Insert(ActionEvent event) {
     
        
         System.out.println("start inser button");
        Reservation_Service rs=new Reservation_Service();
        System.out.println("service created");
        Reservation_entite re=new Reservation_entite(0,
                Integer.parseInt(btnnbr_place.getText()));
                System.out.println("entity created");
    
        rs.insert(re);
        JOptionPane.showMessageDialog(null, "Reservation added");
    }

    @FXML
    private void Update(ActionEvent event) {
        
         System.out.println(btnnbr_place.getClass());
        System.out.println("starting");
       Reservation_Service rs=new Reservation_Service();
       Reservation_entite re=new Reservation_entite(Integer.parseInt(btnidres.getText()),
              
                Integer.parseInt(btnnbr_place.getText()));
        System.out.println("test");
        rs.updatenbrplace(re);
           System.out.println("test2");
           JOptionPane.showMessageDialog(null, "Reservation updated");
        
        
    }

    @FXML
    private void Delete(ActionEvent event) {
        
        int n=Integer.parseInt(btnidres.getText());
        Reservation_Service rs=new Reservation_Service();
        Reservation_entite re=new Reservation_entite(Integer.parseInt(btnidres.getText()));
        rs.delete(re);
        JOptionPane.showMessageDialog(null, "Reservation  deleted");
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
       
         
    Reservation_entite per = tableRes.getSelectionModel().getSelectedItem();
 
    btnidres.setText(String.valueOf(per.getId_reservation()));
     btnnbr_place.setText(String.valueOf(per.getNbr_place()));
        
    }
    }

    
    
    
    
    

   

    

   
    

