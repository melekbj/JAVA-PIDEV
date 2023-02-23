/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;
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
    //  FXMLLoader Fload2=new FXMLLoader(getClass().getResource("/GUI/FXML/ListProduit.fxml"));
            FXMLLoader Fload2=new FXMLLoader(getClass().getResource("/GUI/FXML/formulaireProduit.fxml"));

      //FXMLLoader Fload=new FXMLLoader(getClass().getResource("/GUI/FXML/FormulaireStore.fxml"));
      System.out.println(Fload2.getLocation());
//      Parent root=Fload.load();
      Parent root2=Fload2.load();       
         
      Scene scene = new Scene(root2); 
//      Scene scene2 = new Scene(root2); 

       
       primaryStage.setTitle("StoreShip"); 
          //Button button = new Button("Forward");
        //button.setOnAction(e -> primaryStage.setScene(scene2));
         
        //Button button2 = new Button("Backwards");
        //button2.setOnAction(e -> primaryStage.setScene(scene));
       
      primaryStage.setScene(scene); 
      primaryStage.show(); 
   }    
   public static void main(String args[]){          
      launch(args);     
   } 
}
