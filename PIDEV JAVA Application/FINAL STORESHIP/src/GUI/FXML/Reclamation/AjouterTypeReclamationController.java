/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Reclamation;

import controller.ServiceTypeReclamation;
import entity.Reclamation;
import entity.TypeReclamation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class AjouterTypeReclamationController implements Initializable {

    @FXML
    private Button inserttype;
    @FXML
    private TextField typerecl_textfield;
    @FXML
    private TableColumn<TypeReclamation, String> type;
    @FXML
    private TableColumn<TypeReclamation, Integer> id;
    @FXML
    private TableView<TypeReclamation> tabletype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          type.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        loadtypeinformations();
    }    

    @FXML
    private void insertingtype(ActionEvent event) {
        ServiceTypeReclamation str=new ServiceTypeReclamation();
        str.insert(new TypeReclamation(typerecl_textfield.getText()));
        loadtypeinformations();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                              alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("Insertion Terminée."); 
                                alert.showAndWait();
    }

    public void loadtypeinformations(){
        ServiceTypeReclamation str=new ServiceTypeReclamation();
        List<TypeReclamation> listcourrant=str.getstatstype();
        System.out.println("listcourrant===="+listcourrant);
     ObservableList<TypeReclamation> list = FXCollections.observableArrayList(listcourrant);
        tabletype.setItems(list);
         

        
    }
    
    public void close(){
        
    }
}
