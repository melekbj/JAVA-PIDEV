/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import static GUI.OneEventController.idEvent;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DetailEventsController implements Initializable {

   public static int idUsr = 1;
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
    @FXML
    TextField nameLabel;

    public AnchorPane getRoot() {
        return root;
    }

    private boolean etat;
    private int nbrp;
    private int idev;
    /**
     * Initializes the controller class.
     */

    Connection cnx = ConnexionSource.getInstance().getCnx();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String value = (String) root.getUserData();

        System.out.println("value" + value);

        loadData();
        System.out.println("ena" + idEvent);
    }

//load data
    public void loadData() {

        Evenement_entite e = new Evenement_entite();
        Evenement_Service es = new Evenement_Service();
        e = es.readById(idEvent);
        titreev.setText(e.getTitreEvenement());
        lieuev.setText(e.getLieuEvenement());
        dtdbev.setText(e.getDate_debutEvenement().toString());
        datefev.setText(e.getDate_finEvenement().toString());

        nbrplev.setText(String.valueOf(e.getNbMax_place()));
        String path = "file:///C:/Users/asus/Desktop/ranimjavafx/StoreShip2/src/GUI/images/" + e.getImageEvenement();

        Image image = new Image(path);
        System.out.println("detail" + path);
        imgev.setImage(image);

    }

    @FXML
    private void Reserver(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("nbrPlacerserve.fxml"));
        loader.setController(this);
        try {
            Parent dialogRoot = loader.load();
            Stage dialog = new Stage();
            dialog.setScene(new Scene(dialogRoot, 550, 300));
            
            nameLabel = (TextField) dialogRoot.lookup("#nbrpl");


            this.etat = true;
            //affect nbrp with namLabel
            
            // Use the attribute to personalize the dialog message
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        String filePath ="";
               try {
           //insert event info  and user name from  to qrcode 
            String qrCodeData = "Event name :"+titreev.getText()+"\n"+"User name :"+idUsr+"\n"+"Number of places :"+nameLabel.getText();

                    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        // create a random number generator
            Random random = new Random();
        
        // generate a random name
        String name = "";
        for (int i = 0; i < 4; i++) {
            char c = alphabet[random.nextInt(alphabet.length)];
            name += c;
        }



             filePath = "C:\\Users\\asus\\Desktop\\ranimjavafx\\StoreShip2\\src\\GUI\\Images\\"+name+".jpg";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
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

        LocalDate date = LocalDate.now();
        System.out.println(date);

        nbrp = Integer.parseInt(nameLabel.getText());
        System.out.println(nbrp);

        //scanner code qr 
        Reservation_Service sr = new Reservation_Service();
        sr.insert(new Reservation_entite(idUsr, idEvent, date, nbrp,filePath));
        Stage stage = (Stage) root.getScene().getWindow();        
        Parent root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
