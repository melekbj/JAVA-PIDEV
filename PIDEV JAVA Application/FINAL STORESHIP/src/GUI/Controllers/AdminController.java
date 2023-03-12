/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class AdminController implements Initializable {

    @FXML
    private Button listeU;
    private Button listeC;
    @FXML
    private Button go_back_btn;
    @FXML
    private Label usrlog;
    @FXML
    private Button listAll;
    @FXML
    private Button listAll1;
    @FXML
    private Button listAll11;
    @FXML
    private Button listAll1111;

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        usrlog.setText(getUser().getNom());
    }    

    @FXML
    private void listeUsers(ActionEvent event) throws IOException {
        Stage stage = (Stage) listeU.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ListePartners.fxml"));
        primaryStage.setTitle("liset users");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

//    private void listeComms(ActionEvent event) throws IOException {
//        
//        Stage stage = (Stage) listeC.getScene().getWindow();
//        stage.close();
//        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ListeCommandes.fxml"));
//        primaryStage.setTitle("liset commandes");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Auth.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void listeAllU(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) listAll.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ListeAllUsers.fxml"));
        primaryStage.setTitle("liset commandes");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private static User localUser;
    public void setUser(User u) {
         localUser = u;
         
    }
    public User getUser() {
        return localUser;
    }

    @FXML
    private void GestionEvenement(ActionEvent event)throws IOException {
         Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Evenement/PPanel.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void GestionReclamation(ActionEvent event) throws IOException {
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Reclamation/reclamationNav.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void gestionProduit(ActionEvent event)throws IOException {
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Store/AdminProduit.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void gestioncategorie(ActionEvent event)throws IOException {
         Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Store/PPanel2.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    

    
}
