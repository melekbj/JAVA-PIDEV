/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Categorie;
import entite.Produit;
import entite.User;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author SAFAA
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

    @Override
    public void insert(Produit t) {
       String requete = "insert into produit(nom_produit,photo_produit,prix_produit,quantite_produit,id_categorie,etat_produit,id_user)VALUES ('"+t.getNom_produit()+"','"+t.getPhoto_produit()+"','"+t.getPrix_produit()+"', '"+t.getQuantite_produit()+"','"+t.getCategorie().getId_categorie()+"', '"+t.getEtat_produit()+"','"+t.getUser().getId_user()+"')";
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
    }

    @Override
 
   
    public void delete(Produit p) {
        try {
            String requete = "DELETE FROM produit WHERE nom_produit=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, p.getNom_produit());
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
         String requete = "UPDATE produit SET nom_produit=?,photo_produit=?,prix_produit=?,quantite_produit=?, id_categorie=?, etat_produit=?, id_user=?  WHERE id_produit=?";
       try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
           pst.setString(1, t.getNom_produit());
           pst.setString(2, t.getPhoto_produit());
         
           
            pst.setFloat(3, t.getPrix_produit());
            pst.setInt(4, t.getQuantite_produit());
            pst.setInt(5, t.getCategorie().getId_categorie());
            pst.setString(6, t.getEtat_produit());
            pst.setInt(7, t.getUser().getId_user());
            pst.setInt(8, t.getId_produit());

            pst.executeUpdate();
            System.out.println("Poduit_id " + t.getId_produit()+":" + " modifié !");

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
              User U=new User(rs.getInt("id_user"));
            
              Produit t=new Produit(rs.getInt("id_produit"), rs.getString("nom_produit"),rs.getString("photo_produit"),
                      rs.getFloat("prix_produit"),rs.getInt("quantite_produit"), new Categorie(rs.getInt("id_categorie")) ,
                      rs.getString("etat_produit"),U);

               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     
       
    }

  
    public Produit readById(int id_produit) {
        String requete = "SELECT * FROM produit WHERE id_produit="+id_produit;
      Produit p=new Produit();
        PreparedStatement pst;
        try {
         pst = conn.prepareStatement(requete);
     
        ResultSet rst = pst.executeQuery(requete);
            System.out.println(rst);
        while (rst.next()) {
                p=new Produit(rst.getInt("id_produit"),rst.getString("nom_produit"),
                rst.getString("photo_produit"),
                rst.getFloat("prix_produit"),
                rst.getInt("quantite_produit"),
                new Categorie(rst.getInt("id_categorie"),"smartphone",""),
                rst.getString("etat_produit"),
                new User(rst.getInt(1)));
                
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
}