/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd3a9;

import java.time.LocalDate;
import java.util.Date;

import entite.Personne;
import entite.Reclamation;
import java.util.ArrayList;
import service.ServicePersonne;
import service.ServiceProduit;
import service.ServiceReclamation;
import utils.DataSource;

/**
 *
 * @author wiemhjiri
 */
public class ConnexionBD3A9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        DataSource ds1 =DataSource.getInstance();
//        System.out.println(ds1);
//        DataSource ds2 = DataSource.getInstance();
//        System.out.println(ds2);
//        DataSource ds3 = DataSource.getInstance();
//        System.out.println(ds3);
        Personne p=new Personne("3a9", "test", 23);
        ServicePersonne ps=new ServicePersonne();
       // ps.insert(p);
     //  ps.insertPst(p);
     
     Reclamation r = new Reclamation(30,55,"le produit nest pas fonctionel",LocalDate.now(),"produit",5,5,"dazeda");
     ServiceReclamation rs=new ServiceReclamation();
    //    System.out.println(rs.reclamationPendingCount());
        ServiceProduit sp = new ServiceProduit();
        System.out.println(sp.readById(6));
    }

}
