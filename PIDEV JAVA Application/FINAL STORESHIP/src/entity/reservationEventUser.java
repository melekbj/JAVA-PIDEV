/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class reservationEventUser {
    private int id;
    private String titleEvent;
    private LocalDate dateEvent; 
    private String locationEvent;
    private LocalDate dateReservation;  
    private int nbrPlace;
    private String nomUser;
    private String prenomUser;
    private String imgQr;
    

    public reservationEventUser() {
    }

    public reservationEventUser(int id, String titleEvent, LocalDate dateEvent, String locationEvent, LocalDate dateReservation, int nbrPlace, String nomUser, String prenomUser) {
        this.id = id;
        this.titleEvent = titleEvent;
        this.dateEvent = dateEvent;
        this.locationEvent = locationEvent;
        this.dateReservation = dateReservation;
        this.nbrPlace = nbrPlace;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
    }

    public reservationEventUser(int id, String titleEvent, LocalDate dateEvent, String locationEvent, LocalDate dateReservation, int nbrPlace, String nomUser, String prenomUser, String imgQr) {
        this.id = id;
        this.titleEvent = titleEvent;
        this.dateEvent = dateEvent;
        this.locationEvent = locationEvent;
        this.dateReservation = dateReservation;
        this.nbrPlace = nbrPlace;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.imgQr = imgQr;
    }

    public String getImgQr() {
        return imgQr;
    }

    public void setImgQr(String imgQr) {
        this.imgQr = imgQr;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleEvent() {
        return titleEvent;
    }

    public void setTitleEvent(String titleEvent) {
        this.titleEvent = titleEvent;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getLocationEvent() {
        return locationEvent;
    }

    public void setLocationEvent(String locationEvent) {
        this.locationEvent = locationEvent;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    @Override
    public String toString() {
        return "reservationEventUser{" + "id=" + id + ", titleEvent=" + titleEvent + ", dateEvent=" + dateEvent + ", locationEvent=" + locationEvent + ", dateReservation=" + dateReservation + ", nbrPlace=" + nbrPlace + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + '}';
    }

}
