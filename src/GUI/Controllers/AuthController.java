/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import org.mindrot.jbcrypt.BCrypt;
import javafx.scene.web.WebEngine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import connexion.ConnexionSource;
import controller.UserController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.User;
import entity.Util.State;
import entity.Util.TunisieMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
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
    private FontAwesomeIconView btn_show_hide;
    @FXML
    private PasswordField txt_visible_pwd;
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
    private Button txt_image_up;
    @FXML
    private TextField txt_phone_up;
    @FXML
    private TextField txt_email_up;
    @FXML
    private PasswordField  txt_password_up;
    @FXML
    private PasswordField  txt_cpassword_up;
    @FXML
    private TextField txt_adresse_up;
    @FXML
    private ImageView ImageView;
    @FXML
    private WebView webView;
    @FXML
    private Button btn_signup;
    @FXML
    private ComboBox state;
    
    
    Connection cnx = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private Stage primaryStage;
    private File selectedFile = null;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    

    
    public static boolean isValidEmail(String email) 
    {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
      
    public static boolean containsOnlyNumbers(String str) 
    {
        return str.matches("\\d+");
    }
    
    public static boolean PasswordStrength(String str) 
    {
        return str.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}$");
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
     
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
    




    
    
    
    

    
    
    
    
    private final String SITE_KEY = "6LcVcsUkAAAAAPQI826VNCfpZKpmIfc0kGEPEmTT";
    private final String SECRET_KEY = "6LcVcsUkAAAAAAOtd7vI5ZFL67rQK2o2hcjBlC6X";        
    private TextField txt_recaptcha_response;
    @FXML
    private void login(ActionEvent event) {
        cnx = ConnexionSource.getInstance().getCnx();
        String req = "SELECT * FROM user WHERE email = ? and roles = ? ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, txt_email.getText());
            pst.setString(2, type_in.getValue().toString());
            rs = pst.executeQuery();
            if (rs.next()) {
                
               User u =new User(rs.getInt("id"), rs.getString("email"),rs.getString("roles"), 
             rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),
                 rs.getString("adresse"),rs.getString("image"),
                   rs.getString("genre"),rs.getString("phone"),rs.getInt("etat"));
               
                // User exists, check password
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(txt_pwd.getText(), hashedPassword)) {
                    // Passwords match, login successful

                    if (type_in.getValue().equals("Admin")) {
                        switch (rs.getInt("etat")){
                            case 1:
                                alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("Account blocked.");
                                alert.showAndWait();
                                break;
                            default:
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Login Successfully");
                            alert.showAndWait(); 

                            btn_login.getScene().getWindow().hide();
                            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Admin.fxml"));
                            Stage mainStage = new Stage();
                            Scene scene = new Scene(root);
                            mainStage.setScene(scene);
                            mainStage.show();
                        }
                    }else if(type_in.getValue().equals("Partenaire")) {
                        switch (rs.getInt("etat")) {
                            case 0:
                                alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("Login Successfully");
                                alert.showAndWait();
                                btn_login.getScene().getWindow().hide();
                                
                                FXMLLoader partload=new FXMLLoader(getClass().getResource("/GUI/FXML/PartnerMain.fxml"));
                                PartnerMainController cm = new PartnerMainController();
                                cm.setUser(u);
                                Parent root = partload.load();
                               
                                Stage mainStage = new Stage();
                                Scene scene = new Scene(root);
                                mainStage.setScene(scene);
                                mainStage.show();
                                break;
                            case 1:
                                alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("Account blocked.");
                                alert.showAndWait();
                                break;
                            default:
                                alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("Thank you for signing up!\n Your account is currently pending admin approval.\n"
                                        + " Please check your email for further instructions."); 
                                alert.showAndWait();
                                break;
                        }


                    }else{
                        switch (rs.getInt("etat")){
                            case 1:
                                alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("Account blocked.");
                                alert.showAndWait();
                                break;
                            default:
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Login Successfully");
                            alert.showAndWait(); 

                        btn_login.getScene().getWindow().hide();
                        FXMLLoader clientmain=new FXMLLoader(getClass().getResource("/GUI/FXML/ClientMain.fxml"));
                            ClientMainController cm = new ClientMainController();
                            cm.setUser(u);
                        Parent root = clientmain.load();
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show();
                        }}
                } else {
                    // Passwords do not match
                     alert.setTitle("Warning!");
                     alert.setHeaderText(null);
                     alert.setContentText("Invalid Password");
                     alert.showAndWait(); 
                }
            } else {
                // User does not exist
                            alert.setTitle("Warning!");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid Email or Role");
                            alert.showAndWait(); 
            }
        } catch(Exception ex) {
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Oops! It looks like you forgot to fill in a required field");
                            alert.showAndWait(); 
        }
    }
    
      private boolean isEmailAlreadyExists(String email) throws SQLException {
    boolean emailExists = false;
       cnx = ConnexionSource.getInstance().getCnx();
       String req = "SELECT COUNT(*) FROM user WHERE email = ?";
         pst = cnx.prepareStatement(req);
            pst.setString(1, email);
            rs = pst.executeQuery();
        if (rs.next()) {
           
            int count = rs.getInt(1);
            if (count > 0) {
                emailExists = true;
            }
        }
        
    return emailExists;
}
    
    @FXML
    private void signup(ActionEvent event) throws IOException{
        try {
            
            if (txt_password_up.getText().isEmpty()) {
                throw new IllegalArgumentException("Password is required.");
            }
//            if (!PasswordStrength(txt_password_up.getText())) {
//                throw new IllegalArgumentException("Password must be at least 8 characters long and contain at least one uppercase letter,"
//                        + "one lowercase letter, one digit,\n and one special character.");
//            }
            if (txt_cpassword_up.getText().isEmpty()) {
                throw new IllegalArgumentException("Confirm password is required.");
            }
            if (!txt_password_up.getText().equals(txt_cpassword_up.getText())) {
                throw new IllegalArgumentException("Passwords do not match.");
            }
            
            if (txt_email_up.getText().isEmpty()) {
                throw new IllegalArgumentException("Email is required.");
            }
            if (!isValidEmail(txt_email_up.getText())) {
                throw new IllegalArgumentException("Invalid email address.");
            }
            if (txt_phone_up.getText().isEmpty()) {
                throw new IllegalArgumentException("Phone is required.");
            }
            if (!containsOnlyNumbers(txt_phone_up.getText())) {
                throw new IllegalArgumentException("Phone must be a number.");
            }
            if (txt_age_up.getText().isEmpty()) {
                throw new IllegalArgumentException("Age is required.");
            }
            if (!containsOnlyNumbers(txt_age_up.getText())) {
                throw new IllegalArgumentException("Age must be a number.");
            }
              // Check if the email address already exists
            if (isEmailAlreadyExists(txt_email_up.getText())) {
            
                throw new IllegalArgumentException("Email address already exists.");
            }
            
            
            

            User u = new User(txt_email_up.getText(), type_up.getValue().toString(),
                    hashPassword(txt_password_up.getText()), txt_nom_up.getText(), txt_prenom_up.getText(),
                    Integer.parseInt(txt_age_up.getText()), txt_adresse_up.getText()+" "+state.getValue().toString(), selectedFile.getAbsolutePath(),
                    txt_genre_up.getValue().toString(), txt_phone_up.getText());

            
            UserController uc = new UserController();

            uc.AddUser(u);
 
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Account Created Successfully");
            alert.showAndWait();
            
             btn_signup.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Auth.fxml"));
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show();

        } catch (IllegalArgumentException ex) {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private boolean validateRecaptcha(String response) {
        try {
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
    
            String params = "secret=" + SECRET_KEY + "&response=" + response;
    
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(params);
            out.flush();
            out.close();
    
            int responseCode = conn.getResponseCode();
    
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
    
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();
    
            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();
    
            JSONObject jsonObject = new JSONObject(responseBuffer.toString());
    
            return jsonObject.getBoolean("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_up.getItems().addAll("Client","Partenaire","Admin");
        type_in.getItems().addAll("Admin","Client","Partenaire");
        txt_genre_up.getItems().addAll("Homme","Femme","Autre");
        
        String html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "  <head>\n"
                + "    <title>reCAPTCHA</title>\n"
                + "    <script src='https://www.google.com/recaptcha/api.js'></script>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <form action='?' method='POST'>\n"
                + "      <div class='g-recaptcha' data-sitekey='" + SITE_KEY + "'></div>\n"
                + "      <br/>\n"
                + "      <input type='submit' value='Submit'>\n"
                + "    </form>\n"
                + "  </body>\n"
                + "</html>";
//        webView.getEngine().loadContent(html);
        
         // Add listener to update TextField value with PasswordField value
         txt_pwd.setVisible(false);
        txt_visible_pwd.textProperty().addListener((observable, oldValue, newValue) -> {
            txt_pwd.setText(newValue);
        });
        
         for (State s:TunisieMap.getInstance().getAdjacencyList())
        {
            state.getItems().add(s.getKey());
        }
        
        
    }

    @FXML
    private void resetPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ResetPassword.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
      
    
    @FXML
    private void browse(ActionEvent event) 
    {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload an image");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            selectedFile = fileChooser.showOpenDialog(primaryStage);    
            if (selectedFile != null) 
            {
                txt_image_up.setText(selectedFile.getName());
                ImageView.setImage(new Image("file:" + selectedFile));

            }else{
                txt_image_up.setText(selectedFile.getName());
            }
    }

   
   
    
    @FXML
    private void showHide(MouseEvent event) {

    boolean isPasswordFieldVisible = txt_visible_pwd.isVisible();
    

    // Toggle visibility of PasswordField and TextField
    txt_visible_pwd.setVisible(!isPasswordFieldVisible);
    txt_pwd.setVisible(isPasswordFieldVisible);

    // Update button icon
    btn_show_hide.setIcon(isPasswordFieldVisible ? FontAwesomeIcon.EYE : FontAwesomeIcon.EYE_SLASH);

    }

    
  

   

   
    
    
    
    
    
    
    
}