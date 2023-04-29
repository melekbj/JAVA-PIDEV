/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Store;

import GUI.Controllers.Commande.ShopController;
import entity.Store;
import controller.RatingService;
import entity.Rating;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Jmili
 */
public class StoreController implements Initializable {

    @FXML
    private ImageView imageSt;
    @FXML
    private Label Stname;
    @FXML
    private Label adresse;
    @FXML
    private Label contact;
    @FXML
    private org.controlsfx.control.Rating StoreRating;
    @FXML
    private Label avgrating;
      @FXML
    private Button submit;
    private static StoreController instance;
    
    private Store localStore=new Store();

    public static  StoreController getInstance()
    {
        return instance;
    }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance=this;
//   try { 
//    StoreService storeService = new StoreService();
//    RatingService rate=new RatingService();
//    int userid = 4; 
//    
//    // Load store information from the database
//    localStore = storeService.readById(userid);
//
//    // Populate UI elements with store information from the database
//    Stname.setText(localStore.getNameSt());
//    adresse.setText(localStore.getLocation());
//    contact.setText(localStore.getOwner().getEmail());
//    StoreRating.setRating(rate.readRating((int)localStore.getRating()));
//    avgrating.setText(String.valueOf(rate.avgrating(localStore.getId())));
//        } catch (SQLException ex) {
//            Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    @FXML
    private void updateStore(ActionEvent event) throws IOException {
               // Stage stage = (Stage) updateSt.getScene().getWindow();
      //  stage.close();
        //Stage primaryStage = new Stage();
       // FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/UpdateStore.fxml"));
        MainController LPC=new MainController();
        LPC.updateinfoStore();
//        primaryStage.setTitle("update Store");
 //       primaryStage.setScene(new Scene(root));
 //       primaryStage.show();
    }
        public void setLocalstore(Store s) {
        localStore=s;
//        try { 
//  //  StoreService storeService = new StoreService();
//    RatingService rate=new RatingService();
//   // int userid = 4; 
    
    // Load store information from the database
    //localStore = storeService.readById(userid);

    // Populate UI elements with store information from the database
            System.out.println("localsotre in store controller ====="+ localStore);
    Stname.setText(localStore.getNameSt());
    adresse.setText(localStore.getLocation());
    contact.setText(localStore.getOwner().getEmail());
            try {
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
    

    @FXML
    private void RateStore(ActionEvent event) throws SQLException {
        RatingService RS=new RatingService();
        System.out.println("rating this store"+localStore);
    //    RS.updateRating(localStore.getId(),StoreRating.getRating(),1);
    }

        @FXML
    private void ajouteProduit(ActionEvent event) throws IOException{
           MainController LPC=new MainController();
        LPC.AjoutProduit();
    }
    @FXML
    private void closestore(ActionEvent event) {
        ShopController.getInstance().closestore();
    }
}

