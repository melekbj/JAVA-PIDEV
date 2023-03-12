/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import connexion.ConnexionSource;
import controller.UserController;
import entity.Util.EmailService;
import entity.User;
import entity.Util.EmailService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entity.User;
import java.io.UnsupportedEncodingException;
import javafx.scene.control.Alert;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class ListePartnersController implements Initializable {

    @FXML
    private Button go_back_btn;
    @FXML
    private TableView<User> tv_Users;
    @FXML
    private TableColumn<User, String> colNom;
    @FXML
    private TableColumn<User, String> colPrenom;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private TableColumn<User, Integer> colAge;
    @FXML
    private TableColumn<User, String> colPhone;
    @FXML
    private TableColumn<User, String> colAdresse;
    @FXML
    private TableColumn<User, String> colGenre;
    @FXML
    private TableColumn<User, Integer> colEtat;
    @FXML
    private Button btn_aprv;
    @FXML
    private Button btn_all;
    
    private User user;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsers();
    }    
    
    
    public ObservableList<User> getUsersList(){
        
        UserController uc = new UserController();
        List<User> l = uc.ReadAllUsersByPendingState();
        ObservableList<User> userList = FXCollections.observableArrayList(l);
        return userList;
    }
    
    public ObservableList<User> getAllUsersList(){
        
        UserController uc = new UserController();
        List<User> all = uc.ReadAllUsers();
        ObservableList<User> userList = FXCollections.observableArrayList(all);
        return userList;
    }
    
      public void showUsers(){
        ObservableList<User> list = getUsersList();
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
        colAge.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        colPhone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<User, String>("adresse"));
        colGenre.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
        colEtat.setCellValueFactory(new PropertyValueFactory<User, Integer>("etat"));
        
        tv_Users.setItems(list);
    }
      
      
    @FXML
    public void showAllUsers(ActionEvent event){
        ObservableList<User> list = getAllUsersList();
        colNom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));
        colAge.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        colPhone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<User, String>("adresse"));
        colGenre.setCellValueFactory(new PropertyValueFactory<User, String>("genre"));
        colEtat.setCellValueFactory(new PropertyValueFactory<User, Integer>("etat"));
        
        tv_Users.setItems(list);
    }
    
    
    

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Admin.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        
    }

    @FXML

    private void approve(ActionEvent event) throws UnsupportedEncodingException {

        User us = tv_Users.getSelectionModel().getSelectedItem();
        UserController uc = new UserController();
        uc.approveUser(us);
        
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("User Approved Successfully");
                            alert.showAndWait(); 
        
        
        // Send an email to the user
        String recipientEmail = us.getEmail();
        String messageContent = "Dear"+" "+us.getNom()+" "+us.getPrenom()+"\n"+
                "\n" +
                "I am pleased to inform you that your account with us has been approved. " +
                "We are delighted to have you as a valued member of our community and look forward to providing you with our services.\n" +
                "\n" +
                "Please do not hesitate to contact us if you have any questions or concerns regarding your account or our services. Our team of dedicated professionals is always ready to assist you.\n" +
                "\n" +
                "Thank you for choosing us. We appreciate your trust in our platform and look forward to providing you with an exceptional user experience.\n" +
                "\n" +
                "Best regards,\n" +
                "Melek Bejaoui\n" +
                "StoreShip.tn";
        String subject = "Account Approval Notification";
        EmailService emailService = new EmailService();
        emailService.sendEmail(recipientEmail, subject, messageContent); // Handle the exception appropriately
            
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Mail envoye avec succés");
                            alert.showAndWait(); 

        showUsers();

    }

    
}
