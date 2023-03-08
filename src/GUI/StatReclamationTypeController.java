/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.sun.xml.internal.bind.v2.TODO;
import entite.Reclamation;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import service.ServiceReclamation;

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
           int nbRecProduit=0;
           int nbRecEvenement=0;
           for(int i =0;i<list.size();i++)
           {
               if (list.get(i).getType().equals("evenement"))
                   nbRecEvenement++;
               else if (list.get(i).getType().equals("produit"))
                   nbRecProduit++;
              
           }
        // Creating a list of PieChart.Data objects
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("produit", nbRecEvenement),
                new PieChart.Data("evenement", nbRecProduit));

        // Adding the PieChart.Data list to the PieChart object
        pieChart.setData(pieChartData);

        // Setting the title of the PieChart object
        pieChart.setTitle("Types Reclamations");

    }    

    }    
    

