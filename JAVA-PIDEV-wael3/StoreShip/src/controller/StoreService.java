package controller;

import entity.Store;
import connexion.ConnexionSource;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreService{
    private Connection conn;

    // Constructor to establish a connection to the database
    public StoreService() {
        conn = ConnexionSource.getInstance().getCnx();
    }

    // Insert a new store into the database
 
    public void insert(Store str) {
        String requete = "INSERT INTO `store`(`location`,`nom`,`photo`,`user_id` )"
                + " VALUES ('"+ str.getLocation() + "','"+ str.getNameSt() + "','"+str.getPhoto()+"','"+str.getOwner().getId()+"')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("insert succesful!");
        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Delete a store from the database
 
    public void delete(Store store) {
        String requete = "DELETE FROM `store` WHERE `Store`.`id` = "+store.getId();
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println(store + "has been deleted");
        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Update an existing store in the database
 
    public void update(Store store) {
        String requete = "UPDATE Store SET nom = ? ,location = ? ,user_id = ? ,photo=? WHERE id= "+ store.getId();
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1,store.getNameSt() );
            pst.setString(2,store.getLocation() );
            pst.setInt(3, store.getOwner().getId());
            pst.setString(4, store.getPhoto());
            pst.executeUpdate();
            System.out.println("Store updated");
        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Retrieve a list of all stores in the database
 
    public List<Store> readAll() {
        List<Store> listSt=new ArrayList<>();
        String requete="select * from Store";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                UserController uc=new UserController();
                User u=uc.readById(rs.getInt("id_user"));
               // Service_User us=new Service_User();
                //u=us.readbyID(u.getId());
                Store p=new Store(rs.getString("nom"), rs.getString("location"),
                      u,rs.getString("photo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSt;    
    }

// Retrieve a store by User ID from the database
  
public Store readById(int id) {
    String query = "SELECT * FROM store WHERE user_id=?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Retrieve the user object from the database
            UserController us = new UserController();
            User user = us.readById(id);
             RatingService rate=new RatingService();
            Store store = new Store(rs.getInt(1),rs.getString("nom"), rs.getString("location"),
                  user, rs.getString("photo"),rate.avgrating(rs.getInt(1)));
            return store;
        }
    } catch (SQLException ex) {
        Logger.getLogger(controller.StoreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}


public Store readByStoreId(int id) {
    String query = "SELECT * FROM store WHERE id=?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Retrieve the user object from the database
            UserController us = new UserController();
            User user = us.readById(id);
             RatingService rate=new RatingService();
            Store store = new Store(rs.getInt(1),rs.getString("nom"), rs.getString("location"),
                  user, rs.getString("photo"),rate.avgrating(rs.getInt(1)));
            return store;
        }
    } catch (SQLException ex) {
        Logger.getLogger(controller.StoreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}
}
