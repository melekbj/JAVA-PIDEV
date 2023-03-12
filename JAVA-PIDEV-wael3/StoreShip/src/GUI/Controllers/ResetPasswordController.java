/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import static GUI.Controllers.AuthController.hashPassword;
import connexion.ConnexionSource;
import static controller.UserController.isValidEmail;
import entity.Util.EmailService;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class ResetPasswordController implements Initializable {
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private TextField txt_email_reset;
    @FXML
    private PasswordField txt_new_password_reset;
    @FXML
    private Button btn_reset;
    @FXML
    private PasswordField txt_re_password_reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void resetPassword(ActionEvent event) {
        String email = txt_email_reset.getText();
        String newPassword = txt_new_password_reset.getText();

        if (isValidEmail(email)) {
            if (checkEmailExists(email)) {
                updatePassword(email, newPassword);
            } else {
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Email address not found !");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email address !");
            alert.showAndWait();
        }
    }


   public boolean checkEmailExists(String email) {
        Connection cnx = ConnexionSource.getInstance().getCnx();
        String req = "SELECT * FROM user WHERE email = ?";

    try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // email exists in the database
            return true;
        } else {
            // email does not exist in the database
            return false;
        }
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        return false;
    }
}


 public void updatePassword(String email, String newPassword) {
    String hashedPassword = hashPassword(newPassword);
    try {
        Connection cnx = ConnexionSource.getInstance().getCnx();
        String req = "UPDATE user SET password = ? WHERE email = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, hashedPassword);
        pst.setString(2, email);
        pst.executeUpdate();

        // check if new password and confirm password match
        String confirmPass = txt_re_password_reset.getText();
        if (!newPassword.equals(confirmPass)) {
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("New password and confirm password do not match !");
            alert.showAndWait();
            return;
        }

        // send email to inform user that their password has been updated
        EmailService emailService = new EmailService();
        String subject = "Password updated successfully";
        String messageContent = "Dear [User],\n" +
                "\n" +
                "We wanted to let you know that your password for your StoreShip account has been successfully updated. Your account is now secure with your new password.\n" +
                "\n" +
                "If you did not make any changes to your password, please contact us immediately to report the issue.\n" +
                "\n" +
                "We encourage you to keep your password confidential and change it regularly to maintain the security of your account. If you have any concerns or questions about your account, please do not hesitate to contact us.\n" +
                "\n" +
                "Thank you for using StoreShip, and have a great day!\n" +
                "\n" +
                "Best regards,\n" +
                "StoreShip Team.";
        emailService.sendEmail(email, subject, messageContent);

        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Password updated successfully !");
        alert.showAndWait();
        btn_reset.getScene().getWindow().hide();
    } catch (SQLException ex) {
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Password failed to update !");
        alert.showAndWait();
    } catch (UnsupportedEncodingException ex) {
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Failed to send email !");
        alert.showAndWait();
    }
}




    
    
    
    
    
    
    
}
