/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers.Rayen;

import GUI.Controllers.Rayen.MainController;
import entity.Categorie;
import entity.Produit;
import entity.Store;
import entity.User;
import controller.ProduitStoreService;
import controller.ServiceProduit;
import controller.StoreService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jmili
 */
public class FormulaireproduitController implements Initializable {

    @FXML
    private TextField quantite;
    @FXML
    private TextField price;
    @FXML
    private TextField productname;
    private TextField etat;
    @FXML
    private SplitMenuButton choixCategorie;
    @FXML
    private TextArea product_desc;
    @FXML
    private ImageView image;
    @FXML
    private Button confirmer;
        private File selectedFile = null;
    private Stage primaryStage;
    @FXML
    private Button imageP;
    @FXML
    private Button annuler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void ajoutproduit(ActionEvent event) throws IOException {
                ServiceProduit SS=new ServiceProduit();
        User u=new User();
        Categorie CT=new Categorie();
        Produit st;
        //        public Produit(String nom, String photo, double prix, int quantite, Categorie categorie, int etat) {

        st = new Produit(productname.getText(),selectedFile.getAbsolutePath(),Double.parseDouble(price.getText()),Integer.parseInt(quantite.getText()),CT,Integer.parseInt(etat.getText()));
        SS.insert(st);
        
        Stage stage = (Stage) confirmer.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/ListProduit.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void insertimage(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload an image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null)
        {
            imageP.setText(selectedFile.getName());
            image.setImage(new Image("file:" + selectedFile));
              //ImageView.setImage(new Image("file:" + selectedFile));
        }
    }

    @FXML
    private void go_back(ActionEvent event) {
                MainController LPC=new MainController();
        LPC.noupdateinfoStore();
    }

    @FXML
    private void AddProduit(ActionEvent event) {
               ServiceProduit SS=new ServiceProduit();
        User u=new User();
        Categorie CT=new Categorie();
        Produit st;
        st = new Produit(productname.getText(),selectedFile.getAbsolutePath(),Double.parseDouble(price.getText()),Integer.parseInt(quantite.getText()),CT,Integer.parseInt(etat.getText()));
        SS.insert(st);
      //  Stage stage = (Stage) confirmer.getScene().getWindow();
       // stage.close();
       // Stage primaryStage = new Stage();
       MainController LPC=new MainController();
    
       // FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/ListProduit.fxml"));
       // primaryStage.setScene(new Scene(root));
    LPC.noupdateinfoStore();   
    }
    
}
