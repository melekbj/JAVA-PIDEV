/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ConnexionSource {
    
    private String url="jdbc:mysql://localhost:3306/storeship";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static ConnexionSource instance;

    public ConnexionSource() {
        
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static ConnexionSource getInstance(){
        if(instance==null)
            instance=new ConnexionSource();
        return instance;
    }
    
    public Connection getCnx() {
        return cnx;
    }
     
     
     
     
     
     
     
     
}
