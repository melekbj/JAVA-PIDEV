/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Reclamation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class ReclamationNavController implements Initializable {

    @FXML
    private Pane statstypepane;
    @FXML
    private Pane afficherlist;
    @FXML
    private Pane statsetatpan;
    @FXML
    private Pane ajoutertype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO$
        statstypepane.setVisible(false);
        afficherlist.setVisible(false);
        ajoutertype.setVisible(false);
        loadallinterface();
    }    

    @FXML
    private void loadstatsetat(ActionEvent event) {
         loadetat();
                 ajoutertype.setVisible(false);

        statsetatpan.setVisible(true);      
         statstypepane.setVisible(false);
        afficherlist.setVisible(false);
    }

    @FXML
    private void loadstatstype(ActionEvent event) {
                loadtype();
        ajoutertype.setVisible(false);

         statsetatpan.setVisible(false);  
         statstypepane.setVisible(true);
        afficherlist.setVisible(false);
    }

    @FXML
    private void loadaffichage(ActionEvent event) {
                afficherall();
        ajoutertype.setVisible(false);

         statsetatpan.setVisible(false);
         statstypepane.setVisible(false);
        afficherlist.setVisible(true);
    }
    
    private void loadallinterface(){
        loadetat();
        afficherall();
        loadtype();
    }
    private void loadetat(){
          statsetatpan.getChildren().clear();
          FXMLLoader historiqueloader=new FXMLLoader(getClass().getResource("statReclamatinEtat.fxml"));
         try{
        Node node=historiqueloader.load();
             StatReclamatinEtatController hitoriquecontroller=new StatReclamatinEtatController();
        statsetatpan.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
    }
    private void loadtype(){
          statstypepane.getChildren().clear();
          FXMLLoader historiqueloader=new FXMLLoader(getClass().getResource("statReclamationType.fxml"));
         try{
        Node node=historiqueloader.load();
             StatReclamationTypeController hitoriquecontroller=new StatReclamationTypeController();
        statstypepane.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
    }
    private void afficherall(){
          afficherlist.getChildren().clear();
          FXMLLoader historiqueloader=new FXMLLoader(getClass().getResource("AdminReclamation.fxml"));
         try{
        Node node=historiqueloader.load();
             AdminReclamationController hitoriquecontroller=new AdminReclamationController();
        afficherlist.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
    }

    @FXML
    private void closepanel(ActionEvent event) {
    }

    @FXML
    private void ajoutertyperec(ActionEvent event) {
        affichertypereclamation();
         ajoutertype.setVisible(true);

         statsetatpan.setVisible(false);
         statstypepane.setVisible(false);
        afficherlist.setVisible(false);
    }
    private void affichertypereclamation(){
          ajoutertype.getChildren().clear();
          FXMLLoader historiqueloader=new FXMLLoader(getClass().getResource("AjouterTypeReclamation.fxml"));
         try{
        Node node=historiqueloader.load();
             AjouterTypeReclamationController hitoriquecontroller=new AjouterTypeReclamationController();
             
        ajoutertype.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
    }
    
}
