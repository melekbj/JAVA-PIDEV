/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import connexion.ConnexionSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class AuthController implements Initializable {

    @FXML
    private AnchorPane pane_login;
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private ComboBox type_in;
    @FXML
    private ComboBox type_up;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_pwd;
    @FXML
    private Button btn_login;
    @FXML
    private TextField txt_prenom_up;
    @FXML
    private TextField txt_nom_up;
    @FXML
    private ComboBox txt_genre_up;
    @FXML
    private TextField txt_age_up;
    @FXML
    private TextField txt_image_up;
    @FXML
    private TextField txt_phone_up;
    @FXML
    private TextField txt_email_up;
    @FXML
    private TextField txt_password_up;
    @FXML
    private TextField txt_adresse_up;
    
    Connection cnx = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

        
    
      public static boolean isValidEmail(String email) {
       String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
       return email.matches(regex);
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_up.getItems().addAll("Admin","Client","Partenaire");
        type_in.getItems().addAll("Admin","Client","Partenaire");
        txt_genre_up.getItems().addAll("Homme","Femme","Autre");
    }    

    @FXML
    private void LoginPaneShow(ActionEvent event) {
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }

    @FXML
    private void signupPaneShow(ActionEvent event) {
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }

    @FXML
    private void login(ActionEvent event) {
        cnx = ConnexionSource.getInstance().getCnx();
        String req = "SELECT * FROM user WHERE email = ? and password = ? and roles = ? ";
        
            try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, txt_email.getText());
            pst.setString(2, txt_pwd.getText());
            pst.setString(3, type_in.getValue().toString());
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login Successfully");
                
                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("PPanel.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
            }else {
                JOptionPane.showMessageDialog(null, "Invalide Username or Password ");
            }
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }

    @FXML
    private void signup(ActionEvent event) {
        
        cnx = ConnexionSource.getInstance().getCnx();
        String req = "INSERT INTO user (email,roles,password,nom,prenom,age,adresse,image,genre,phone)VALUES(?,?,?,?,?,?,?,?,?,?) ";
        
         try {
            pst = cnx.prepareStatement(req);
            
             if (isValidEmail(txt_email_up.getText())) {
             } else {
                 throw new IllegalArgumentException("Invalid email address.");
            }
            pst.setString(1, txt_email_up.getText());
            pst.setString(2, type_up.getValue().toString());
            pst.setString(3, txt_password_up.getText());
            pst.setString(4, txt_nom_up.getText());
            pst.setString(5, txt_prenom_up.getText());
            pst.setString(6, txt_age_up.getText());
            pst.setString(7, txt_adresse_up.getText());
            pst.setString(8, txt_image_up.getText());
            pst.setString(9, txt_genre_up.getValue().toString());
            pst.setString(10, txt_phone_up.getText());

            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "User Added Successfully");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }



  


   
    
}
