/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author Jmili
 */

import Util.DataSource;
import Entities.Categorie;
import Entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCategorie implements IService<Categorie> {
   
      private Connection conn;

    public ServiceCategorie() {
        conn = DataSource.getInstance().getCnx();
   
    }
   
   
    //  private int id_categorie;
    //private String nom_categorie;
   // private String description_categorie;

    @Override
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

    @Override
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
    @Override
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

    @Override
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
    

    @Override
    public Categorie readById(int id) {
         Categorie list=new Categorie();
            String requete="select * from categorie ";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
              Categorie t=new Categorie(rs.getInt("id"), rs.getString("nom"),rs.getString("description"));

               list.setNom(t.getNom());
               list.setDescription(t.getDescription());
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }
}