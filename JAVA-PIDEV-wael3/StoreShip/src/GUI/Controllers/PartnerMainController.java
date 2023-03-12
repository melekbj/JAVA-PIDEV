/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class PartnerMainController implements Initializable {

    private static PartnerMainController instance;

    public static PartnerMainController getInstance(){
        return instance;
    }
    
    @FXML
    private Pane pane_nav;
    @FXML
    private Pane pane_content;

    private static User locUser;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         instance=this;
        try {
            FXMLLoader nav = new FXMLLoader((getClass().getResource("/GUI/FXML/NavP.fxml")));
            Node node = nav.load();
            pane_nav.getChildren().add(node);
            
        } catch (IOException ex) {
            Logger.getLogger(PartnerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
          FXMLLoader contenu = new FXMLLoader((getClass().getResource("/GUI/FXML/PartnerContenu.fxml")));
          Node node1 = contenu.load();
          
            pane_content.getChildren().add(node1);
        } catch (IOException ex) {
            Logger.getLogger(PartnerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }   
    
   
    
    public void setUser(User u) {
        locUser = u;
    }
    public User getUser() {
        return locUser;
    } 
    
    
    
    
    
}
