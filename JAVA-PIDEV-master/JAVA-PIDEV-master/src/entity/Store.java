/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Plop
 */
public class Store {
    private int id;
    private String address;
    private String libelle;
    private User user;

    public Store() {
    }

    public Store(int id, String address, String libelle, User user) {
        this.id = id;
        this.address = address;
        this.libelle = libelle;
        this.user = user;
    }

    public void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Store(int id, String address, String libelle) {
        this.id = id;
        this.address = address;
        this.libelle = libelle;
    }

    public Store(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
