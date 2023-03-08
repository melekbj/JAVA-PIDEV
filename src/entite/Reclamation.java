package entite;

import java.time.LocalDate;

public class Reclamation {
private int IdReclamation;
private int IdAdmin;
private int IdUser;
private int IdProduit;
private int IdCommande;
private String Contenu;
private LocalDate Date;
private String Etat;
private String Type;

public Reclamation(int idReclamation, int idUser, int idCommande,String etat,LocalDate date,String contenu,int idProduit1,int idAdmin,String type) {
	super();
	IdReclamation = idReclamation;
	IdAdmin = idAdmin;
	IdUser = idUser;
	IdProduit = idProduit1;
	Contenu = contenu;
	Date = date;
	Etat = etat;
	IdCommande=idCommande;
	Type=type;
	
	
}
public Reclamation(int idUser, int idCommande,String etat,LocalDate date,String contenu,int idProduit,int idAdmin,String type) {
	super();
	IdAdmin = idAdmin;
	IdUser = idUser;
	IdProduit = idProduit;
	Contenu = contenu;
	Date = date;
	Etat = etat;
	IdCommande=idCommande;
	Type=type;
}

    public Reclamation() {
    }
public int getIdCommande() {
	return IdCommande;
}
public void setIdCommande(int idCommande) {
	IdCommande = idCommande;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public int getIdReclamation() {
	return IdReclamation;
}
@Override
public String toString() {
	return "Reclamation [IdReclamation=" + IdReclamation + ", IdAdmin=" + IdAdmin + ", IdUser=" + IdUser
			+ ", IdProduit=" + IdProduit + ", Contenu=" + Contenu + ", Date=" + Date + ", Etat=" + Etat + "]";
}
public void setIdReclamation(int idReclamation) {
	IdReclamation = idReclamation;
}
public int getIdAdmin() {
	return IdAdmin;
}
public void setIdAdmin(int idAdmin) {
	IdAdmin = idAdmin;
}
public int getIdUser() {
	return IdUser;
}
public void setIdUser(int idUser) {
	IdUser = idUser;
}
public int getIdProduit() {
	return IdProduit;
}
public void setIdProduit(int idProduit) {
	IdProduit = idProduit;
}
public String getContenu() {
	return Contenu;
}
public void setContenu(String contenu) {
	Contenu = contenu;
}
public LocalDate getDate() {
	return Date;
}
public void setDate(LocalDate date) {
	Date = date;
}
public String getEtat() {
	return Etat;
}
public void setEtat(String etat) {
	Etat = etat;
}
	
}
