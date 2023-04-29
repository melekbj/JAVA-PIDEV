/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Evenement;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import connexion.ConnexionSource;
import controller.Evenement_Service;
import controller.Reservation_Service;
import entity.Evenement_entite;
import entity.Reservation_entite;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DetailEventsController implements Initializable {

    @FXML
    private Text titreev;
    @FXML
    private Text lieuev;
    @FXML
    private Text dtdbev;
    @FXML
    private Text datefev;
    @FXML
    private Text nbrplev;
        @FXML
    private ImageView imgev;
    @FXML
    private AnchorPane root;
    TextField nameLabel;
    @FXML
    private Pane reserverpane;
    private static Evenement_entite localEvenement;
    private static DetailEventsController instance;
    public static DetailEventsController getInstance(){
        return instance;
    }
    

    
    private boolean etat;
    private int nbrp;
    private int idev;
    /**
     * Initializes the controller class.
     */

    Connection cnx = ConnexionSource.getInstance().getCnx();
    @FXML
    private VBox virtuelbox;
    @FXML
    private VBox virtuel2box;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance=this;
reserverpane.setVisible(false);
        
    }

//load data
    public void loadData(Evenement_entite ev) {
        localEvenement=ev;
        Evenement_Service es = new Evenement_Service();
        titreev.setText(localEvenement.getTitreEvenement());
        lieuev.setText(localEvenement.getLieuEvenement());
        dtdbev.setText(localEvenement.getDate_debutEvenement().toString());
        datefev.setText(localEvenement.getDate_finEvenement().toString());

        nbrplev.setText(String.valueOf(localEvenement.getNbMax_place()));
        String path = localEvenement.getImageEvenement();
        System.out.println(localEvenement);

        try {
                    Image image = new Image("file:///"+path);

                    imgev.setImage(image);
        } catch (Exception xxxe) {
            xxxe.printStackTrace();
        }

    }

    @FXML
    private void Reserver(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("nbrPlacerserve.fxml"));
        try {
                  Node node=loader.load();
               hideme(false);   
            reserverpane.setVisible(true);
            reserverpane.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
     

    }

  public Evenement_entite getEvenement(){
      return localEvenement;
  } 
  public void setEvenement(Evenement_entite ev){
      localEvenement=ev;
  }
  public void hideme(Boolean myboolean){
       virtuelbox.setVisible(myboolean);
   
   virtuel2box.setVisible(myboolean);
  }
  
    @FXML
  public void close(){
                  reserverpane.setVisible(false);
      AllEventController.getInstance().close();
  }
  public void closeNB(){
      reserverpane.setVisible(false);
      hideme(true);
      
  }
  
  public Pane getreserverpane(){
      hideme(false);
      return reserverpane;
  }
}
