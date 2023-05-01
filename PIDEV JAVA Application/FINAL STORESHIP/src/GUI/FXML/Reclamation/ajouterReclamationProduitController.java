/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Reclamation;

import GUI.Controllers.ClientMainController;
import GUI.Controllers.Commande.HistoriqueCommandeController;
import controller.ServiceReclamation;
import controller.ServiceTypeReclamation;
import controller.Service_Detail_Commande;
import entity.Detail_Commande;
import entity.Produit;
import entity.Reclamation;
import entity.TypeReclamation;
import entity.User;
import entity.Util.State;
import entity.Util.TunisieMap;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class ajouterReclamationProduitController implements Initializable {

    @FXML
    private TextArea descriptionReclamation;
    @FXML
    private Button passerReclamation;
    @FXML
    private Label nomProduit;
   
    @FXML
    private Label categorie;
    @FXML
    private Label prix;
    @FXML
    private Label idCommande;
    @FXML
    private Label prixTotal;
    private User localuser;
    private Detail_Commande localdetail;
    @FXML
    private ComboBox<String> state;
    @FXML
    private Button imageSt;
    @FXML
    private ImageView image_St;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }    
   
    
    @FXML
    private void ajoutertest(ActionEvent event) {
        ServiceTypeReclamation str=new ServiceTypeReclamation();
       Reclamation re=new Reclamation(null
                ,localuser
                ,localdetail.getProduit()
                ,localdetail.getCommande()
                ,descriptionReclamation.getText()
                ,LocalDate.now()
                ,"Pending"
                ,str.readByName(state.getValue().toString())
       );
       
         
            
                
        try {
            
              // save the image inside htdoc
           
         String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
         
         
String newFileName = "image_" + System.currentTimeMillis() + extension;
        Path destination = Paths.get("C:/xampp/htdocs/ImagePidev/", newFileName);
            System.out.println("Destination image Path == "+destination);
        Files.copy(selectedFile.toPath(), destination);

// Get the new file name
String newFilePath = destination.toString();
            System.out.println("New File Path===="+newFilePath);
            re.setImageString(newFilePath);
        } catch (Exception e) {
            System.out.println("Error insert Image Reclamation");
        }
       
        ServiceReclamation sr = new ServiceReclamation();
        if(!descriptionReclamation.getText().equals(""))
        sr.insert(re); 
      
           // Alert Box 
           Alert alert = new Alert(Alert.AlertType.INFORMATION.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("Votre Reclamation a été ajouter");
alert.showAndWait();
    }
    
  public void reclamerproduit(Detail_Commande p)
  { 
      ServiceTypeReclamation str=new ServiceTypeReclamation();
      List<TypeReclamation> list=str.readAll();
      for (TypeReclamation s:list)
        {
            state.getItems().add(s.getTypeName());
        }
      
      System.out.println("detailCOmmande pppppppppppppppppp"+ p);
      localuser = ClientMainController.getInstance().getUser();
  localdetail=p;
        nomProduit.setText(localdetail.getProduit().getNom());
        System.out.println("Nom Produit------------------- "+ localdetail.getProduit().getNom());
        prix.setText(Double.toString(localdetail.getProduit().getPrix()));
        System.out.println("Prix Produit-----------------"+localdetail.getProduit().getPrix());
       // categorie.setText(String.valueOf(p.getProduit().getCategorie().getNom()));
         descriptionReclamation.textProperty().addListener((observable, oldValue, newValue) -> {
        int wordCount = newValue.trim().split("\\s+").length;
        String style = wordCount < 3 ?
                       "-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 0);" :
                       "-fx-effect: dropshadow(three-pass-box, green, 10, 0, 0, 0);";
       descriptionReclamation.setStyle(style);
    });
  }

    @FXML
    private void cancelreclamation(ActionEvent event) {
        HistoriqueCommandeController.getInstance().closereclamation();
    }
     private Stage primaryStage;
   private File selectedFile = null;
   
    @FXML
    private void insertImage(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload an image");
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);
    selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null)
    {
        imageSt.setText(selectedFile.getName());
        try {
            image_St.setImage(new javafx.scene.image.Image("file:" + selectedFile));
        } catch (Exception e) {
            System.out.println("error image");
        }
    }
    }
  
  
}
