/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author Jmili
 */

public class Produit {
    private int id;
    private String nom;
    private String description;
    private Categorie categorie;
    private float prix;
    private int quantite;
    private String photo;
    private String etat;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Produit() {
    }
        
    public Produit(int id) {
        this.id = id;
    }
    public Produit(String nom, String description, Categorie categorie, float prix, int quantite, String etat) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
        this.etat = etat;
    }
        public Produit(String nom,Categorie categorie, float prix, int quantite, String etat) {
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
        this.etat = etat;
    }
    public Produit(String nom, String description, Categorie categorie, float prix, int quantite, String photo, String etat) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.etat = etat;
    }

    public Produit(int id, String nom, String description, Categorie categorie, float prix, int quantite, String photo, String etat) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "\n Produit{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", categorie=" + categorie + ", prix=" + prix + ", quantite=" + quantite + ", photo=" + photo + ", etat=" + etat + '}';
    }
   
}
