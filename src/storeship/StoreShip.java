/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package storeship;

import Entities.Store;
import Entities.User;
import Service.ProduitStoreService;
import Service.StoreService;
import Util.DataSource;

/**
 *
 * @author Jmili
 */
public class StoreShip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSource ds1 =DataSource.getInstance();
        ProduitStoreService PSS=new ProduitStoreService();                         
            System.out.println(PSS.readById(1));  
            //System.out.println(PSS.readAll());  
 Store st=new Store("store","location",new User(1));
 StoreService Sts=new StoreService();
 //Sts.insert(st);
//Sts.delete(st);
//Sts.update(st);
//System.out.println(Sts.readAll());
      // System.out.println(Sts.readById(1));    
    }
    
}
