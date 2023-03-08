
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Scene;

import javafx.scene.Parent;

import javafx.scene.layout.StackPane;

import static javafx.application.Application.launch;

import java.io.IOException;

import javafx.scene.Scene;

import javafx.scene.Parent;

import javafx.scene.layout.StackPane;

import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;

import javafx.application.Application;

/**
 *
 * @author asus
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
   
      
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminReclamation.fxml"));
            //FXMLLoader loader=new FXMLLoader(getClass().getResource("statReclamatinEtat.fxml"));
         //  FXMLLoader loader=new FXMLLoader(getClass().getResource("statReclamationType.fxml"));
       // FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterReclamationReservation.fxml"));
     FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterReclamationProduit.fxml"));
      //FXMLLoader loader=new FXMLLoader(getClass().getResource("afficherReclamationsClient.fxml"));
 
            
      StackPane window=new StackPane();
      try{
          System.out.println(loader.getLocation()); 
      Parent root=loader.load();
         window.getChildren().add(root);
        Scene custom=new Scene(window);
        custom.getStylesheets().add("GUI/styles.css");
        primaryStage.setScene(custom);
      }
      catch(IOException ex){
          System.out.println(ex);
      }
       // DetailsPersonneController dc=loader.getController();
     
       primaryStage.setTitle("Hello World!");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
