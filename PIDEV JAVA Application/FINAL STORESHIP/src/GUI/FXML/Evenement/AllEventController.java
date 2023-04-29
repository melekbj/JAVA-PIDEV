/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Evenement;

import GUI.Controllers.ClientMainController;
import connexion.ConnexionSource;
import entity.Evenement_entite;
import entity.User;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class AllEventController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private OneEventController.MyListener myListener;

    private List<Evenement_entite> Eve = new ArrayList<>();

    
    
        Connection cnx = ConnexionSource.getInstance().getCnx();
    @FXML
    private Pane oneventreservations;
    private static AllEventController instance;
    public static AllEventController getInstance(){
        return instance; 
    }
    private static User localUser;
    private List<Evenement_entite> getData() {
     List<Evenement_entite> eev = new ArrayList<>();
     Evenement_entite Eev;
     try {
            String req = "SELECT * from evenement";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {        
                Eve.add(new Evenement_entite(rs.getInt("id"),
                        rs.getDate("date_debut").toLocalDate(),
                        rs.getDate("date_fin").toLocalDate(),
                        rs.getString("image_ev"),
                        rs.getString("lieu_ev"),
                        rs.getString("titre_ev"),
                        rs.getString("desc_ev"),
                        rs.getInt("nb_max")));
                                

            }
        }catch (SQLException ex) {
            System.out.println("Error is in AllEventController SQL REQUEST");
            System.out.println(ex.getMessage());
        }
        System.out.println(eev);
    return eev;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                  User u=ClientMainController.getInstance().getUser();

        localUser=u;
        instance=this;
        Eve.addAll(getData());
        int column = 0;
        int row = 1;

         try {
            for (int i = 0; i < Eve.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneEvent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                OneEventController itemController = fxmlLoader.getController();
                    itemController.setData(Eve.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); 
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE); 
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
    }

             oneventreservations.setVisible(false);    

         }
       public void showreservation(Evenement_entite ev){
           instance.oneventreservations.getChildren().clear();
             instance.oneventreservations.setVisible(true);
             
             FXMLLoader localloader =new FXMLLoader(getClass().getResource("detailEvents.fxml"));
        try {
            Node node=localloader.load();
            DetailEventsController controler=localloader.getController();
            System.out.println("Sending out this Event +++++ "+ev);
            controler.loadData(ev);
            instance.oneventreservations.getChildren().add(node);
            
        } catch (IOException ex) {
            Logger.getLogger(AllEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }

    /*   private static User localuser;
       public void setUser(User user){
           localuser=user;
       } */
       
public User getUser(){
      return localUser;
  } 
  public void setEvenement(User user){
      localUser=user;
  }
  
  public void close(){
                   oneventreservations.setVisible(false);    
  }

}
