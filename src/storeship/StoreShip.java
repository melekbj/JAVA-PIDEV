/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package storeship;

import entity.Store;
import entity.User;
import controller.ProduitStoreService;
import controller.StoreService;
import connexion.ConnexionSource;
import controller.ServiceProduit;
import entity.Produit;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Jmili
 */
public class StoreShip {


public void envoyer() {
// Etape 1 : Création de la session
Properties props = new Properties();
 String username = "mohamedrayen.jmili@esprit.tn";
 String password = "223JMT0063";
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
props.put("mail.smtp.ssl.protocols", "TLSv1.2");
Session session = Session.getInstance(props,new Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("votre_mail@gmail.com"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("mohamedrayenjmili@gmail.com"));
message.setSubject("Test email");
message.setText("Bonjour, ce message est un test ...");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} }

    public static void main(String[] args) throws SQLException {
        //StoreShip test = new StoreShip();
        // test.envoyer();
        ConnexionSource ds1 =ConnexionSource.getInstance();
        ProduitStoreService PSS=new ProduitStoreService();            
         StoreService Sts=new StoreService();
 ServiceProduit SP=new ServiceProduit();
         System.out.println(PSS.readStoreById(new Produit(7)));  
//                        System.out.println(Sts.readByStoreId(1));  

            //System.out.println(PSS.readAll());  
 //Store st=new Store("store","location",new User(1));

// Produit produit=SP.readById(6); 
 //Sts.insert(st);
//Sts.delete(st);
//Sts.update(st);
//System.out.println(Sts.readAll());
// System.out.println(Sts.readById(3));    
//        PSS.readStoreById(produit);
 
    }
    
}
