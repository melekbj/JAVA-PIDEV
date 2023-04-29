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
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import connexion.ConnexionSource;
import entity.Evenement_entite;
import entity.Reservation_entite;
import entity.User;
import entity.reservationEventUser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
import javafx.scene.control.Button;
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
    private User localUser;
    private Evenement_entite localEvenement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD     
        getUser_Evenement();
    }

    //readByLastId
    public void readByLastId() {
        try {
            String requete = "SELECT * FROM reservation  WHERE user_id=? and event_id=? ";

            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setInt(1, localUser.getId());
            pst.setInt(2, localEvenement.getIdEvenement());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                Reservation_entite re=new Reservation_entite(rs.getInt(1),localUser,localEvenement,rs.getDate(4).toLocalDate(),rs.getInt(5));
                //(int id, String titleEvent, LocalDate dateEvent, String locationEvent, LocalDate dateReservation, int nbrPlace, String nomUser, String prenomUser)
//                reservationEventUser r = new reservationEventUser(rs.getInt("id"), rs.getString("titre_ev"), rs.getDate("date_debut").toLocalDate(), 
//                        rs.getString("lieu_ev"), rs.getDate("date").toLocalDate(),
//                        rs.getInt("nb_places"),
//                        rs.getString("nom"), rs.getString("prenom"),rs.getString("imgQr"));
                nomv.setText(re.getUser().getNom());
                prenomvi.setText(re.getUser().getPrenom());
                nomev.setText(re.getEv().getTitreEvenement());
                lieuev.setText(re.getEv().getLieuEvenement());
                dateev.setText(re.getEv().getDate_debutEvenement().toString());
                daterv.setText(re.getEv().getDate_finEvenement().toString());
                nbrpl.setText(String.valueOf(re.getNbr_place()));
                 String filePath="";
        try {
            //insert data to the qrcodedata
            
            String qrCodeData = ""
                   // + "Id User:: " + localUser.getId() 
                    +"\nNom User :: "+ localUser.getNom() +""
                    + "    \nPrenom User :: "+ localUser.getPrenom() +""
                   // + "\nidEvent: " + localEvenement.getIdEvenement() + ""
                    + "\n Nom Evenement::"+ localEvenement.getTitreEvenement()
                    + "\n nbrp: " + nbrpl.getText();

            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

            // create a random number generator
            Random random = new Random();

            // generate a random name
            String name = "";
            for (int i = 0; i < 5; i++) {
                char c = alphabet[random.nextInt(alphabet.length)];
                name += c;
            }

             filePath = "C:\\Users\\Plop\\Desktop\\StoreShip222\\src\\GUI\\Images\\" + name + ".jpg";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }

                //set image
                        String path = "file:///"+filePath;
        Image image = new Image(path);
                try {
                            qrcode.setImage(image);

                } catch (Exception e) {
                    System.out.println("error image QRCode");
                }
 


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
    public void getUser_Evenement(){
      localUser=  AllEventController.getInstance().getUser();
        localEvenement=DetailEventsController.getInstance().getEvenement();
    }

    @FXML
    private void close(ActionEvent event) {
        NbrPlacerserveController.getInstance().close();
    }

}
