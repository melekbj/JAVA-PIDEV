/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connexion.ConnexionSource;
import entity.Categorie;
import entity.Produit;
import entity.User;
import java.sql.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SAFAA
 */
public class ServiceProduit implements Iservice<Produit> {
   

    private Connection conn;

    public ServiceProduit() {
        conn = ConnexionSource.getInstance().getCnx();
    
    }
    // private String nom_produit;
    //private String photo_produit;
   // private float prix_produit;
    //private int quantité_produit;
    //private int id_catégorie;
    //private int id_store;

    @Override
    public void insert(Produit t) {

        
        //       String requete = "insert into produit(nom,photo,prix,quantite,categorie_id,etat,user_id)VALUES ('"+t.getNom()+"','"+t.getPhoto()+"','"+t.getPrix()+"', '"+t.getQuantite()+"','"+t.getCategorie().getId()+"', '"+t.getEtat()+"','"+t.getUser().getId()+"')";
               String requete = "insert into produit("
                       + "nom,"
                       + "photo,"
                       + "prix,"
                       + "quantite,"
                       + "categorie_id,"
                       + "etat)VALUES ('"
                       + ""+t.getNom()+"',"
                       + "'"+t.getPhoto()+"',"
                       + "'"+t.getPrix()+"',"
                       + " '"+t.getQuantite()+"',"
                       + ""+t.getCategorie().getId()+", "
                       + "'"+t.getEtat()+"')";
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
         String requete = "UPDATE produit SET nom=?,photo=?,prix=?,quantite=?, categorie_id=?, etat=?  WHERE id=?";
       try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
           pst.setString(1, t.getNom());
           pst.setString(2, t.getPhoto());
         
           
            pst.setFloat(3, t.getPrix());
            pst.setInt(4, t.getQuantite());
            pst.setInt(5, 1);//refaire id categorie
            pst.setString(6, t.getEtat());
           // pst.setInt(7, 1);//refaire id categorie
            pst.setInt(7, t.getId());

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
            
              Produit t=new Produit(rs.getInt("id"), rs.getString("nom"),rs.getString("photo"),
                      rs.getFloat("prix"),rs.getInt("quantite"), new Categorie(rs.getInt("categorie_id")) ,
                      rs.getString("etat"));

               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     
       
    }

    @Override
    public Produit readById(int id_produit) {
        String requete = "SELECT * FROM produit WHERE id=?";
      Produit p=new Produit();
        PreparedStatement pst;
        try {
         pst = conn.prepareStatement(requete);
       pst.setInt(1, id_produit);
        ResultSet rst = pst.executeQuery(requete);
        while (rst.next()) {
                p=new Produit(rst.getInt(1),rst.getString(2),
                rst.getString(3),
                rst.getFloat(4),
                rst.getInt(5),
                new Categorie(rst.getInt(6)),
                rst.getString(7)
                );
                
                /*              pst.setInt(1, p.getId_produit());

           pst.setString(2, p.getNom_produit());
           pst.setString(3, p.getPhoto_produit());
    

            pst.setFloat(4, p.getPrix_produit());
            pst.setInt(5, p.getQuantite_produit());
            pst.setInt(5,);
            pst.setString(7, p.getEtat_produit());
             pst.setInt(8, p.getUser().getId_user()); */
        }}
 catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
     public ObservableList<Produit> likeByProduit(String a) {
     
        ObservableList<Produit> listData = FXCollections.observableArrayList();
        try {
            String sql = "select * from produit where id like '%"+a+"%' or nom like '%"+a+"%'   ";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {  
                Produit u = new Produit();
                
               u.setId(rs.getInt("id"));  
      u.setNom(rs.getString("nom"));
      u.setPhoto(rs.getString("photo"));      
        u.setPrix(rs.getFloat("prix"));
         u.setQuantite(rs.getInt("quantite")); 
      u.setEtat(rs.getString("etat"));
      
        
                listData.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }
}
