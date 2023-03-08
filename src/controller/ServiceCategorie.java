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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ServiceCategorie {
    
      private Connection conn;

    public ServiceCategorie() {
        conn = ConnexionSource.getInstance().getCnx();
    
    }
    
    
    //  private int id_categorie;
    //private String nom_categorie;
   // private String description_categorie;

 
    public void insert(Categorie t) {
             String requete = "insert into categorie( nom,description)VALUES ('"+t.getNom()+"','"+t.getDescription()+"')";
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
            String requete = "DELETE FROM categorie WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, t.getId());
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
        String requete = "UPDATE categorie SET nom=?,description=?  WHERE id=?";
       try {
            
            PreparedStatement pst = conn.prepareStatement(requete);
            
           pst.setString(1, t.getNom());
           pst.setString(2, t.getDescription());
            pst.setInt(3, t.getId());

            pst.executeUpdate();
            System.out.println("Categorie_id " + t.getId()+":" + " modifié !");

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
              Categorie t=new Categorie(rs.getInt("id"), rs.getString("nom"),rs.getString("description"));

               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     
    }

 
    public Categorie readById(int id) {
              Categorie CT=new Categorie();
      try {
            String requete = "Select *  FROM categorie WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
           
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {   System.out.println("categorie Trouve");
                CT= new Categorie(rs.getInt(1),rs.getString(2),rs.getString(3));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             System.out.println("categorie  inExistenteé!");
        }
      return CT;
    }
    
    
    // return the id of the categorie by name
    public int getCategorieByName(String titre){
      int i=0;
      try {
            String requete = "Select *  FROM categorie WHERE nom=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, titre);
            
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {   System.out.println("categorie Trouve");
                i= rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             System.out.println("categorie  inExistenteé!");
        }
      return i;
  
    }
    
    
      public ObservableList<Categorie> likeByCategorie(String a) {
     
        ObservableList<Categorie> listData = FXCollections.observableArrayList();
        try {
            String sql = "select * from categorie where id like '%"+a+"%' or nom like '%"+a+"%'   ";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {  
                Categorie u = new  Categorie();
                
               u.setId(rs.getInt("id"));  
      u.setNom(rs.getString("nom"));
      u.setDescription(rs.getString("description"));      
     
      
        
                listData.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }
    

}
