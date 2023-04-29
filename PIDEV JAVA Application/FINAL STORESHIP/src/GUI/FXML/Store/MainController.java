package GUI.FXML.Store;

import GUI.Controllers.ClientMainController;
import GUI.Controllers.PartnerMainController;
import entity.Produit;
import entity.Store;
import entity.User;
import controller.ProduitStoreService;
import controller.ServiceProduit;
import controller.StoreService;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

    @FXML
    private VBox listp;
    @FXML
    private Pane STORE;
    
    @FXML
    private AnchorPane APANE;
    @FXML
    private Button retour;
    @FXML
    private SplitPane SplitPane;
    @FXML
    private VBox ListC;
    @FXML
    private Pane Pane1;
    @FXML
    private ImageView imageP;
    @FXML
    private Label nameP;
    @FXML
    private Label prix;
    @FXML
    private Label categorie;
    @FXML
    private Button btn_Stock;
    @FXML
    private TextField quantite;
    private int produit_id;
    private Store localStore;
    private Produit localproduit;
    private User localuser;
    
private static MainController instance;
    public static MainController getINSTANCE() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("MainController for");
        instance = this;
        APANE.setVisible(false);
        Pane1.setVisible(false);
    }

    public void ajouterStore() {
        StoreService store = new StoreService();
        UserController userC = new UserController();
        setLocaluser(PartnerMainController.getInstance().getUser());
        localStore = store.readById(localuser.getId());
        System.out.println("localStore   a============" + localStore);
        try {
            FXMLLoader storeLoader;
            if (localStore == null) {
                AjoutStore();
            } else {
                if (getlocaluser().getRoles().equals("ROLE_PARTNER") || getlocaluser().getRoles().equals("ROLE_ADMIN")) {
                    System.out.println("role is Partenaire ou ADMIN ");
                    storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/StorePartenaire.fxml"));
                    Node storeNode = storeLoader.load();
                    StoreController storeController = storeLoader.getController();
                    storeController.setLocalstore(localStore);
                    instance.STORE.getChildren().add(storeNode);
                } else {
                    storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/StoreClient.fxml"));
                    Node storeNode = storeLoader.load();
                    StoreController storeController = storeLoader.getController();
                    storeController.setLocalstore(localStore);
                    instance.STORE.getChildren().add(storeNode);

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouterStoreparProduit(Produit p) {
        ProduitStoreService PSS = new ProduitStoreService();
        UserController userC = new UserController();
        User usr = ClientMainController.getInstance().getUser();
        setLocaluser(userC.readById(usr.getId()), p);
        localStore = PSS.readStoreById(p);
        try {
            FXMLLoader storeLoader;
            if (getlocaluser().getRoles().equals("ROLE_PARTNER") || getlocaluser().getRoles().equals("ROLE_ADMIN")) {
                storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/StorePartenaire.fxml"));
            } else {
                storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/StoreClient.fxml"));
            }
            Node storeNode = storeLoader.load();
            StoreController storeController = storeLoader.getController();
            storeController.setLocalstore(localStore);
            STORE.getChildren().add(storeNode);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ajouterlistproduit();
    }

    public void setLocaluser(User localuser, Produit produit) {
        this.localuser = localuser;
        this.localproduit = produit;
    }

    public void setLocaluser(User localuser) {
        this.localuser = localuser;
    }

    public User getlocaluser() {
        return localuser;
    }

    public Store getStore() {
        return localStore;
    }

//load des produit tout en depant de user role
    public void ajouterlistproduit() {

        UserController userC = new UserController();
        User user = null;
        ClientMainController clientController = ClientMainController.getInstance();
        if (clientController != null) {
            user = clientController.getUser();
        }
        if (user == null) {
            PartnerMainController partnerController = PartnerMainController.getInstance();
            if (partnerController != null) {
                user = partnerController.getUser();
            }
        }
        if (user != null) {
            setLocaluser(user);
        }
        instance.listp.getChildren().clear();
        ProduitStoreService ListProduit = new ProduitStoreService();
        // intgre recuperate store

        List<Produit> list = ListProduit.readById(localStore.getId()).getProd();
        System.out.println("Listerrrrrrrrrrrrrrrrrrrrrrrrrrr"+list);
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPrefWidth(400);
        hbox.setPrefHeight(50);
        int counter = 0;
        boolean parteneroradmin = true;

        FXMLLoader produitLoader = null;
        for (Produit p : list) {
            if (counter == 3) {
                listp.getChildren().add(hbox);
                counter = 0;
                hbox = new HBox();
                hbox.setPrefWidth(300);
                hbox.setPrefHeight(50);

            }
            try {
                System.out.println(getlocaluser());
                if (getlocaluser().getRoles().equals("ROLE_PARTNER")) {
                    produitLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/Produit.fxml"));
                } else if (getlocaluser().getRoles().equals("ROLE_ADMIN")) {
                    produitLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/ProduitAdmin.fxml"));
                } else {
                    produitLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Commande/Produit.fxml"));
                    parteneroradmin = false;
                    System.out.println("doing this");
                }

                Node node = produitLoader.load();
                if (parteneroradmin) {
                    ProduitController cc = produitLoader.getController();
                    cc.setLocalProduit(p);
                } else {
                    System.out.println("leading to this");
                    GUI.Controllers.Commande.ProduitController mp = produitLoader.getController();
                    mp.setDetail_Produit(p);
                }

                hbox.getChildren().add(node);
                counter++;
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        instance.listp.getChildren().add(hbox);

    }

    /*public void ajouterCommande() {
    ListC.getChildren().clear();
    StoreService store = new StoreService();
    List<Produit> list=produit.readAll();
    
    User usr = new User(1);
    Store St = store.readById(usr.getId());
    try {
        FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Commande.fxml"));
        Node storeNode = storeLoader.load();
        StoreController storeController = storeLoader.getController();
        storeController.setLocalstore(St);
        System.out.println(St);
        STORE.getChildren().add(storeNode);
    } catch (IOException ex) {
        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
    }
}*/

    @FXML
    private void go_back(ActionEvent event) {
    }

    public void updateinfoStore() throws IOException {
        instance.APANE.getChildren().clear();
        instance.SplitPane.setVisible(false);
        instance.APANE.setVisible(true);
        FXMLLoader root = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/UpdateStore.fxml"));
        Node node = root.load();
        instance.APANE.getChildren().add(node);
    }

    public void AjoutStore() {
        try {
            System.out.println("my spledid formualrie");
            instance.APANE.getChildren().clear();
            instance.SplitPane.setVisible(false);
            instance.APANE.setVisible(true);
            FXMLLoader root = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/FormulaireStore.fxml"));
            System.out.println("trying to load ");
            Node node = root.load();
            System.out.println("dint load ");
            instance.APANE.getChildren().add(node);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateinfoProduit(Produit produit) throws IOException {
        instance.APANE.getChildren().clear();
        instance.SplitPane.setVisible(false);
        instance.APANE.setVisible(true);
        FXMLLoader root = new FXMLLoader(getClass().getResource("/GUI/FXML/UIpdateProduit.fxml"));

        Node node = root.load();

        UIpdateProduitController pc = root.getController();

        pc.setlocalproduit(produit);

        instance.APANE.getChildren().add(node);
    }

    public void AjoutProduit() throws IOException {
        instance.APANE.getChildren().clear();
        instance.SplitPane.setVisible(false);
        instance.APANE.setVisible(true);
        FXMLLoader root = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/PPanel.fxml"));

        Node node = root.load();

        instance.APANE.getChildren().add(node);
        ajouterlistproduit();
    }

    public void noupdateinfoStore() {
        instance.SplitPane.setVisible(true);
        instance.APANE.setVisible(false);
    }

    @FXML
    private void updateStock(ActionEvent event) {
        ServiceProduit SS = new ServiceProduit();
        SS.updatequantite(produit_id, Integer.parseInt(quantite.getText()));
        ajouterlistproduit();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("this product has been updated!");
        alert.showAndWait();
        Pane1.setVisible(false);

    }

    public void ADDPRODUIT() throws IOException {
        instance.APANE.getChildren().clear();
        instance.SplitPane.setVisible(false);
        instance.APANE.setVisible(true);
        FXMLLoader root = new FXMLLoader(getClass().getResource("/GUI/FXML/.fxml"));
        Node node = root.load();
        instance.APANE.getChildren().add(node);
    }

    public void updateinfoStock(Produit p) {

        produit_id = p.getId();
        nameP.setText(p.getNom());
        prix.setText(String.valueOf(p.getPrix()));
        quantite.setText(String.valueOf(p.getQuantite()));
        categorie.setText(p.getCategorie().getNom());
        try {
            imageP.setImage(new Image("file:///" + p.getPhoto()));

        } catch (Exception e) {
            System.out.println("error image");
        }
        Pane1.setVisible(true);
    }

    @FXML
    private void cancelupdate(ActionEvent event) {
        Pane1.setVisible(false);
    }

    // a wael pour terminer
    /* private void addCommandeNodes(){
        ServiceDetail_Commande sc=new ServiceDetail_Commande();
        List<Detail_Commande> list=sc.readByStore(localStore.getId());
                for (Detail_Commande c:list)
                {
                    listC.addDetail_Commande(c);
                }
    }*/
}
