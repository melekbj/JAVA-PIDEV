/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Categorie;
import entite.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
/**
 *
 * @author SAFAA
 */
public class ServiceCategorie implements IService<Categorie> {
    
      private Connection conn;

    public ServiceCategorie() {
        conn = DataSource.getInstance().getCnx();
    
    }
    
    
    //  private int id_categorie;
    //private String nom_categorie;
   // private String description_categorie;

   
    public void insert(Categorie t) {
             String requete = "insert into categorie( nom_categorie,description_categorie)VALUES ('"+t.getNom_categorie()+"','"+t.getDescription_categorie()+"')";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
         /*  pst.setString(1, t.getNom_produit()); 
           pst.setString(2, t.getPhoto_produit());
            pst.setFloat(3, t.getPrix_produit());
            pst.setInt(4, t.getQuantité_produit());
            pst.setInt(5, t.getId_catégorie());
             pst.setInt(6, t.getId_store()); */
            pst.executeUpdate();
            System.out.println("Categorie ajouté!");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Categorie non ajouté!");
        }

    }


    public void delete(Categorie t) {
              try {
            String requete = "DELETE FROM categorie WHERE nom_categorie=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getNom_categorie());
            pst.executeUpdate();
            System.out.println("Categorie supprimé!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Categorie non supprimé!");
        }
    }
 //  private int id_categorie;
    //private String nom_categorie;
   // private String description_categorie;
    
    public void update(Categorie t) {
        String requete = "UPDATE categorie SET nom_categorie=?,description_categorie=?  WHERE id_categorie=?";
       try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
           pst.setString(1, t.getNom_categorie());
           pst.setString(2, t.getDescription_categorie());
            pst.setInt(3, t.getId_categorie());

            pst.executeUpdate();
            System.out.println("Categorie_id " + t.getId_categorie()+":" + " modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Categorie non modifié!");
        }
    }

   
    public List<Categorie> readAll() {
         List<Categorie> list=new ArrayList<>();
            String requete="select * from categorie ";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
              Categorie t=new Categorie(rs.getInt("id_categorie"), rs.getString("nom_categorie"),rs.getString("description_categorie"));

               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     
      
    }


    public Categorie readById(int id) {
        return new Categorie(id);
    }
    
}

