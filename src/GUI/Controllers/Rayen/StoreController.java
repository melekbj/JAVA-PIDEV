/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers.Rayen;

import entity.Produit;
import entity.Store;
import controller.ProduitStoreService;
import controller.RatingService;
import controller.ServiceProduit;
import controller.StoreService;
import controller.UserController;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

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
private Store localStore=new Store();
    @FXML
    private Rating StoreRating;
    @FXML
    private Label avgrating;
    @FXML
    private Button submit;
    private static StoreController instance;
    
    
    public static  StoreController getInstance()
    {
        return instance;
    }
    @FXML
    private Button AddProduit;
    @FXML
    private Button updateSt;
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
    Stname.setText(localStore.getNameSt());
    adresse.setText(localStore.getLocation());
    contact.setText(localStore.getOwner().getEmail());
    imageSt.setImage(new Image("file:" + localStore.getPhoto()));
    StoreRating.setRating(localStore.getRating());
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
        RS.updateRating(localStore.getId(),StoreRating.getRating(),1);
    }


    @FXML
    private void ajouteProduit(ActionEvent event) throws IOException{
           MainController LPC=new MainController();
        LPC.AjoutProduit();
    }
}

