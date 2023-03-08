/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;
import GUI.Controllers.Rayen.MainController;
import GUI.Controllers.Rayen.StoreController;
import javafx.scene.Group;
import javafx.scene.paint.Color; 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Jmili
 */
public class NewFXMain extends Application {    
   @Override     
   public void start(Stage primaryStage) throws Exception {      
      FXMLLoader Fload2=new FXMLLoader(getClass().getResource("/GUI/FXML/Main.fxml"));
            //FXMLLoader Fload2=new FXMLLoader(getClass().getResource("/GUI/FXML/formulaireProduit.fxml"));
      //FXMLLoader Fload2=new FXMLLoader(gtClass().getResource("/GUI/FXML/FormulaireStore.fxml"));
//      Parent root=Fload.load();
      Parent root2=Fload2.load(); 
      MainController LPC=Fload2.getController();
      LPC.ajouterlistproduit();
      LPC.ajouterStore();
      Scene scene = new Scene(root2); 
//      Scene scene2 = new Scene(root2);        
       primaryStage.setTitle("StoreShip");
      primaryStage.setScene(scene); 
      primaryStage.show(); 
   }    
   public static void main(String args[]){          
      launch(args);     
   } 
}
