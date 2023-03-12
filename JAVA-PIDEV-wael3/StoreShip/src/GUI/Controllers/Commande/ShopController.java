/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers.Commande;

import GUI.Controllers.ClientMainController;
import GUI.FXML.Store.MainController;
import GUI.FXML.Store.StoreController;
import controller.ProduitStoreService;
import controller.ServiceProduit;
import controller.Service_Commande;
import controller.Service_Detail_Commande;
import entity.Commande;
import entity.Detail_Commande;
import entity.Produit;
import entity.Store;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class ShopController implements Initializable {

    @FXML
    private AnchorPane shoppingdisplay;
    @FXML
    private VBox shopproduit;
    @FXML
    private VBox Paniercourrante;
       private static List<Detail_Commande> paniercourrant=new ArrayList<>();
    private Commande commande=new Commande();

    @FXML
    private ScrollPane panierdisplay;
    private static boolean  panierdisplaystate=false;
    private static ShopController instance;
    public static ShopController getInstance(){
        return instance;
    }
    @FXML
    private Pane visiterstore;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instance=this;
        instance.panierdisplay.setVisible(panierdisplaystate);
        instance.visiterstore.setVisible(false);
    }    

    @FXML
    private void displaypanier(ActionEvent event) {
       instance.panierdisplay.setVisible(!panierdisplaystate);
       panierdisplaystate=!panierdisplaystate;
    }
    
public void ajouterproduitpanierlist(Produit p,int quantite){
        //intiliating services
        Detail_Commande dc=new Detail_Commande();
        //testing phase integre  must change User to automatique getter
        ProduitStoreService pss=new ProduitStoreService();
        Store store=pss.readStoreById(p);
          User user=ClientMainController.getInstance().getUser();
        // setting user on commande
        commande.setUser(user);
        //setting destination on commande     default TUNIS for now 
        // integre must change destination input
       
        
        //default value to test and now to remove
        commande.setDestination("Tunis");  
       //commande.setDestination(user.getadress());
        
        // converting produit into the correct format for Detail_commande to insert into DB
        Detail_Commande.convertProduitToDetail(dc,p,store ,quantite , commande);
        //boolean for checking if the product already exist in panier or no
        boolean added=false;
        Detail_Commande detailtoremove;
        for(Detail_Commande detail:paniercourrant)
        {      
            if (detail.getProduit().getId()==p.getId()){
                detail.setQuantite(detail.getQuantite()+quantite);
                detail.setPrix_total(detail.getQuantite()*(float)detail.getProduit().getPrix());
                added=true;
                
            }
            if (detail.getQuantite()==0)
            {
                paniercourrant.remove(detail);
                break;
            } 
            
        }
        
        // adding product if product does not exist in panier
        if(!added)
            {
                        paniercourrant.add(dc);

            }
        
         // integre   Commande.update(commande) updating currect commande with proper value;
         // commande must have prix , etat is default to pending  or nonPayer
           updatepanierdisplay(paniercourrant);
        }
public void updatepanierdisplay(List<Detail_Commande> paniercourrant){
        instance.Paniercourrante.getChildren().clear();
                      
        for (Detail_Commande c:paniercourrant) {
            
            try {
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource( "/GUI/FXML/Commande/Detail_commande.fxml"));
                Node node = produitLoader.load();

                Detail_commandeController detail_commande=produitLoader.getController();
                detail_commande.setDetail_Produit(c);

              instance.Paniercourrante.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex);
            }
    }
    }
            
     public void populateshop(){
        instance.shopproduit.getChildren().clear();
        ServiceProduit produit=new ServiceProduit();
        List<Produit> list=produit.readAllActive();
        HBox hbox=new HBox();
        hbox.setSpacing(10);
        hbox.setPrefWidth(400);
        hbox.setPrefHeight(50);
        int counter=0;
         for (Produit p:list) {
             if (counter==2)
             {
                 instance.shopproduit.getChildren().add(hbox);
                 counter=0;
                 hbox=new HBox();
                hbox.setPrefWidth(400);
                hbox.setPrefHeight(50);

             }
            try {                 System.out.println("counter break");

                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Commande/Produit.fxml"));
                Node node = produitLoader.load();
             ProduitController cc=produitLoader.getController();
               cc.setDetail_Produit(p);
              hbox.getChildren().add(node);
              counter++;
            } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
                     instance.shopproduit.getChildren().add(hbox);
}

    @FXML
    private void confirmerpanier(ActionEvent event) {
        // must check if paniercourrant is empty or not
        if (!paniercourrant.isEmpty())
        {
         // reset the display
    //   commandeContainer.getChildren().clear();
       //setting the commande structure and initialising the services
            Service_Commande sc=new Service_Commande();
            Service_Detail_Commande sdc=new Service_Detail_Commande();
          User user=ClientMainController.getInstance().getUser();
       commande.setUser(user);
         commande.setPrix(paniercourrant
                .stream()
                .map(e->e.getPrix_total())
                .reduce(0.0f,Float::sum)
        );
       commande=sc.insertCommande(commande);
     
       // looping over paniercourrant to insert all detail_concerning them intoDB
       for (Detail_Commande dc:paniercourrant)
       {    
           System.out.println("commande courrant a ainserer0"+commande);
           dc.setCommande(commande);
           // calcule store depends du store    integre rayen
           
           sdc.insert(dc);
       }
      
       HistoriqueCommandeController.getInstance().updatedisplayhistorique();
       // empty panier display
       Paniercourrante.getChildren().clear();
       // reseting current panier
       paniercourrant.clear();
    }
    }
    
    
    public void visitestore(Produit p)
    {
        visiterstore.setVisible(true);
        visiterstore.getChildren().clear();
        
          try {                 System.out.println("counter break");

                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Store/Main.fxml"));
                Node node = produitLoader.load();
              MainController cc=produitLoader.getController();
                    cc.ajouterStoreparProduit(p);
                      visiterstore.getChildren().add(node);

            } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void closestore(){
        visiterstore.setVisible(false);
    }
}
