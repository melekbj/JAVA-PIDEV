/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connexion.ConnexionSource;
import entity.Util.EmailService;
import java.util.List;
import entity.User;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserController implements UController<User> {
    
    private Connection cnx;

    public UserController() {
        cnx= ConnexionSource.getInstance().getCnx();}
    
    public static boolean isValidEmail(String email) {
       String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
       return email.matches(regex);
    }
  
    
    @Override
   public void AddUser(User t) {
    String requete = "insert into user(email,roles,password,nom,prenom,age,adresse,image,genre,phone,etat) values(?,?,?,?,?,?,?,?,?,?,?)";
    try {
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, t.getEmail());
        pst.setString(2, t.getRoles());
        pst.setString(3, t.getPassword());
        pst.setString(4, t.getNom());
        pst.setString(5, t.getPrenom());
        pst.setInt(6, t.getAge());
        pst.setString(7, t.getAdresse());
        pst.setString(8, t.getImage());
        pst.setString(9, t.getGenre());
        pst.setString(10, t.getPhone());
        if (t.getRoles().equals("Partenaire")) {
            pst.setInt(11, -1); // set etat to -1 for partner users
        } else {
            pst.setInt(11, 0); // set etat to 0 for other users
        }
        int rowsAdded = pst.executeUpdate();
        if (rowsAdded > 0) {
            System.out.println("User Added successfully.");
        } else {
            System.out.println("User failed to add.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    @Override
    public void DeleteUser(User t) {
        Scanner sc = new Scanner(System.in);
        try {
            String requete = "DELETE FROM user WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            System.out.println("***ENTER ID OF THE USER YOU WANT TO DELETE***");
            int id = sc.nextInt();
            pst.setInt(1, id);
            
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            } } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void UpdateUser(User t) {
//        Scanner sc = new Scanner(System.in);
        try {
            String requete = "UPDATE user SET nom = ?, prenom = ?, age = ?, adresse=? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
//            System.out.println("enter nom");
//            String nom = sc.nextLine();
            pst.setString(1, t.getNom());
            
//            System.out.println("enter prenom");
//            String prenom = sc.nextLine();
            pst.setString(2,t.getPrenom());
            
//            System.out.println("enter age");
//            int age = sc.nextInt();
            pst.setInt(3,t.getAge() );
            
//            System.out.println("***ENTER ID OF USER YOU WANT TO UPDATE***");
//            int id = sc.nextInt();
            pst.setString(4,t.getAdresse() );
            
             pst.setInt(5,t.getId() );
            
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User failed to update.");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public List<User> ReadAllUsers() {
        List<User> list=new ArrayList<>();
                String requete="select * from user";
            try {
                Statement st=cnx.createStatement();
                ResultSet rs=st.executeQuery(requete);
                
                while(rs.next()){
                    User u =new User(rs.getInt("id"), rs.getString("email"),rs.getString("roles"), 
             rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),
                 rs.getString("adresse"),rs.getString("image"),
                   rs.getString("genre"),rs.getString("phone"),rs.getInt("etat"));
                    list.add(u);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;      
    }

    @Override
    public User readById(int id) {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
          try {
            String sql = "SELECT * FROM user WHERE id = ?";
            stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("roles"),
                    rs.getString("password"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getInt("age"),
                    rs.getString("adresse"),
                    rs.getString("image"),
                    rs.getString("genre"),
                    rs.getString("phone"),
                    rs.getInt("etat")
                );
            } else {
                return null; // user not found
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    
                }
            }
            
        }

        return null;

    }
    

       public List<User> ReadAllUsersByPendingState() {
        List<User> list=new ArrayList<>();
                String requete="select * from user where etat = -1";
            try {
                Statement st=cnx.createStatement();
                ResultSet rs=st.executeQuery(requete);
                
                while(rs.next()){
                    User u =new User(rs.getInt("id"), rs.getString("email"),rs.getString("roles"), 
             rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),
                 rs.getString("adresse"),rs.getString("image"),
                   rs.getString("genre"),rs.getString("phone"),rs.getInt("etat"));
                    list.add(u);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;      
    }
    
    public void approveUser(User user) throws UnsupportedEncodingException {
        String requete = "UPDATE user SET etat = 0 where id = ? " ;
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.executeUpdate();
            System.out.println("Etat modifié avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
     public void blockUser(User user) throws UnsupportedEncodingException {
        String requete = "UPDATE user SET etat = 1 where id = ? " ;
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.executeUpdate();
            System.out.println("user blocké");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
    
    
    
    

}
        
        
        
        

        
        
        
        
        
        
        
        
        
        
      
















        

//    public User readUserById(int id) throws SQLException {
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        User user = null;
//
//        try {
//            // prepare the SQL statement to read a user by ID
//            String sql = "SELECT * FROM users WHERE id = ?";
//            stmt = conn.prepareStatement(sql);
//
//            // set the ID parameter in the SQL statement
//            stmt.setInt(1, id);
//
//            // execute the SQL statement and get the result set
//            rs = stmt.executeQuery();
//
//            // check if the result set has a user and create a User object with the data
//            if (rs.next()) {
//                user = new User();
//                user.setId(rs.getInt("id"));
//                user.setName(rs.getString("name"));
//                user.setEmail(rs.getString("email"));
//                // set other properties as needed
//            }
//        } finally {
//            // close the result set, statement, and connection
//            if (rs != null) rs.close();
//            if (stmt != null) stmt.close();
//        }
//
//        return user;
//    }