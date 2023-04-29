/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Evenement;

//import static com.sun.deploy.uitoolkit.impl.fx.ui.MixedCodeInSwing.show;
import entity.Evenement_entite;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OneEventController implements Initializable {

    @FXML
    private ImageView imgEvent;
    @FXML
    private Text titleEvent;
  
    public static int idEvent;
private  Evenement_entite Evenement_entite;
    
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void detailEvent(ActionEvent event) {
        // load the new scene
       
    }
    


    public void setData(Evenement_entite Evenement_entite,  MyListener myListener) throws MalformedURLException
    {
        this.myListener = myListener;
this.Evenement_entite=Evenement_entite;

        titleEvent.setText(Evenement_entite.getTitreEvenement());
//get image from root directory and databse
        
      

                try {
                      String path = Evenement_entite.getImageEvenement();
        Image image = new Image("file:///"+path);
        System.out.println(path);
                System.out.println(image);
                    imgEvent.setImage(image);

        } catch (Exception e) {
                    System.out.println("error image");
        }


    }

    @FXML
    private void EventDetail(MouseEvent event) throws IOException {
        idEvent=Evenement_entite.getIdEvenement();
    AllEventController.getInstance().showreservation(Evenement_entite);
       
        
    }
  

    

    
   
    


    public interface MyListener {
    public void onClickListener(Evenement_entite Evenement_entite);
}
}