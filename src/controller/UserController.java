/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connexion.ConnexionSource;
import java.util.List;
import entity.User;
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
             if (isValidEmail(t.getEmail())) {
             } else {
                 throw new IllegalArgumentException("Invalid email address.");
            }
            pst.setString(1, t.getEmail());
            
            if (!t.getRoles().equals("admin") && !t.getRoles().equals("partenaire")
                    && !t.getRoles().equals("client")) {
                throw new IllegalArgumentException("Invalid role: " + t.getRoles());
            } 
            
            pst.setString(2, t.getRoles());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getNom());
            pst.setString(5, t.getPrenom());
            pst.setInt(6, t.getAge());
            pst.setString(7, t.getAdresse());
            pst.setString(8, t.getImage());
            pst.setString(9, t.getGenre());
            pst.setString(10, t.getPhone());
            pst.setInt(11, t.getEtat());

            int rowsAdded = pst.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("User Added successfully.");
            } else {
                System.out.println("User failed to add.");
            }} catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }    }

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
        Scanner sc = new Scanner(System.in);
        try {
            String requete = "UPDATE user SET nom = ?, prenom = ?, age = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            System.out.println("enter nom");
            String nom = sc.nextLine();
            pst.setString(1, nom);
            
            System.out.println("enter prenom");
            String prenom = sc.nextLine();
            pst.setString(2,prenom);
            
            System.out.println("enter age");
            int age = sc.nextInt();
            pst.setInt(3,age );
            
            System.out.println("***ENTER ID OF USER YOU WANT TO UPDATE***");
            int id = sc.nextInt();
            pst.setInt(4,id );
            
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

    
}
