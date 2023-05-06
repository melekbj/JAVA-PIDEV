/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import GUI.FXML.Evenement.AllEventController;
import GUI.FXML.Store.CommandeController;
import GUI.FXML.Store.MainController;
import controller.ProduitStoreService;
import controller.Service_Detail_Commande;
import controller.StoreService;
import controller.UserController;
import entity.Detail_Commande;
import entity.Store;
import entity.User;
import entity.Util.QRCodeGenerator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class PartnerContenuController implements Initializable {

    private static PartnerContenuController instance;

    public static PartnerContenuController getInstance() {
        return instance;
    }

    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    private ImageView iv_img;

    @FXML
    private Pane pane3;
    @FXML
    private ImageView re_img;
    @FXML
    private Pane pane4;
    @FXML
    private Label lab_name;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_age;
    @FXML
    private TextField tf_adr;
    @FXML
    private TextField tf_email;
    private User localuser;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ImageView qr_image;
    @FXML
    private TextField tf_num;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
        pane4.setVisible(false);
        try {
            re_img.setImage(new Image("file:///" + PartnerMainController.getInstance().getUser().getImage()));

        } catch (Exception e) {
            System.out.println("error image");
        }
        lab_name.setText(PartnerMainController.getInstance().getUser().getNom()
                + " " + PartnerMainController.getInstance().getUser().getPrenom());

        tf_nom.setText(PartnerMainController.getInstance().getUser().getNom());
        tf_prenom.setText(PartnerMainController.getInstance().getUser().getPrenom());
        tf_age.setText(String.valueOf(PartnerMainController.getInstance().getUser().getAge()));
        tf_adr.setText(PartnerMainController.getInstance().getUser().getAdresse());
        tf_email.setText(PartnerMainController.getInstance().getUser().getEmail());
        tf_email.setEditable(false);
        tf_num.setText(PartnerMainController.getInstance().getUser().getPhone());
        tf_num.setEditable(false);

        localuser = PartnerMainController.getInstance().getUser();
        // Generate the QR code image using the QRCodeGenerator utility class
//        String data = 
//              "Full Name:"+PartnerMainController.getInstance().getUser().getNom()+" "+
//                PartnerMainController.getInstance().getUser().getPrenom()+"\n" +
//              "TEL:"+PartnerMainController.getInstance().getUser().getPhone()+"\n"+
//              "EMAIL:"+PartnerMainController.getInstance().getUser().getEmail();
//
//        int size = 250;
//        qr_image.setImage(QRCodeGenerator.generateQRCode(data, size).getImage());

    }

    public void setPane1() {
        loadstore();
        try {
                    loadcommandes();

        } catch (Exception e) {
            e.printStackTrace();
        }
        instance.pane1.setVisible(true);
        instance.pane2.setVisible(false);
        instance.pane3.setVisible(false);
    }

    public void setPane2() {
        instance.pane1.setVisible(false);

        instance.pane2.setVisible(true);
        instance.pane3.setVisible(false);
        loadstore();

    }

    public void setPane3() {
        instance.pane1.setVisible(false);

        instance.pane2.setVisible(false);
        instance.pane3.setVisible(true);

    }

    @FXML
    private void load4(ActionEvent event) {
        pane4.setVisible(true);
    }

    @FXML
    private void updateProfile(ActionEvent event) {

        UserController uc = new UserController();

        User u = PartnerMainController.getInstance().getUser();
        u.setNom(tf_nom.getText());
        u.setPrenom(tf_prenom.getText());
        u.setAge(Integer.parseInt(tf_age.getText()));
        u.setAdresse(tf_adr.getText());
        u.setAdresse(tf_adr.getText());

        uc.UpdateUser(u);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("user updated successfully");
        alert.showAndWait();
        PartnerMainController.getInstance().setUser(u);

    }

    public void loadstore() {
        instance.pane2.getChildren().clear();
        FXMLLoader evenloader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/Main.fxml"));
        try {
            Node node = evenloader.load();
            MainController maincontroller = new MainController();
            maincontroller.ajouterStore();
            instance.pane2.getChildren().add(node);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void loadcommandes() {
        instance.pane1.getChildren().clear();
        StoreService ss=new StoreService();
        
        Store store = MainController.getINSTANCE().getStore();
        Service_Detail_Commande pss = new Service_Detail_Commande();
        List<Detail_Commande> details_list = pss.readByStoreId(store);
        System.out.println("the current List to load" +details_list);
        ScrollPane scrollPane = new ScrollPane();
scrollPane.setPrefHeight(550);
scrollPane.setPrefWidth(450);
            VBox vBox = new VBox();
            vBox.setSpacing(5);
        for (Detail_Commande detail : details_list) {
            // Load the Commande.fxml file and set the Detail_Commande values
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/Commande.fxml"));
            try {
                Node node = loader.load();
                CommandeController controller = loader.getController();
                controller.setDetail_Commandes(detail);
                // Add the node to the appropriate container in your UI
                // For example, if you have a VBox with id "commandeContainer":
                vBox.getChildren().add(node);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
scrollPane.setContent(vBox);
instance.pane1.getChildren().add(scrollPane);
    }
}
