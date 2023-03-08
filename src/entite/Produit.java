/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author SAFAA
 */
public class Produit {
      private int id_produit;
    private String nom_produit;
   
    private String photo_produit;
    private float prix_produit;
    private int quantite_produit;
    private Categorie categorie;
    private String etat_produit;
    private  User user;
     
    
    
    
    
 public  Produit() {
    }

    public Produit(int id_produit, String nom_produit, String photo_produit, float prix_produit, int quantite_produit, Categorie categorie, String etat_produit, User user) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.photo_produit = photo_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.categorie = categorie;
        this.etat_produit = etat_produit;
        this.user = user;
    }

    public Produit(String nom_produit, String photo_produit, float prix_produit, int quantite_produit, Categorie categorie, String etat_produit,  User user) {
        this.nom_produit = nom_produit;
        this.photo_produit = photo_produit;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.categorie = categorie;
        this.etat_produit = etat_produit;
        this.user = user;
    }
    

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getPhoto_produit() {
        return photo_produit;
    }

    public void setPhoto_produit(String photo_produit) {
        this.photo_produit = photo_produit;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

  
    public String getEtat_produit() {
        return etat_produit;
    }

    public void setEtat_produit(String etat_produit) {
        this.etat_produit = etat_produit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    


  
 

   
          

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", photo_produit=" + photo_produit + ", prix_produit=" + prix_produit + ", quantite_produit=" + quantite_produit + ", categorie=" + categorie + ", etat_produit=" + etat_produit + ", user=" + user + '}';
    }
   
}