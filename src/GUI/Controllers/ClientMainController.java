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
public class ClientMainController implements Initializable {

    @FXML
    private Pane pane_nav;
    @FXML
    private Pane pane_con;
    
    private static User localUser;
    
    private static ClientMainController instance;
   
    
    public static ClientMainController getInstance(){
        return instance;
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         instance=this;
        try {
            FXMLLoader nav = new FXMLLoader((getClass().getResource("/GUI/FXML/NavC.fxml")));
            Node node = nav.load();
            pane_nav.getChildren().add(node);
            
        } catch (IOException ex) {
            Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
          FXMLLoader contenu = new FXMLLoader((getClass().getResource("/GUI/FXML/ClientContenu.fxml")));
          Node node1 = contenu.load();
          ClientContenuController contenucontroller=contenu.getController();
        //  contenucontroller.setPane1();
          
            pane_con.getChildren().add(node1);
        } catch (IOException ex) {
            Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
    }   
    
    public void setUser(User u) {
         localUser = u;
         
    }
    public User getUser() {
        return localUser;
    }
    
    
    
    
    

}
