/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Entities.Categorie;
import Entities.Produit;
import Entities.User;
import Util.DataSource;
import java.sql.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jmili
 */
public class ServiceProduit implements IService<Produit> {
   

    private Connection conn;

    public ServiceProduit() {
        conn = DataSource.getInstance().getCnx();
   
    }
    // private String nom_produit;
    //private String photo_produit;
   // private float prix_produit;
    //private int quantité_produit;
    //private int id_catégorie;
    //private int id_store;
/*
    @Override
    public void insert(Produit t) {

        //       String requete = "insert into produit(nom,photo,prix,quantite,categorie_id,etat,user_id)VALUES ('"+t.getNom()+"','"+t.getPhoto()+"','"+t.getPrix()+"', '"+t.getQuantite()+"','"+t.getCategorie().getId()+"', '"+t.getEtat()+"','"+t.getUser().getId()+"')";
               String requete = "insert into produit("
                       + "nom,"
                       + "photo,"
                       + "prix,"
                       + "quantite,"
                       + "categorie_id,"
                       + "etat,"
                       + "user_id)VALUES ('"
                       + ""+t.getNom()+"',"
                       + "'"+t.getPhoto()+"',"
                       + "'"+t.getPrix()+"',"
                       + " '"+t.getQuantite()+"',"
                       + ""+1+", "
                       + "'"+t.getEtat()+"',"
                       + "'"+1+"')";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
         /*  pst.setString(1, t.getNom_produit());
           pst.setString(2, t.getPhoto_produit());
            pst.setFloat(3, t.getPrix_produit());
            pst.setInt(4, t.getQuantité_produit());
            pst.setInt(5, t.getId_catégorie());
             pst.setInt(6, t.getId_store()); 
            pst.executeUpdate();
            System.out.println("Produit ajouté!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Produit non ajouté!");
        }*/
   @Override
    public void insert(Produit t) {

        //       String requete = "insert into produit(nom,photo,prix,quantite,categorie_id,etat,user_id)VALUES ('"+t.getNom()+"','"+t.getPhoto()+"','"+t.getPrix()+"', '"+t.getQuantite()+"','"+t.getCategorie().getId()+"', '"+t.getEtat()+"','"+t.getUser().getId()+"')";
               String requete =" INSERT INTO `produit`(`nom`, `prix`, `quantite`, `etat`, `categorie_id`) VALUES  VALUES ("
                       +t.getNom()+","
                       +t.getPrix()+","
                       +t.getQuantite()+","
                       +t.getEtat()+","
                       +1+")";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
         /*  pst.setString(1, t.getNom_produit());
           pst.setString(2, t.getPhoto_produit());
            pst.setFloat(3, t.getPrix_produit());
            pst.setInt(4, t.getQuantité_produit());
            pst.setInt(5, t.getId_catégorie());
             pst.setInt(6, t.getId_store()); */
            pst.executeUpdate();
            System.out.println("Produit ajouté!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Produit non ajouté!");
        }
         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
 
   
    public void delete(Produit p) {
        try {
            String requete = "DELETE FROM produit WHERE id=?";
           
            PreparedStatement pst = conn.prepareStatement(requete);
           
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Produit supprimé!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Produit non supprimé!");
        }
    }

    @Override
    // private String nom_produit;
    //private String photo_produit;
   // private float prix_produit;
    //private int quantité_produit;
    //private int id_catégorie;
    //private int id_store;
    public void update(Produit t) {
         String requete = "UPDATE produit SET nom=?,photo=?,prix=?,quantite=?, categorie_id=?, etat=?, user_id=?  WHERE id=?";
       try {
           
            PreparedStatement pst = conn.prepareStatement(requete);
           
           pst.setString(1, t.getNom());
           pst.setString(2, t.getPhoto());
         
           
            pst.setFloat(3, t.getPrix());
            pst.setInt(4, t.getQuantite());
            pst.setInt(5, 1);//refaire id categorie
            pst.setString(6, t.getEtat());
            pst.setInt(7, 1);//refaire id categorie
            pst.setInt(8, t.getId());

            pst.executeUpdate();
            System.out.println("Produit_id " + t.getId()+":" + " modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Produit non modifié!");
        }


    }
    // private String nom_produit;
    //private String photo_produit;
   // private float prix_produit;
    //private int quantité_produit;
    //private int id_catégorie;
    //private int id_store;

    @Override
    public List<Produit> readAll() {
             List<Produit> list=new ArrayList<>();
            String requete="select * from produit ";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                ServiceCategorie sg =new ServiceCategorie();
            Categorie C =new Categorie();
//            C=sg.readById(rs.getInt("id_categorie"));
          //ServiceUser su =new ServiceUser();
            //U=su.readById(rs.getInt("id_user"));
              User U=new User(rs.getInt("user_id"));
           
              Produit t=new Produit(rs.getInt("id"), rs.getString("nom"),rs.getString("photo"),
                      new Categorie(rs.getInt("categorie_id")) ,rs.getFloat("prix"),rs.getInt("quantite"), 
                      rs.getString("etat"),U.getPrenom());

               list.add(t);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
       
    }

   @Override
public Produit readById(int id_produit) {
    String requete = "SELECT `user_id`, `nom`, `prix`, `quantite`, `etat`, `categorie_id` FROM `produit` WHERE `id`=?";
    Produit p = new Produit();
    PreparedStatement pst;
    try {
        pst = conn.prepareStatement(requete);
        pst.setInt(1, id_produit);
        ResultSet rst = pst.executeQuery();
        UserController usc = new UserController();
        ServiceCategorie SCT = new ServiceCategorie();
        while (rst.next()) {
            p = new Produit(id_produit,
                    usc.readById(rst.getInt(1)).getPrenom(),
                    rst.getString(2),
                    SCT.readById(rst.getInt(6)),
                    rst.getFloat(3),
                    rst.getInt(4),
                    rst.getString(5),
                    null);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
    }
    return p;
}

}

