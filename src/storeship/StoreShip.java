/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package storeship;

import connexion.ConnexionSource;
import controller.ServiceProduit;
import controller.UserController;
import entity.Categorie;
import entity.Produit;
import entity.User;

/**
 *
 * @author Admin
 */
public class StoreShip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnexionSource ds1 = ConnexionSource.getInstance();
//      System.out.println(ds1);
                
        User u = new User("melekbejaoui@gmail.com", "client", "melek", "ez,fl"
            , "zknfkzl", 20, "zklnklz", "klenkfl", "fef", "5555555555",0);
        
        UserController uc = new UserController();
        u.setId(1);
        
      //  uc.AddUser(u);
//          uc.delete(u);
//            uc.update(u);
        uc.ReadAllUsers().forEach(System.out::println);
        
        
        
 
       // System.out.println(ds1);
       
        // Produit p=new Produit(6,"3a", "est", 23 , 2 , 3 ,22);
         Categorie C=new Categorie(1,"voiture","mercedes");
          Produit p3=new Produit(11,"voiture", "phooooo", 2000000 , 888 ,C,"22");
                 
//System.out.println(ds1);
        //Produit p1=new Produit("3a19", "tesst", 33 , 24 , 7 ,22);
        //Produit p2=new Produit("3a19", "tesst", 33 , 24 , 7 ,22);
        //Produit p3=new Produit("3a6", "tt", 3 , 4 , 7 ,22);
         //Produit p=new Produit;
        ServiceProduit ps=new ServiceProduit();
         // ServiceCategorie ps=new ServiceCategorie();
       
     //ps.insert(p3);
      //ps.delete(p3);
       // ps.update(p3);
       // ps.readById(17);
       
       ps.readAll().forEach(System.out::println);
        
                
    }
    
}
