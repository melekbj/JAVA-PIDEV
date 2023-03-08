/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author SAFAA
 */
public class Categorie {
   
    private int id_categorie;
    private String nom_categorie;
    private String description_categorie;
  

    public Categorie() {
    }

    public Categorie(int id_categorie,  String nom_categorie, String description_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.description_categorie = description_categorie;
       
    }
       public Categorie( String nom_categorie, String description_categorie) {
      
        this.nom_categorie = nom_categorie;
        this.description_categorie = description_categorie;
       
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getDescription_categorie() {
        return description_categorie;
    }

    public Categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setDescription_categorie(String description_categorie) {
        this.description_categorie = description_categorie;
    }

  
          @Override
    public String toString() {
        return "Catégorie{" + "id_catégorie=" + id_categorie + ", nom_catégorie=" + nom_categorie + ", description_catégorie=" + description_categorie + '}';
    }
    
       

   
    
}