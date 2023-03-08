package GUI.Controllers.Rayen;

import entity.Produit;
import controller.ServiceProduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ProduitController implements Initializable {


    @FXML
    private ImageView imageP;

    @FXML
    private Label nameP;

    @FXML
    private Label prix;

    @FXML
    private VBox produit;

    @FXML
    private Label quantite;

    private File selectedFile = null;
    private Stage primaryStage;
    Produit localproduit=new Produit(); 
    ServiceProduit produitService=new ServiceProduit();

    @FXML
    private Label categorie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    private void setInitialFields() {
        // Populate UI elements with store information from the database
        nameP.setText(localproduit.getNom());
        prix.setText(String.valueOf(localproduit.getPrix()));
        quantite.setText(String.valueOf(localproduit.getQuantite()));
        categorie.setText(localproduit.getCategorie().getNom());
        imageP.setImage(new Image("file:" + localproduit.getPhoto()));    
    }

    @FXML
    void deleteproduit(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to delet this product");
        Optional<ButtonType> Result=alert.showAndWait();
        if(Result.get()==ButtonType.OK){
        produitService.delete(localproduit);
        MainController.getINSTANCE().ajouterlistproduit();
        }
    }
    @FXML
    void updateStock(ActionEvent event) throws IOException {
       // MainController LPC = new MainController();
       // LPC.updateinfoProduit(localproduit);
        MainController.getINSTANCE().updateinfoStock(localproduit);       
    }

    public void setLocalProduit(Produit p) {
        localproduit = p;
        setInitialFields();
    }

    private Produit getItem() {
        return localproduit;
    }

    @FXML
    private void RefuserProduit(ActionEvent event) {
    }

    @FXML
    private void ApproverProduit(ActionEvent event) {
    }

}
