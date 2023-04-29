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
import controller.Reservation_Service;
import entity.Evenement_entite;
import entity.Reservation_entite;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class NbrPlacerserveController implements Initializable {

    @FXML
    private TextField nbrpl;
    @FXML
    private Pane Ticketpane;
    private User localUser;
    private Evenement_entite localEvenement;
    private static NbrPlacerserveController instance;
    public static NbrPlacerserveController getInstance(){
        return instance;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instance=this;
        System.out.println("test nbrplace reserve");
                Ticketpane.setVisible(false);

    }    

    @FXML
     private void save(ActionEvent event) 
     {
         getUser_Evenement();
         System.out.println("localUser "+ localUser);
       
        LocalDate date = LocalDate.now();
        System.out.println(date);


        Reservation_Service sr = new Reservation_Service();
        sr.insert(new Reservation_entite(localUser, localEvenement, date, Integer.parseInt(nbrpl.getText()) ));
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ticket.fxml"));
        Ticketpane.setVisible(true);
        try{
            Node node = loader.load();
            TicketController tickercontroller=loader.getController();
            tickercontroller.readByLastId();
                      DetailEventsController.getInstance().getreserverpane().getChildren().add(node);
                    }
        catch(IOException ex){
            Logger.getLogger(NbrPlacerserveController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
       
        
    }
    public void getUser_Evenement(){
      localUser=  AllEventController.getInstance().getUser();
        localEvenement=DetailEventsController.getInstance().getEvenement();
    }
    
    public void close(){
                        Ticketpane.setVisible(false);
        DetailEventsController.getInstance().closeNB();
    }
}
