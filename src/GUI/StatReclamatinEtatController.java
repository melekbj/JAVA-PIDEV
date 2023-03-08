/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

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
public class StatReclamatinEtatController implements Initializable {

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
     ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
           ServiceReclamation rs= new ServiceReclamation();
           ArrayList<Reclamation> list = (ArrayList<Reclamation>) rs.readAll();
           int nbAccepted=0;
           int nbRefused=0;
           int nbPending=0;
           for(int i =0;i<list.size();i++)
           {
               if (list.get(i).getEtat().equals("pending"))
                   nbPending++;
               else if (list.get(i).getEtat().equals("accepted"))
                   nbAccepted++;
               else if (list.get(i).getEtat().equals("refused"))
                       nbRefused++;
           }
        // Creating a list of PieChart.Data objects
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("accepted", nbAccepted),
                new PieChart.Data("refused", nbRefused),
                new PieChart.Data("pending", nbPending));

        // Adding the PieChart.Data list to the PieChart object
        pieChart.setData(pieChartData);

        // Setting the title of the PieChart object
        pieChart.setTitle("Etats Reclamations");

    }    

}
