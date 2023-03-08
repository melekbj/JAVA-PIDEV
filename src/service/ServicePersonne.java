/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Personne;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author wiemhjiri
 */
public class ServicePersonne implements IService<Personne> {

    private Connection conn;

    public ServicePersonne() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(Personne t) {
        String requete = "insert into personne (nom,prenom,age) values "
                + "('" + t.getNom() + "','" + t.getPrenom() + "'," + t.getAge() + ")";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertPst(Personne p) {
        String requete = "insert into personne(nom,prenom,age) values(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setInt(3, p.getAge());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Personne> readAll() {
            List<Personne> list=new ArrayList<>();
            String requete="select * from perssonne";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                System.out.println(rs.getInt("id"));
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;          
    }

    @Override
    public Personne readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
