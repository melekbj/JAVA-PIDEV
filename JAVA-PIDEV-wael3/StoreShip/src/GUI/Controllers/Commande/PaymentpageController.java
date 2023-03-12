/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers.Commande;

import controller.PaymentProcessor;
import controller.Service_Commande;
import controller.Service_Detail_Commande;
import entity.Commande;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 * @author Plop
 */
public class PaymentpageController implements Initializable {

     
      @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expirationMonthField;
    @FXML
    private TextField expirationYearField;
    @FXML
    private TextField cvcField;
    @FXML
    private Label amountField;
    @FXML
    private Button submitButton;
    private Commande commande;
    @FXML
    private Label ref;
    @FXML
    private Label uniqueproducts;
    @FXML
    private Label totalproduct;
    @FXML
    private Label destination;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        submitButton.setDisable(true);
        // TODO
        cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            checkallfields();
        });
        expirationMonthField.textProperty().addListener((observable, oldValue, newValue) -> {
            checkallfields();
        });
        expirationYearField.textProperty().addListener((observable, oldValue, newValue) -> {
            checkallfields();
        });
        cvcField.textProperty().addListener((observable, oldValue, newValue) -> {
            checkallfields();
        });
        
    }
    private void getinformations(){
        Service_Detail_Commande sdc=new Service_Detail_Commande();
        uniqueproducts.setText(String.valueOf(sdc.getnumberuniqueproducts(commande)));
        totalproduct.setText(String.valueOf(sdc.getnumberallproducts(commande)));
        destination.setText(commande.getDestination());
        ref.setText(String.valueOf(commande.getId()));
    }
    public boolean validateinputcardnumber() {
        if (cardNumberField.getText().matches("\\d{16}")) {
            cardNumberField.setStyle("-fx-text-box-border: #2ecc71; -fx-focus-color: #2ecc71; -fx-text-fill: green;");
        } else {
            cardNumberField.setStyle("-fx-text-box-border: #e74c3c; -fx-focus-color: #e74c3c; -fx-text-fill: red");
        }
        return cardNumberField.getText().matches("\\d{16}");

    }

    public boolean validatinputmounthexpiration() {
        if (expirationMonthField.getText().matches("0[1-9]|1[0-2]")) {
            expirationMonthField.setStyle("-fx-text-box-border: #2ecc71; -fx-focus-color: #2ecc71; -fx-text-fill: green;");

        } else {
            expirationMonthField.setStyle("-fx-text-box-border: #e74c3c; -fx-focus-color: #e74c3c; -fx-text-fill: red");

        }
        return expirationMonthField.getText().matches("0[1-9]|1[0-2]");
    }

    public boolean validateinputyearexpiration() {
        if (expirationYearField.getText().matches("\\d{4}")) {
            expirationYearField.setStyle("-fx-text-box-border: #2ecc71; -fx-focus-color: #2ecc71; -fx-text-fill: green;");
        } else {
            expirationYearField.setStyle("-fx-text-box-border: #e74c3c; -fx-focus-color: #e74c3c; -fx-text-fill: red");
        }
        return expirationYearField.getText().matches("\\d{4}");
    }

    public boolean validateinputcvc() {
        if (cvcField.getText().matches("\\d{3,4}")) {
            cvcField.setStyle("-fx-text-box-border: #2ecc71; -fx-focus-color: #2ecc71; -fx-text-fill: green;");
        } else {
            cvcField.setStyle("-fx-text-box-border: #e74c3c; -fx-focus-color: #e74c3c; -fx-text-fill: red");
        }
        return cvcField.getText().matches("\\d{3,4}");
    }

    public boolean checkallfields() {
        boolean status = false;
        if (validateinputcardnumber() && validatinputmounthexpiration() && validateinputyearexpiration() && validateinputcvc() )
        {    status=true;
                     submitButton.setDisable(false);

        }
        return status;
    }
    
    public boolean payementmethod(){
        PaymentProcessor payement=new PaymentProcessor();
        return payement.processPayment(
                cardNumberField.getText(),
                expirationMonthField.getText(),
                expirationYearField.getText(),
                cvcField.getText(),commande
        );
    }

    @FXML
    private void payment(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

       if ( payementmethod()){
          alert.setTitle("Payment Status");
                            alert.setHeaderText(null);
                            alert.setContentText("Payment Successfully");
                            alert.showAndWait();
                            updatecommande();
       }
       
                            else{
                                     alert.setTitle("Payment Status");
                            alert.setHeaderText(null);
                            alert.setContentText("Payment Failure");
                            alert.showAndWait(); 
                                    }
    }
    
    public void getcommande(Commande c){
        commande=c;
        amountField.setText(String.valueOf(c.getPrix()));
                getinformations();

    }
    
    private void updatecommande(){
        Service_Commande sc=new Service_Commande();
        commande.setEtat("Payed");
        sc.updateetat(commande);
        HistoriqueCommandeController.getInstance().updatedisplayhistorique();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        HistoriqueCommandeController.getInstance().Cancel();
    }
}
