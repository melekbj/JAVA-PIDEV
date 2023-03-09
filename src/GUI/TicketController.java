/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import connexion.ConnexionSource;
import entity.reservationEventUser;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class TicketController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection cnx = ConnexionSource.getInstance().getCnx();
    @FXML
    private ImageView qrcode;
    @FXML
    private Label nomv;
    @FXML
    private Label dateev;
    @FXML
    private Label lieuev;
    @FXML
    private Label nomev;
    @FXML
    private Label prenomvi;
    @FXML
    private Label nbrpl;
    @FXML
    private Label daterv;
    @FXML
    private AnchorPane pane2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD
        this.readByLastId(DetailEventsController.idUsr);
     
    }

    //readByLastId
    public void readByLastId(int id) {
        try {
            String condition = (" r.user_id ='" + id + "'");
            String requete = "SELECT * FROM reservation r,evenement e,user u WHERE r.user_id=u.id and r.event_id=e.id and " + condition + " ORDER BY r.id DESC LIMIT 1";

            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //(int id, String titleEvent, LocalDate dateEvent, String locationEvent, LocalDate dateReservation, int nbrPlace, String nomUser, String prenomUser)
                reservationEventUser r = new reservationEventUser(rs.getInt("id"), rs.getString("titre_ev"), rs.getDate("date_debut").toLocalDate(), 
                        rs.getString("lieu_ev"), rs.getDate("date").toLocalDate(),
                        rs.getInt("nb_places"),
                        rs.getString("nom"), rs.getString("prenom"),rs.getString("imgQr"));
                nomv.setText(r.getNomUser());
                prenomvi.setText(r.getPrenomUser());
                nomev.setText(r.getTitleEvent());
                lieuev.setText(r.getLocationEvent());
                dateev.setText(r.getDateEvent().toString());
                daterv.setText(r.getDateReservation().toString());
                nbrpl.setText(String.valueOf(r.getNbrPlace()));
                //set image
                        String path = "file:///"+r.getImgQr();
        Image image = new Image(path);
        qrcode.setImage(image);
 


            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void pdf(ActionEvent event) {
        
         
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
             Window primaryStage = null;
             job.showPrintDialog(primaryStage); 
            
             Node root=this.pane2
;   
              job.printPage(root);
              
              job.endJob(); 

    
        
    }
        
    }

}
