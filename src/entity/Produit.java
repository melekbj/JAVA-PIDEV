/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author SAFAA
 */
public class Produit {
      private int id;
    private String nom;
   
    private String photo;
    private float prix;
    private int quantite;
    private Categorie categorie;
    private String etat;
  //  private  User user;
     
    
    
    
    
 public  Produit() {
    }

    public Produit(int id, String nom, String photo, float prix, int quantite, Categorie categorie, String etat) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.etat = etat;
       // this.user = user;
    }

    public Produit(String nom, String photo, float prix, int quantite, Categorie categorie, String etat) {
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.etat = etat;
        //this.user = user;
    }

    public Produit(String nom, String photo, float prix, int quantite, String etat) {
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
        this.quantite = quantite;
        this.etat = etat;
    }

    public Produit(int id, String nom, String photo, float prix, int quantite, String etat) {
        this.id = id;
        this.nom = nom;
        this.photo = photo;
        this.prix = prix;
        this.quantite = quantite;
        this.etat = etat;
    }

    public Produit(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    

   

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", photo=" + photo + ", prix=" + prix + ", quantite=" + quantite + ", categorie=" + categorie + ", etat=" + etat + ", user=" +  '}';
    }
    

    


  
 

   
  
   
}
