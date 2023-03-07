/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package storeship;

import connexion.ConnexionSource;
import controller.UserController;
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
        
        
        uc.AddUser(u);
//          uc.delete(u);
//            uc.update(u);
        uc.ReadAllUsers().forEach(System.out::println);
        
                
    }
    
}
