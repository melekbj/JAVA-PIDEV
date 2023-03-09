/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import controller.UserController;
import entity.Util.EmailService;
import entity.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class ListeAllUsersController implements Initializable {

    @FXML
    private Label go_back_btn;
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
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

    
    private User user;
    @FXML
    private Button btn_block;
    
    public ObservableList<User> getAllUsersList(){
        
        UserController uc = new UserController();
        List<User> all = uc.ReadAllUsers();
        ObservableList<User> userList = FXCollections.observableArrayList(all);
        return userList;
    }
    
    public void showAllUsers(){
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAllUsers();
    }    

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
    private void block(ActionEvent event) throws UnsupportedEncodingException {

        User us = tv_Users.getSelectionModel().getSelectedItem();
        UserController uc = new UserController();
        uc.blockUser(us);
        
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("User blocked");
                            alert.showAndWait(); 
        
        
        // Send an email to the user
        String recipientEmail = us.getEmail();
        String messageContent = "Dear"+" "+us.getNom()+" "+us.getPrenom()+"\n"+
                "We regret to inform you that your account has been blocked from accessing our website. Our records indicate that there have been repeated violations of our terms of service and community guidelines. As a result, we have had to take this necessary action to ensure the safety and well-being of our community.\n" +
                "\n" +
                "Please note that this decision is final, and your account will remain blocked until further notice. If you believe that this has been done in error, or if you would like to appeal this decision, please contact our support team at melekbejaoui29@gmail.com .\n" +
                "\n" +
                "We appreciate your understanding in this matter.\n" +
                "\n" +
                "Sincerely,\n" +
                "StoreShip";
        String subject = "Account Restricted";
        EmailService emailService = new EmailService();
        emailService.sendEmail(recipientEmail, subject, messageContent); // Handle the exception appropriately
            
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Mail envoye avec succés");
                            alert.showAndWait(); 

        showAllUsers();

    }

    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Admin.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    
}
