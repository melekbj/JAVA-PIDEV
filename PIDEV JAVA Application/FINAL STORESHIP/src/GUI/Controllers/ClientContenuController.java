/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import GUI.Controllers.Commande.HistoriqueCommandeController;
import GUI.Controllers.Commande.ShopController;
import GUI.FXML.Evenement.AllEventController;
import GUI.FXML.Reclamation.historiquereclamationController;
import controller.UserController;
import entity.User;
import entity.Util.QRCodeGenerator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class ClientContenuController implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    
  
    
    @FXML
    private Pane pane4;
    @FXML
    private ImageView re_img;
     @FXML
    private Label lab_name;
   
     
             Alert alert = new Alert(Alert.AlertType.INFORMATION);

    
    
   
    private static ClientContenuController instance;
    
    
    public static ClientContenuController getInstance(){
      
        return instance;
    }
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_age;
    @FXML
    private TextField tf_adr;
    @FXML
    private TextField tf_email;
    @FXML
    private Button sub_btn;
    @FXML
    private ImageView qr_img;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane31;
    @FXML
    private TextField tf_num;
   
    
    
    
    public ObservableList<User> getUsersList(){
        
        UserController uc = new UserController();
        List<User> l = uc.ReadAllUsers();
        ObservableList<User> userList = FXCollections.observableArrayList(l);
        return userList;
    }
    
    
    
    
    
    private String data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance=this;
                data = 
              "Full Name:"+ClientMainController.getInstance().getUser().getNom()+" "+
                ClientMainController.getInstance().getUser().getPrenom()+"\n" +
              "TEL:"+ClientMainController.getInstance().getUser().getPhone()+"\n"+
              "EMAIL:"+ClientMainController.getInstance().getUser().getEmail();

     
        pane4.setVisible(false);
   
        try {
            System.out.println("line 128 ClientCOntenuController");
                    re_img.setImage(new Image("file:///"+ ClientMainController.getInstance().getUser().getImage()));

        } catch (Exception e) {
            System.out.println("error image ");
        }
            
        lab_name.setText(ClientMainController.getInstance().getUser().getNom()
                 +" "+ClientMainController.getInstance().getUser().getPrenom());
        
        tf_nom.setText(ClientMainController.getInstance().getUser().getNom());
        tf_prenom.setText(ClientMainController.getInstance().getUser().getPrenom());
        tf_age.setText(String.valueOf(ClientMainController.getInstance().getUser().getAge()));
        tf_adr.setText(ClientMainController.getInstance().getUser().getAdresse());
        tf_email.setText(ClientMainController.getInstance().getUser().getEmail());
        tf_email.setEditable(false);
        tf_num.setText(ClientMainController.getInstance().getUser().getPhone());
        tf_num.setEditable(false);
        
        
        loadshop();
        loadhistorique();
        loadevenement();
        
    }  
    
    public void   setPane1(){
        instance.pane1.setVisible(true);
                instance.pane2.setVisible(false);
        instance.pane3.setVisible(false);
           instance.pane4.setVisible(false);
        instance.pane5.setVisible(false);
        
        openshop();

    }
      public void setPane2(){
        instance.pane1.setVisible(false);
        instance.pane2.setVisible(true);
        instance.pane3.setVisible(false);
        instance.pane4.setVisible(false);
        instance.pane5.setVisible(false);

    }
        public void   setPane3(){
        instance.pane1.setVisible(false);
        instance.pane2.setVisible(false);
        instance.pane3.setVisible(true);
           instance.pane4.setVisible(false);
        instance.pane5.setVisible(false);
      
        // Generate the QR code image using the QRCodeGenerator utility class


    }
        public void   setPane4(){
        instance.pane1.setVisible(false);
        instance.pane2.setVisible(false);
        instance.pane3.setVisible(false);
           instance.pane4.setVisible(true);
        instance.pane5.setVisible(false);
loadhistoriquereclamation();
    }
        public void   setPane5(){
        instance.pane1.setVisible(false);
        instance.pane2.setVisible(false);
        instance.pane3.setVisible(false);
           instance.pane4.setVisible(false);
        instance.pane5.setVisible(true);

    }
        
        
        public void setPane31(){
        instance.pane31.setVisible(true);
        

    }

    @FXML
    private void load31(ActionEvent event) {
        ClientContenuController c = new ClientContenuController();
        c.setPane31();
    }

    @FXML
    private void updateProfile(ActionEvent event) throws IOException {
        
         UserController uc =  new UserController();
        
        User u=ClientMainController.getInstance().getUser();
        u.setNom(tf_nom.getText());
        u.setPrenom(tf_prenom.getText());
        u.setAge(Integer.parseInt(tf_age.getText()));
        u.setAdresse(tf_adr.getText());
        
        uc.UpdateUser(u);
        alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("user updated successfully");
            alert.showAndWait();
        ClientMainController.getInstance().setUser(u);
        
        sub_btn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ClientMain.fxml"));
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show();
    }
    
    
    
    
     private void openshop(){
       ShopController shopcontroller=new ShopController();
       shopcontroller.populateshop();
    }
    private void loadshop(){
                pane1.getChildren().clear();

        FXMLLoader shoploader=new FXMLLoader(getClass().getResource("/GUI/FXML/Commande/Shop.fxml"));
         try{
        Node node=shoploader.load();
             ShopController shopcontroller=new ShopController();
        pane1.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
    }
    private void loadhistorique(){
        pane2.getChildren().clear();
          FXMLLoader historiqueloader=new FXMLLoader(getClass().getResource("/GUI/FXML/Commande/HistoriqueCommande.fxml"));
         try{
        Node node=historiqueloader.load();
             HistoriqueCommandeController hitoriquecontroller=new HistoriqueCommandeController();
        pane2.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
    }
    public void loadevenement()
    {
            pane5.getChildren().clear();
          FXMLLoader evenloader=new FXMLLoader(getClass().getResource("/GUI/FXML/Evenement/AllEvent.fxml"));
         try{
        Node node=evenloader.load();
             AllEventController evencontroller=new AllEventController();
        pane5.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
         
         
    }
    public void loadhistoriquereclamation(){
        
        instance.pane4.getChildren().clear();
          FXMLLoader evenloader=new FXMLLoader(getClass().getResource("/GUI/FXML/Reclamation/historiquereclamation.fxml"));
         try{
             Node node=evenloader.load();
             historiquereclamationController evencontroller=new historiquereclamationController();
        instance.pane4.getChildren().add(node);
         }
         catch (IOException ex) {
                System.out.println(ex);
            }
    }
}
