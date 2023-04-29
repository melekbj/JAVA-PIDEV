/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Reclamation;

import controller.ServiceReclamation;
import controller.ServiceTypeReclamation;
import entity.Reclamation;
import entity.TypeReclamation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class StatReclamationTypeController implements Initializable {

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
     
           ServiceReclamation rs= new ServiceReclamation();
           ArrayList<Reclamation> list = (ArrayList<Reclamation>) rs.readAll();
           
           
           ServiceTypeReclamation str=new ServiceTypeReclamation();
           
         
           
         List<TypeReclamation> listcourrant=str.getstatstype();
           
        // Creating a list of PieChart.Data objects
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (TypeReclamation type:listcourrant)
        {
            pieChartData.add(new PieChart.Data(type.getTypeName(), type.getId()));
        }
               

        // Adding the PieChart.Data list to the PieChart object
        pieChart.setData(pieChartData);

        // Setting the title of the PieChart object
        pieChart.setTitle("Types Reclamations");

    }    

    }    
    

