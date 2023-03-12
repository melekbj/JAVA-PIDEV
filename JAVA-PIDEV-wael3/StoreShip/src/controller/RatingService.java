/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Rating;
import connexion.ConnexionSource;
import java.sql.*;

/**
 *
 * @author Jmili
 */
public class RatingService {

  private Connection conn;

  public RatingService() {
        conn = ConnexionSource.getInstance().getCnx();
  }

  // Create a new rating in the table
  public void createRating(int storeId, double rating, int userId) throws SQLException {
    String sql = "INSERT INTO rating (store_id, rate, user_id) VALUES (?, ?, ?)";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, storeId);
    stmt.setDouble(2, rating);
    stmt.setInt(3, userId);
    stmt.executeUpdate();
  }

  // Read a rating from the table by ID
  public double readRating(int userid) throws SQLException {
    String sql = "SELECT rate FROM rating WHERE user_id = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, userid);
    ResultSet rs = stmt.executeQuery();
    double rating = -1; // Default to -1 if no rating is found
    if (rs.next()) {
      rating = rs.getDouble("Rate");
    }
    return rating;
  }

  // Update a rating in the table by userID
  public void updateRating(int storeid, double rating,int userid) throws SQLException {
    String sql = "UPDATE rating SET rate = ? WHERE store_id= ? and user_id=?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setDouble(1, rating);
    stmt.setInt(2, storeid);
    stmt.setInt(3, userid);
  int updatedrows=  stmt.executeUpdate();
  if (updatedrows==0){
      createRating( storeid,  rating, userid);
  }
  }

public float avgrating(int id_store) throws SQLException{
    String req = "SELECT AVG(rate) AS avg_rating FROM Rating WHERE store_id = ?";
    PreparedStatement stmt = conn.prepareStatement(req);
    stmt.setInt(1, id_store);
    ResultSet rs = stmt.executeQuery();
    float rating = -1; // Default to -1 if no rating is found
    if (rs.next()) {
      rating = rs.getFloat("avg_rating");
    }
    return rating;
}
}
