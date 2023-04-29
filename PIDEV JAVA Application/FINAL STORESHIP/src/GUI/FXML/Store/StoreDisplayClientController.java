/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Store;

import GUI.Controllers.ClientMainController;
import GUI.Controllers.Commande.ShopController;
import controller.RatingService;
import entity.Store;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class StoreDisplayClientController implements Initializable {

    @FXML
    private ImageView imageSt;
    @FXML
    private Label Stname;
    @FXML
    private Label adresse;
    @FXML
    private Label contact;
    @FXML
    private Rating StoreRating;
    @FXML
    private Label avgrating;
    @FXML
    private Button submit;
    @FXML
    private Button closestore;
  private static StoreDisplayClientController instance;

 
    private Store localStore=new Store();

    public static  StoreDisplayClientController getInstance()
    {
        return instance;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         instance=this;
    }    

    
     @FXML
    private void RateStore(ActionEvent event)  {
         try {
                RatingService RS=new RatingService();
        System.out.println("rating this store"+localStore);
             User ConnectedUser=ClientMainController.getInstance().getUser();
             
        RS.updateRating(localStore.getId(),StoreRating.getRating(),ConnectedUser.getId());

         } catch (Exception e) {
             System.out.println("error SQL to Submit rating");
         }
         }

    @FXML
    private void closestore(ActionEvent event) {
        
                ShopController.getInstance().closestore();

    }
     public void setLocalstore(Store s) {
        localStore=s;
    // Populate UI elements with store information from the database
    Stname.setText(localStore.getNameSt());
    adresse.setText(localStore.getLocation());
    contact.setText(localStore.getOwner().getEmail());
            try { // trying image path
                    imageSt.setImage(new Image("file:///" + localStore.getPhoto()));
            } catch (Exception e) {
                System.out.println("error image localStore");
            }
   // StoreRating.setRating(localStore.getRating());
    avgrating.setText(String.valueOf(localStore.getRating()));
//        } catch (SQLException ex) {
//            Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

        public Store getlocalStore()
        {
            return localStore;
        }
    
}
