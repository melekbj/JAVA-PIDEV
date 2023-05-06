/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connexion.ConnexionSource;
import entity.Categorie;
import entity.Produit;
import entity.Reclamation;
import entity.Store;
import entity.User;
import entity.Util.EmailService;
import java.io.UnsupportedEncodingException;
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
public class ServiceProduit {
   

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

 
    public void insert(Produit t,Store s) {

        t.setEtat(1);
        //       String requete = "insert into produit(nom,photo,prix,quantite,categorie_id,etat,user_id)VALUES ('"+t.getNom()+"','"+t.getPhoto()+"','"+t.getPrix()+"', '"+t.getQuantite()+"','"+t.getCategorie().getId()+"', '"+t.getEtat()+"','"+t.getUser().getId()+"')";
              String requete = "insert into produit("
                       + "nom,"
                       + "photo,"
                       + "prix,"
                       + "quantite,"
                       + "categorie_id,"
                       + "etat)VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getNom());
                        pst.setString(2, t.getPhoto());
                                    pst.setDouble(3, t.getPrix());
                                                pst.setInt(4, t.getQuantite());
                                                            pst.setInt(5, t.getCategorie().getId());
pst.setInt(6, 0);



            pst.executeUpdate();
               ResultSet rs = pst.getGeneratedKeys();
    if (rs.next()) {
        int insertedId = rs.getInt(1);
            ProduitStoreService pss=new ProduitStoreService();
            t.setId(insertedId);
                pss.insertProduitStore(t, s);
        
    }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Produit non ajouté!");
        }

         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.

         //To change body of generated methods, choose Tools | Templates.
    }

 
 
   
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
         
           
            pst.setDouble(3, t.getPrix());
            pst.setInt(4, t.getQuantite());
            pst.setInt(5, t.getCategorie().getId());//refaire id categorie
            pst.setInt(6, t.getEtat());
            pst.setInt(7, t.getId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Produit non modifié!");
        }


    }
     public void updatequantite (int id, int quantite){
      String requete = "UPDATE produit SET quantite=? WHERE id=?";
       try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, quantite);
            pst.setInt(2, id);
            pst.executeUpdate();
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

 
    public List<Produit> readAll() {
             List<Produit> list=new ArrayList<>();
            String requete="select * from produit ";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                ServiceCategorie sg =new ServiceCategorie();
            Categorie C =new Categorie();
            C=sg.readById(rs.getInt("categorie_id"));

            
              Produit t=new Produit(rs.getInt("id"), rs.getString("nom"),rs.getString("photo"),
                      rs.getDouble("prix"),rs.getInt("quantite"), C ,
                      rs.getInt("etat"));

               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("============current ITEM LIST ========="+list);
        return list;     
       
    }
    
   
     public List<Produit> readAllActive() {
             List<Produit> list=new ArrayList<>();
            String requete="select * from produit where etat=1 and quantite > 0 ";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                ServiceCategorie sg =new ServiceCategorie();
            Categorie C =new Categorie();
            C=sg.readById(rs.getInt("categorie_id"));

            
              Produit t=new Produit(rs.getInt("id"), rs.getString("nom"),rs.getString("photo"),
                      rs.getDouble("prix"),rs.getInt("quantite"), C ,
                      rs.getInt("etat"));

               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("============current ITEM LIST ========="+list);
        return list;     
       
    }

 
    public Produit readById(int id_produit) {
    String requete = "SELECT * FROM `produit` WHERE `id`=?";
    Produit p = null;
    PreparedStatement pst;
    try {
        pst = conn.prepareStatement(requete);
        pst.setInt(1, id_produit);
        ResultSet rst = pst.executeQuery();
        ServiceCategorie SCT = new ServiceCategorie();
        if (rst.next()) {
            p = new Produit(rst.getInt(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getDouble(4),
                            rst.getInt(5),
                            new Categorie(SCT.readById(rst.getInt(7)).getId(),
                                          SCT.readById(rst.getInt(7)).getNom(),
                                          SCT.readById(rst.getInt(7)).getDescription()),
                            rst.getInt(6));
            System.out.println("E5deeeeeemmmmmm"+p);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
    }
    return p;
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
        u.setPrix(rs.getDouble("prix"));
         u.setQuantite(rs.getInt("quantite")); 
      u.setEtat(rs.getInt("etat"));
      
        
                listData.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }
     public void accepter(Produit produit)  throws UnsupportedEncodingException  {
		String requete = "UPDATE produit SET etat = 1  where id =  " + produit.getId();
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
                    // Send an email to the user
	        } catch (SQLException ex) {
	            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
     public void refuser(Produit produit)  throws UnsupportedEncodingException  {
		String requete = "UPDATE produit SET etat = '-1'  where id =  " + produit.getId();
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
                    // Send an email to the user
	        } catch (SQLException ex) {
	            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
     
     public void updatequantity(Produit p, int quantity) {
    try {
        // Create the SQL update statement
        String query = "UPDATE products SET quantite = ? WHERE id = ?";
        
        // Create a prepared statement
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        
        // Set the values of the parameters in the SQL statement
        preparedStatement.setInt(1, p.getQuantite()-quantity);
        preparedStatement.setInt(2, p.getId());
        
        // Execute the update statement
        preparedStatement.executeUpdate();
        
        System.out.println("Product quantity updated successfully!");
        
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
