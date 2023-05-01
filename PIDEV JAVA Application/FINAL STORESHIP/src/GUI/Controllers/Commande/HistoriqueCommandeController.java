/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers.Commande;

import GUI.Controllers.ClientMainController;
import GUI.FXML.Reclamation.ajouterReclamationProduitController;
import controller.Service_Commande;
import entity.Commande;
import entity.Detail_Commande;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class HistoriqueCommandeController implements Initializable {

    @FXML
    private VBox commandelist;
    @FXML
    private ScrollPane panierdisplay;
    @FXML
    private VBox detailcommande;
    @FXML
    private TextField Minpricevalue;
    @FXML
    private TextField Maxpricevalue;
    private static List<Commande> locallist=new ArrayList<>();
    private static HistoriqueCommandeController instance;
    public static HistoriqueCommandeController getInstance(){
        return instance;
    }
    @FXML
    private Button applyfilter;
    @FXML
    private Pane Paymentholder;
    @FXML
    private Pane reclamationpane;
    @FXML
    private Pane DestinationPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                instance=this;
                addCommandeNodes(getfromdataall());
                reclamationpane.setVisible(false);
                DestinationPane.setVisible(false);
    }    

    public void updatedisplayhistorique(){
         Paymentholder.setVisible(false);
         addCommandeNodes(getfromdataall());
    }
     @FXML
    private void applypricefilter(ActionEvent event) {
       applypricefilter();
    }
    
    private void applypricefilter(){
         List<Commande> testlist=new ArrayList<>();
         System.out.println("Local list ==="+locallist);
         if (!Maxpricevalue.getText().matches("\\d+"))
         {
             Maxpricevalue.setText("0");
         }
         if (!Minpricevalue.getText().matches("\\d+"))
         {
                          Minpricevalue.setText("0");

         }
         // test si un changement est effectuer
         if(Integer.parseInt(Maxpricevalue.getText())!=0 || Integer.parseInt(Minpricevalue.getText())!=0)
         {for (Commande c:locallist){
                if ((c.getPrix()>=Float.parseFloat(Minpricevalue.getText()) && c.getPrix()<=Float.parseFloat(Maxpricevalue.getText())))
                {
                    testlist.add(c);
                }
                if (c.getPrix()>=Float.parseFloat(Minpricevalue.getText()) && Integer.parseInt(Maxpricevalue.getText())==0)
                {
                     testlist.add(c);
                }
                if (c.getPrix()>=Float.parseFloat(Maxpricevalue.getText()) && Integer.parseInt(Minpricevalue.getText())==0)
                {
                     testlist.add(c);
                }
         }
         addCommandeNodes(testlist);
         } // sinon default affichage
         else{
             addCommandeNodes(locallist);
         }
            
    }

   // ajoute les instances de COMMANDE a pour populé l'affichage
    public void addCommandeNodes(List<Commande> list) {
                          instance.commandelist.getChildren().clear();
                          startGlowAnimation();
        for (Commande c:list) {
            try {
                FXMLLoader commandeLoader = new FXMLLoader(getClass().getResource("/GUI/FXML/Commande/Commande.fxml"));
                Node node = commandeLoader.load();
                
             CommandeController cc=commandeLoader.getController();
               cc.setCommande(c);
              instance.commandelist.getChildren().add(node);
            } catch (IOException ex) {
            Logger.getLogger(HistoriqueCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     @FXML
    private void selectall(ActionEvent event) {
        addCommandeNodes(getfromdataall());
    }
   

    @FXML
    private void selectlast7days(ActionEvent event) {
          Service_Commande se=new Service_Commande();
          User u=ClientMainController.getInstance().getUser();
        
        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, 1);
        Date date1 = cal.getTime(); 
        cal.add(Calendar.DATE, -8);
        Date date2 = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr1 = dateFormat.format(date1);
        String dateStr2 = dateFormat.format(date2);
               locallist=se.readAllBetweendate(u,dateStr1,dateStr2);
               applypricefilter();       

    }
   
    public List<Commande> getfromdataall(){
    Service_Commande se=new Service_Commande();
                     
          User u=ClientMainController.getInstance().getUser();
       locallist=se.readAllByUser(u);
       return locallist;
    }

    @FXML
    private void selectoday(ActionEvent event) {
      Service_Commande se=new Service_Commande();
          User u=ClientMainController.getInstance().getUser();
        
          Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
        Date date1 = cal.getTime(); 
        cal.add(Calendar.DATE, -1);
        Date date2 = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr1 = dateFormat.format(date1);
        String dateStr2 = dateFormat.format(date2);
                       locallist=se.readAllBetweendate(u,dateStr1,dateStr2);
       applypricefilter(); 
    }

    @FXML
    private void selectlast30days(ActionEvent event) {
          Service_Commande se=new Service_Commande();
          User u=ClientMainController.getInstance().getUser();
        
        Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
        Date date1 = cal.getTime(); 
        cal.add(Calendar.DATE, -30);
        Date date2 = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr1 = dateFormat.format(date1);
        String dateStr2 = dateFormat.format(date2);
               locallist=se.readAllBetweendate(u,dateStr1,dateStr2);
        applypricefilter(); 
    }
    
    

   
    public void populatedetails(List<Detail_Commande> listproduit){
        // add list produit in produit_container  using the same instance as Commandepanier
       instance.detailcommande.getChildren().clear();
        System.out.println("populating details");
        for (Detail_Commande c:listproduit) {
            try {
                System.out.println("items ===============");
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource( "/GUI/FXML/Commande/Detail_commande.fxml"));
                Node node = produitLoader.load();
              Detail_commandeController detail_commande=produitLoader.getController();
                detail_commande.setDetail_Produit_display(c);
           
                instance.detailcommande.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    public void processpayment(Commande c){
                FXMLLoader payementloader=new FXMLLoader(getClass().getResource("/GUI/FXML/Commande/paymentpage.fxml"));
                
                    
        try {
            Node node=payementloader.load();
            PaymentpageController ppc=payementloader.getController();
            ppc.getcommande(c);
            Paymentholder.setVisible(true);
            Paymentholder.getChildren().add(node);
            
                 

        } catch (IOException ex) {
            Logger.getLogger(HistoriqueCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public void Cancel(){
        Paymentholder.getChildren().clear();
        Paymentholder.setVisible(false);
    }
      private void startGlowAnimation() {
          FadeTransition fade = new FadeTransition(Duration.seconds(1), commandelist);
        fade.setFromValue(0.2);
        fade.setToValue(1.0);
        fade.setAutoReverse(true);
        fade.play();
    }
      
     public void reclamerDetail(Detail_Commande p){
         System.out.println("historique detail_commande "+p);
        try {    
            reclamationpane.getChildren().clear();
         reclamationpane.setVisible(true);         
         FXMLLoader loadreclamation=new FXMLLoader(getClass().getResource("/GUI/FXML/Reclamation/ajouterReclamationProduit.fxml"));
          Node  node = loadreclamation.load();
              
            ajouterReclamationProduitController reclamationControlelr=loadreclamation.getController();
         reclamationControlelr.reclamerproduit(p);
         reclamationpane.getChildren().add(node);
        } catch (IOException ex) {
            Logger.getLogger(HistoriqueCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         
     }
     
     public void closereclamation(){
         reclamationpane.setVisible(false);
     }
}
