/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package storeship;

import connexion.ConnexionSource;
import controller.Evenement_Service;
import controller.Reservation_Service;
import controller.UserController;
import entity.Evenement_entite;
import entity.Reservation_entite;
import entity.User;
import java.time.LocalDate;

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
     /*           
        User u = new User("melekbejaoui23@gmail.com", "client", "melek", "ez,fl"
            , "zknfkzl", 20, "zklnklz", "klenkfl", "fef", "5555555555",0);
        
        UserController uc = new UserController();
        
        */
        //uc.AddUser(u);
//          uc.delete(u);
//            uc.update(u);
       // uc.ReadAllUsers().forEach(System.out::println);
        
      LocalDate d1=LocalDate.of(1999, 03, 06);
       LocalDate d2=LocalDate.of(2004, 03, 06);
      //  Date d1= Date.valueOf("2022-12-3");
      Evenement_entite e1 = new Evenement_entite(d1,d2,"hhhhhhhh","xxxx","rrrrr","aaaa",2);
/*     Evenement_Service es=new Evenement_Service();
          e1.setIdEvenement(45);
        es.update(e1);
  */     

Evenement_Service es = new Evenement_Service();
es.insert(e1);
      // Reservation_entite re = new Reservation_entite(4,5);
  //  Reservation_Service rs=new Reservation_Service();
  // rs.insert(re);
   
    }
    
}
