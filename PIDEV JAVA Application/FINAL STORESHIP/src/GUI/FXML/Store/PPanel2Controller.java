/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FXML.Store;

import controller.ServiceCategorie;
import controller.ServiceProduit;

import entity.Categorie;
import entity.Produit;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SAFAA
 */
public class PPanel2Controller implements Initializable {

    @FXML
    private Label fxnom;
    @FXML
    private Label fxdescription;
    private TextField txtid;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdescription;
    @FXML
    private TableView<Categorie> tablev;
    @FXML
    private TableColumn<Categorie, String> nomcolumn;
    @FXML
    private TableColumn<Categorie, String> columndescription;
    @FXML
    private Button btninsert;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnreadall;
    @FXML
    private Button search;
    @FXML
    private TextField txtsearch;
    ObservableList<Categorie> CategorieListSearch;
    private static List<Categorie> CategorieList;
    private Categorie per;
    @FXML
    private Label fxnom1;
    @FXML
    private Button go_back_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columndescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        readall();
    }    

    @FXML
    private void insert(ActionEvent event) {
        
             if (txtnom.getText().isEmpty() || txtdescription.getText().isEmpty() ) 
             {
                  String titre="Error";
                  
String message = "required fields are empty";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));
    }
           
        /* else if ( txtnom.getText().matches(".*[0-9].*")|| txtnom.getText().matches(".*[%-@-.-/-!-;-,-_].*") ||
               txtetat.getText().matches(".*[0-9].*")||txtetat.getText().matches(".*[%-@-.-/-!-;-,-_].*") ) {*/
             
             
             else{
                   boolean exists=false;
             for (Categorie c:CategorieList)
             {
                 if (c.getNom().equals(txtnom.getText()))
                 {
                     exists=true;
                 }
             }
                 if (exists)
              {
                               String titre="Categorie déja existe";
String message = "Please fill in a new name";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));
         
        }
        
         else if ( txtnom.getText().matches(".*[0-9].*")||  txtdescription.getText().matches(".*[0-9].*")) {
                               String titre="Error";
String message = "Please enter only letters";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));
         
        }
         else {        String titre="SUCCESS";
String message ="Categorie Added Successfully";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));
  ServiceCategorie es=new ServiceCategorie();
        Categorie e=new Categorie(
                
                txtnom.getText(),
                txtdescription.getText()
                  );
      //  JOptionPane.showMessageDialog(null, "Categorie Added Successfully");
   
        es.insert(e);
        
readall();
}}
         /*else if ( txtid.getText().matches(".*[a-z].*"))
            {
                 String titre="Error";
                String message = "Please enter only Numbers !";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));

        }*/

       
    /*    String titre="SUCCESS";
String message ="Categorie Added Successfully";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));*/

    }

    @FXML
    private void delete(ActionEvent event) {
  String titre="SUCCESS";
String message ="Categorie deleted Successfully";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));

         int n=Integer.parseInt(txtid.getText());
        ServiceCategorie  es=new ServiceCategorie ();
        Categorie e=new Categorie (per.getId());
        es.delete(e);
      //  JOptionPane.showMessageDialog(null, "Categorie deleted Successfully");
    }

    @FXML
    private void update(ActionEvent event) {
          String titre="SUCCESS";
String message ="Categorie updated Successfully";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));
        System.out.println("starting");
        ServiceCategorie es=new ServiceCategorie();
        Categorie e=new Categorie(per.getId(),
               txtnom.getText(),
                txtdescription.getText()
                );
               
        System.out.println("test");
        es.update(e);
           System.out.println("test2");
           //JOptionPane.showMessageDialog(null, "Categorie updated Successfully");
           
    }

    @FXML
    private void readall(ActionEvent event) {
          readall();
    }
    private void readall(){
        ServiceCategorie es=new ServiceCategorie();
           CategorieList=es.readAll();
           

        
            ObservableList<Categorie> ez=FXCollections.observableArrayList(CategorieList);
        
               tablev.setItems(ez);
        
    }

    private void produit(ActionEvent event) {
         try {
                Parent page = FXMLLoader.load(getClass().getResource("PPanel.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        per = tablev.getSelectionModel().getSelectedItem();
    txtid.setText(String.valueOf(per.getId()));
    txtnom.setText(String.valueOf(per.getNom()));
    txtdescription.setText(String.valueOf(per.getDescription()));
  
    }

    @FXML
    private void search(ActionEvent event) {
         ServiceCategorie st= new  ServiceCategorie();
        CategorieListSearch = st.likeByCategorie(txtsearch.getText());
        tablev.setItems(CategorieListSearch);
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
    
}
