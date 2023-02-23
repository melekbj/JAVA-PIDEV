/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Jmili
 */
public class Store {
    private int id ;
    private String nameSt;
    private String location;
    private User owner;

    public Store() {
    }
    public Store(int id,String nameSt, String location,User owner) {
        this.id=id;
        this.nameSt = nameSt;
        this.location = location;
        this.owner = owner;
    }
    public Store(String nameSt, String location,User owner) {
        this.nameSt = nameSt;
        this.location = location;
        this.owner = owner;
    }
        public Store(String nameSt, String location) {
        this.nameSt = nameSt;
        this.location = location;
    }
    
        public Store(int id) {
        this.id=id;
    }

    public String getLocation() {
        return location;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setNameSt(String nameSt) {
        this.nameSt = nameSt;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getOwner() {
        return owner;
    }

    public String getNameSt() {
        return nameSt;
    }
    public void setId(int id) {
        this.id = id;
}
    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.nameSt);
        hash = 71 * hash + Objects.hashCode(this.location);
        hash = 71 * hash + Objects.hashCode(this.owner);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Store other = (Store) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nameSt, other.nameSt)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return Objects.equals(this.owner, other.owner);
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", nameSt=" + nameSt + ", location=" + location + ", owner=" + owner + '}';
    }   
}
