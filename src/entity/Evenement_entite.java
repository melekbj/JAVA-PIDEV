/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

  //import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author medio
 */
public class Evenement_entite {

    private int idEvenement;
    private LocalDate date_debutEvenement;
    private LocalDate date_finEvenement;
    private String imageEvenement; 
    private String lieuEvenement;
    private String titreEvenement;
    private String descriptionEvenement;
    private int nbMax_place;
  
   

    public Evenement_entite() {
    }

    public Evenement_entite(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Evenement_entite(int idEvenement, LocalDate date_debutEvenement, LocalDate date_finEvenement, String imageEvenement, String lieuEvenement, String titreEvenement, String descriptionEvenement, int nbMax_place) {
        this.idEvenement = idEvenement;
        this.date_debutEvenement = date_debutEvenement;
        this.date_finEvenement = date_finEvenement;
        this.imageEvenement = imageEvenement;
        this.lieuEvenement = lieuEvenement;
        this.titreEvenement = titreEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.nbMax_place = nbMax_place;
    }

   

    

    

    //public Evenement_entite(int idEvenement) {
      //  this.idEvenement = idEvenement;
    //}

    public Evenement_entite(LocalDate date_debutEvenement, LocalDate date_finEvenement, String imageEvenement, String lieuEvenement, String titreEvenement, String descriptionEvenement, int nbMax_place) {
        this.date_debutEvenement = date_debutEvenement;
        this.date_finEvenement = date_finEvenement;
        this.imageEvenement = imageEvenement;
        this.lieuEvenement = lieuEvenement;
        this.titreEvenement = titreEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.nbMax_place = nbMax_place;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public LocalDate getDate_debutEvenement() {
        return date_debutEvenement;
    }

    public void setDate_debutEvenement(LocalDate date_debutEvenement) {
        this.date_debutEvenement = date_debutEvenement;
    }

    public LocalDate getDate_finEvenement() {
        return date_finEvenement;
    }

    public void setDate_finEvenement(LocalDate date_finEvenement) {
        this.date_finEvenement = date_finEvenement;
    }

    public String getImageEvenement() {
        return imageEvenement;
    }

    public void setImageEvenement(String imageEvenement) {
        this.imageEvenement = imageEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public String getTitreEvenement() {
        return titreEvenement;
    }

    public void setTitreEvenement(String titreEvenement) {
        this.titreEvenement = titreEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public int getNbMax_place() {
        return nbMax_place;
    }

    public void setNbMax_place(int nbMax_place) {
        this.nbMax_place = nbMax_place;
    }

    @Override
    public String toString() {
        return "Evenement_entite{" + "idEvenement=" + idEvenement + ", date_debutEvenement=" + date_debutEvenement + ", date_finEvenement=" + date_finEvenement + ", imageEvenement=" + imageEvenement + ", lieuEvenement=" + lieuEvenement + ", titreEvenement=" + titreEvenement + ", descriptionEvenement=" + descriptionEvenement + ", nbMax_place=" + nbMax_place + '}';
    }

    
  
    
    
    
}

