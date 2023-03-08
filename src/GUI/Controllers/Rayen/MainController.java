package GUI.Controllers.Rayen;

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

public class MainController implements Initializable{

    @FXML
    private VBox listp;
    @FXML
    private Pane STORE;
    private static MainController instance;
    @FXML
    private AnchorPane APANE;
    @FXML
    private Button retour;
    public static MainController getINSTANCE(){
    return instance;
    }
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
private User localuser;
    User us =new User(4);


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance=this;
        APANE.setVisible(false);
        Pane1.setVisible(false);
    }

   

public void ajouterStore() {
    StoreService store = new StoreService();
    UserController  userC=new UserController();
        User usr =new User(4);
    setLocaluser(userC.readById(us.getId()));
    localStore= store.readById(usr.getId());
    try {
        FXMLLoader storeLoader;
        if(getlocaluser().getRoles().equals("Partenaire")||getlocaluser().getRoles().equals("Admin"))
        {storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/StorePartenaire.fxml"));}
        else
        {storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/StoreClient.fxml"));}
        Node storeNode = storeLoader.load();
        StoreController storeController = storeLoader.getController();
        storeController.setLocalstore(localStore);
        STORE.getChildren().add(storeNode);
    } catch (IOException ex) {
        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
    }
}       
public void ajouterStoreparProduit(Produit p) {
    ProduitStoreService PSS=new ProduitStoreService();
    UserController  userC=new UserController();
        User usr =new User(4);
    setLocaluser(userC.readById(us.getId()));
    localStore= PSS.readStoreById(p);
    try {
        FXMLLoader storeLoader;
        if(getlocaluser().getRoles().equals("Partenaire")||getlocaluser().getRoles().equals("Admin"))
        {storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/StorePartenaire.fxml"));}
        else
        {storeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/StoreClient.fxml"));}
        Node storeNode = storeLoader.load();
        StoreController storeController = storeLoader.getController();
        storeController.setLocalstore(localStore);
        STORE.getChildren().add(storeNode);
    } catch (IOException ex) {
        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
    }
}       
    public void setLocaluser(User localuser) {
        this.localuser = localuser;
    }

     public User getlocaluser()
        {
            return localuser;
        }

//load des produit tout en depant de user role
 public void ajouterlistproduit(){
         UserController  userC=new UserController();
         setLocaluser(userC.readById(us.getId()));
        listp.getChildren().clear();
        ProduitStoreService ListProduit=new ProduitStoreService();
        // intgre recuperate store
        int idstore=1;
        List<Produit> list=ListProduit.readById(idstore).getProd();
        HBox hbox=new HBox();
        hbox.setSpacing(10);
        hbox.setPrefWidth(400);
        hbox.setPrefHeight(50);
        int counter=0;
        FXMLLoader produitLoader = null;
        for (Produit p:list) {
            if (counter==3)
            {
                listp.getChildren().add(hbox);
                counter=0;
                hbox=new HBox();
                hbox.setPrefWidth(300);
                hbox.setPrefHeight(50);

            }
            try {
                System.out.println(getlocaluser());
                        if(getlocaluser().getRoles().equals("Partenaire"))
        {produitLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Produit.fxml"));}
                        else if(getlocaluser().getRoles().equals("Admin"))
        {produitLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/ProduitAdmin.fxml"));}
                        else
        { /*produitLoader = new FXMLLoader(getClass().getResource( "/GUI/FXML/Produit.fxml"));*/}
                             
                Node node = produitLoader.load();                
                ProduitController cc=produitLoader.getController();
                cc.setLocalProduit(p);
                hbox.getChildren().add(node);
                counter++;
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        listp.getChildren().add(hbox);

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
    private void go_back(ActionEvent event)  {
    }
    public void updateinfoStore() throws IOException{
        instance.APANE.getChildren().clear();
                    instance.SplitPane.setVisible(false);
    instance.APANE.setVisible(true);
            FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/UpdateStore.fxml"));
            Node node=root.load();
instance.APANE.getChildren().add(node);
    }   
    public void updateinfoProduit(Produit produit) throws IOException{
        instance.APANE.getChildren().clear();
        instance.SplitPane.setVisible(false);
        instance.APANE.setVisible(true);
            FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/UIpdateProduit.fxml"));
                
            Node node=root.load();
        
            UIpdateProduitController pc=root.getController();
           
            pc.setlocalproduit(produit);
            
        instance.APANE.getChildren().add(node);
    }
    
       public void AjoutProduit() throws IOException{
        instance.APANE.getChildren().clear();
                    instance.SplitPane.setVisible(false);
    instance.APANE.setVisible(true);
            FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/PPanel.fxml"));
                
            Node node=root.load();
 
instance.APANE.getChildren().add(node);
    }
        public void noupdateinfoStore(){
            instance.SplitPane.setVisible(true);
            instance.APANE.setVisible(false);
    }

    @FXML
    private void updateStock(ActionEvent event) {
        ServiceProduit SS=new ServiceProduit();
        SS.updatequantite(produit_id,Integer.parseInt(quantite.getText()));
        ajouterlistproduit();
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("this product has been updated!");
        alert.showAndWait();
        Pane1.setVisible(false);
        
    }
        
    public void ADDPRODUIT() throws IOException{
        instance.APANE.getChildren().clear();
                    instance.SplitPane.setVisible(false);
    instance.APANE.setVisible(true);
            FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/.fxml"));
            Node node=root.load();
instance.APANE.getChildren().add(node);
    } 
public void updateinfoStock (Produit p){
   
        produit_id=p.getId();
        nameP.setText(p.getNom());
        prix.setText(String.valueOf(p.getPrix()));
        quantite.setText(String.valueOf(p.getQuantite()));
        categorie.setText(p.getCategorie().getNom());
        imageP.setImage(new Image("file:" + p.getPhoto()));   
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
   

