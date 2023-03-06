/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Categorie;
import entity.Commande;
import entity.Produit;
import entity.User;
//import tile.DataSource;
import connexion.ConnexionSource;
import entity.Produit;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Plop
 */
public class ServiceProduit  {
  private Connection connection; 
    /* Pour faire une seule instance de notre CONNECTION a la BD*/
    public ServiceProduit(){
        connection=ConnexionSource.getInstance().getCnx();
    }
    public void insert(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void delete(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void update(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<Produit> readAll() {
             List<Produit> list_produit=new ArrayList<>();
        String requete="select * from produit";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Produit produit=new Produit(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("nom"),new Categorie(rs.getInt("categorie_id")),
                        rs.getFloat("prix"),rs.getInt("quantite"),
                        "",rs.getInt("etat"));
                list_produit.add(produit);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_produit;
    }

    
    public Produit readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
   
}
