package Service;

import Entities.Store;
import Util.DataSource;
import Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreService implements IService<Store>{
    private Connection conn;

    public StoreService() {
        conn = DataSource.getInstance().getCnx();
    }
    @Override
    public void insert(Store str) {
        String requete = "INSERT INTO `store`(`location`,`nom` )"
                + " VALUES ('"+ str.getLocation() + "','"+ str.getNameSt() +"')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
            System.out.println("insert succesful!");
        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
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

    @Override
    public void update(Store store) {
        String requete = "UPDATE Store SET libelle = ? ,adresse = ? ,id_user = ? WHERE id= "+ store.getId();
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1,store.getNameSt() );
            pst.setString(2,store.getLocation() );
            pst.setInt(3, store.getOwner().getId());
            pst.executeUpdate();
            System.out.println("Store updated");
        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Store> readAll() {
        List<Store> listSt=new ArrayList<>();
        String requete="select * from Store";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                User u=new User(rs.getInt("id_user"));
               // Service_User us=new Service_User();
                //u=us.readbyID(u.getId());
                Store p=new Store(rs.getString("libelle"), rs.getString("adresse"),
                      u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSt;    }

    @Override
    public Store readById(int id) {
        Store s=new Store();
        String requete="SELECT * FROM Store WHERE " + id;
        try {
            PreparedStatement st=conn.prepareStatement(requete);
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                User u=new User(rs.getInt("user_id"));
               // Service_User us=new Service_User();
                //u=us.readbyID(u.getId());
                Store str=new Store(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("location"),u);
        s.setLocation(str.getLocation());
        s.setNameSt(str.getNameSt());
        s.setOwner(str.getOwner());
        s.setId(str.getId());
            }

        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
return s;    
    }
}

