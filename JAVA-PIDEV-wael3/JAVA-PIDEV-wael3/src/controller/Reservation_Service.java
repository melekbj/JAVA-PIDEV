/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connexion.ConnexionSource;
import entity.Evenement_entite;
import entity.User;
import entity.Reservation_entite;
import entity.reservationEventUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author medio
 */
public class Reservation_Service implements Iservice<Reservation_entite> {
     private Connection conn;
     
     
     public Reservation_Service() {
        conn = ConnexionSource.getInstance().getCnx();
    }

    @Override
    public void insert(Reservation_entite t) {
        String req="INSERT INTO reservation (user_id,event_id,date,nb_places) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst=conn.prepareStatement(req);
            pst.setInt(1, t.getUser().getId());
            pst.setInt(2, t.getEv().getIdEvenement());
            pst.setDate(3, Date.valueOf(t.getDate()));
            pst.setInt(4, t.getNbr_place());
            pst.executeUpdate();
            System.out.println("reservation ajoute");
    } catch (SQLException ex) {
        Logger.getLogger(Reservation_Service.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("reservation non ajoute");
    }           
      
      
      
    }

    @Override
    public void delete(Reservation_entite t) {
         String query = "DELETE FROM reservation WHERE id_reservation = " + t.getId_reservation()+ "";
        try{
            Statement ste = conn.createStatement();
            ste.executeUpdate(query);
            System.out.println("Evenement Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Reservation_entite t) {
         String req="UPDATE reservation SET " 
                    + " id_user = '"+t.getUser()+"'"
                    + ",idEvenement='"+t.getEv()+"'"
                    + ",nbr_place="+t.getNbr_place()+" "
                    + " WHERE `id_reservation`='"+t.getId_reservation()+"'";
            try {
                PreparedStatement pst=conn.prepareStatement(req);
            //Statement st=conn.createStatement();
            pst.executeUpdate();
            System.out.println("reservation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void updatenbrplace(Reservation_entite t) {
         String req="UPDATE reservation SET " 
                    + "nbr_place="+t.getNbr_place()+""
                    + " WHERE `id_reservation`='"+t.getId_reservation()+"'";
            try {
                PreparedStatement pst=conn.prepareStatement(req);
            //Statement st=conn.createStatement();
            pst.executeUpdate();
            System.out.println("reservation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation_entite> readAll() {
            User user=new User();
         Evenement_entite ev=new Evenement_entite();
        
                List<Reservation_entite> list=new ArrayList<>();
            String requete="select * from reservation ";
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                 user.setId(rs.getInt(2));
                 ev.setIdEvenement(rs.getInt(3));
               Reservation_entite re=new Reservation_entite(
                      // rs.getInt("id_reservation"),
                        // rs.setId_user(rs.getInt("id_user")),
                      // rs.getInt("id_user"),
                    //  rs.getInt("id_evenement"),
                       // rs.getDate("date").toLocalDate(),
                       // rs.getInt("nbr_place"));
                        rs.getInt(1),user,ev,   rs.getDate("date").toLocalDate(),
                        rs.getInt("nbr_place"));
                
                      

                         
                 
   list.add(re);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reservation_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     
    }

    
    @Override
    public Reservation_entite readById(int id) {
        User user=new User();
         Evenement_entite ev=new Evenement_entite();
        Reservation_entite  re=new Reservation_entite();
        String condition = (" id_reservation ='"+id+"'");
        String requete="SELECT * FROM reservation WHERE " + condition+" LIMIT 1";
        try {
            PreparedStatement st=conn.prepareStatement(requete);
            ResultSet rs=st.executeQuery(requete);
          if(rs.next()){
              user.setId(rs.getInt(2)); /*Affectation des utilisateur ,  implementer ServiceUser readById*/
              ev.setIdEvenement(rs.getInt(3)); /* Affectattion des Evenement implementer ServiceEvenement ReadById*/
                 re=new Reservation_entite(
                         rs.getInt("id_reservation"),
                         user,
                         ev,
                         rs.getDate("date").toLocalDate(),
                        rs.getInt("nbr_place")

                         
                 );
            }
        } catch (SQLException e) {
            System.out.println("Error ReadByID Exception = \n"+e );
        }
        return re;
      
    }

   
    
  public reservationEventUser readByLastId(int id) {

        reservationEventUser re = new reservationEventUser();
       //get reservation by idUser and idEvent order decroissant limit 1
        String condition = (" r.user_id ='" + id + "'");
        String requete = "SELECT * FROM reservation r,evenement e,user u WHERE r.user_id=u.id and r.event_id=e.id and " + condition + " ORDER BY r.id DESC LIMIT 1";

        try {
            PreparedStatement st = conn.prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                re = new reservationEventUser(
                        rs.getInt("id"),
                        rs.getString("titre_ev"),
                        rs.getDate("date_debut").toLocalDate(),
                        rs.getString("lieu_ev"),
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("nb_places"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error ReadByID Exception = \n" + e);
        }
        return re;

    }   
    
  
}

