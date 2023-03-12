package GUI.FXML.Store;

import entity.Categorie;
import entity.Produit;
import entity.User;
import controller.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class UIpdateProduitController implements Initializable {
    @FXML
    private Button btn_img;
    @FXML
    private SplitMenuButton choixCategorie;

    @FXML
    private ImageView image;
    @FXML
    private Button annuler_btn;
    @FXML
    private TextField price;

    @FXML
    private TextArea product_desc;

    @FXML
    private TextField productname;

    @FXML
    private TextField quantite;
    private File selectedFile = null;
    private Stage primaryStage;
    private Produit localproduit=new Produit();

    @FXML
    void go_back(ActionEvent event) throws IOException {
        MainController LPC=new MainController();
        LPC.noupdateinfoStore();
    }

    @FXML
    void insertimage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload an image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null)
        {
            btn_img.setText(selectedFile.getName());
            image.setImage(new Image("file:" + selectedFile));
              //ImageView.setImage(new Image("file:" + selectedFile));
        }
    }
    
    @FXML
    void modifierProduit(ActionEvent event) {
         ServiceProduit SS=new ServiceProduit();
        Categorie CT=new Categorie();
        Produit st;
        
        st = new Produit(productname.getText(),selectedFile.getAbsolutePath(),Double.parseDouble(price.getText()),Integer.parseInt(quantite.getText()),CT);
        SS.update(st);
        

      //  Stage stage = (Stage) confirmer.getScene().getWindow();
       // stage.close();
       // Stage primaryStage = new Stage();
       MainController LPC=new MainController();
    
       // FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/ListProduit.fxml"));
       // primaryStage.setScene(new Scene(root));
    LPC.noupdateinfoStore();   
    }
    public void setlocalproduit(Produit p){

        localproduit=p;
        setinitialfields();


    }
    private void setinitialfields(){

        quantite.setText(String.valueOf(localproduit.getQuantite()));

        price.setText(String.valueOf(localproduit.getPrix()));

        productname.setText(localproduit.getNom());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

}
