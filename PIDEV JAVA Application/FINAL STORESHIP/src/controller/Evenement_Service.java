/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Evenement_entite;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
//import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import connexion.ConnexionSource;
import controller.Iservice;
import entity.Reservation_entite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author medio
 */
public class Evenement_Service implements Iservice<Evenement_entite> {

    private Connection cnx;

    public Evenement_Service() {
        cnx = ConnexionSource.getInstance().getCnx();
    }

    
    @Override
    public void insert(Evenement_entite t) {

        String req = "INSERT INTO evenement (date_debut,date_fin,image_ev,lieu_ev,titre_ev,desc_ev,nb_max) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setDate(1, Date.valueOf(t.getDate_debutEvenement()));
            pst.setDate(2, Date.valueOf(t.getDate_finEvenement()));
            pst.setString(3, t.getImageEvenement());
            pst.setString(4, t.getLieuEvenement());
            pst.setString(5, t.getTitreEvenement());
            pst.setString(6, t.getDescriptionEvenement());
            pst.setInt(7, t.getNbMax_place());
            pst.executeUpdate();
            System.out.println("Evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

   
    @Override
    public void delete(Evenement_entite t) {
        String query = "DELETE FROM evenement WHERE id = " + t.getIdEvenement() + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Evenement Deleted Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Evenement_entite f) {
        //  pst.setDate(3, Date.valueOf( t.getDate_naiss()));
        //  + ",`date_debutEvenement`='"+f.getValueOf(Date_debutEvenement())+"'"
        String req = "UPDATE evenement SET "
                + "`date_debut`='" + Date.valueOf(f.getDate_debutEvenement()) + "'"
                + ",`date_fin`='" + Date.valueOf(f.getDate_finEvenement()) + "'"
                + " ,image_ev= '" + f.getImageEvenement() + "'"
                + ",lieu_ev='" + f.getLieuEvenement() + "'"
                + ",titre_ev='" + f.getTitreEvenement() + "'"
                + ",desc_ev='" + f.getDescriptionEvenement() + "'"
                + ",nb_max='" + f.getNbMax_place() + "'"
                + " WHERE `id`='" + f.getIdEvenement() + "'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            //Statement st=conn.createStatement();
            pst.executeUpdate();
            System.out.println("Evenement modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement_entite> readAll() {
        List<Evenement_entite> list = new ArrayList<>();
        String requete = "select * from evenement";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Evenement_entite ee = new Evenement_entite(
                        rs.getInt("id"),
                        rs.getDate("date_debut").toLocalDate(),
                        rs.getDate("date_fin").toLocalDate(),
                        rs.getString("image_ev"),
                        rs.getString("lieu_ev"),
                        rs.getString("titre_ev"),
                        rs.getString("desc_ev"),
                        rs.getInt("nb_max"));

                list.add(ee);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Evenement_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Evenement_entite readById(int id) {
        Evenement_entite ee = new Evenement_entite();
        String condition = (" id ='" + id + "'");
        String requete = "SELECT * FROM evenement WHERE " + condition + " LIMIT 1";
        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                ee = new Evenement_entite(
                        rs.getInt("id"),
                        rs.getDate("date_debut").toLocalDate(),
                        rs.getDate("date_fin").toLocalDate(),
                        rs.getString("image_ev"),
                        rs.getString("lieu_ev"),
                        rs.getString("titre_ev"),
                        rs.getString("desc_ev"),
                        rs.getInt("nb_max")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error ReadByID Exception = \n" + e);
        }
        return ee;

    }

    //get evenement by id
    public Evenement_entite getEvenementById(int id) {
        Evenement_entite ee = new Evenement_entite();
        String condition = (" id ='" + id + "'");
        String requete = "SELECT * FROM evenement WHERE " + condition + " LIMIT 1";
        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                ee = new Evenement_entite(
                        rs.getInt("id"),
                        rs.getDate("date_debut").toLocalDate(),
                        rs.getDate("date_fin").toLocalDate(),
                        rs.getString("image_ev"),
                        rs.getString("lieu_ev"),
                        rs.getString("titre_ev"),
                        rs.getString("desc_ev"),
                        rs.getInt("nb_max")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error ReadByID Exception = \n" + e);
        }
        return ee;

    }
    
     public void updatePlaces(Reservation_entite t) {
         int newMax = t.getEv().getNbMax_place()-t.getNbr_place();
    String req = "UPDATE evenement SET "
            + "nb_places='" + newMax + "'"
            + " WHERE `event_id`='" + t.getEv().getIdEvenement() + "'";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate();
        System.out.println("Places updated successfully");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
     }
    
    

}
