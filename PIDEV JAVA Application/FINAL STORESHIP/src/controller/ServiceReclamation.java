package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connexion.ConnexionSource;
import entity.Reclamation;
import entity.User;
import entity.Util.EmailService;
import java.io.UnsupportedEncodingException;



public class ServiceReclamation  {
    private Connection conn;

	public ServiceReclamation() {
        conn = ConnexionSource.getInstance().getCnx();
	}

	
	public void insert(Reclamation t) {
		 String requete = "INSERT INTO reclamation (client_id,commande_id,etat,date,description,produit_id,type_id) VALUES "
	                + "(" + t.getIdUser().getId() + "," + t.getIdCommande().getId() + ",'pending', '" + LocalDate.now()+ "','"+t.getContenu()+ "'," + t.getIdProduit().getId()+",'" +t.getType().getId()+"' )";
	        try {
	        	System.out.println(requete);
	            Statement st = conn.createStatement();
	            st.executeUpdate(requete);
	        } catch (SQLException ex) {
	            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
	        }
		
	}

	
	public void delete(int id) {
		String requete = "DELETE FROM Reclamation WHERE id = " + id;
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
                    System.out.println(requete);
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

	 
	public void update(Reclamation t) {
		// TODO Auto-generated method stub
		
	}
	public void accepter(Reclamation reclam)  throws UnsupportedEncodingException  {
             User us=reclam.getIdUser();
		String requete = "UPDATE reclamation SET etat = 'accepted'  where id =  " + reclam.getIdReclamation();
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
                    // Send an email to the user
                String recipientEmail = us.getEmail();
                String messageContent = "Dear"+" "+us.getNom()+" "+us.getPrenom()+"\n"+
                "your reclamation will be treated soon...";
        String subject = "Reclamation Accepted";
        EmailService emailService = new EmailService();
        emailService.sendEmail(recipientEmail, subject, messageContent); // Handle the exception appropriately
            
                    
	        } catch (SQLException ex) {
	            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	public void refuser(Reclamation reclam)throws UnsupportedEncodingException {
           
            User us=reclam.getIdUser();
		String requete = "UPDATE reclamation SET etat = 'refused'  where id =  " + reclam.getIdReclamation();
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
 String recipientEmail = us.getEmail();
                String messageContent = "Dear"+" "+us.getNom()+" "+us.getPrenom()+"\n"+
                "your reclamation was declined ";
        String subject = "Reclamation Declined";
        EmailService emailService = new EmailService();
        emailService.sendEmail(recipientEmail, subject, messageContent); // Handle the exception appropriately
            	            
	        } catch (SQLException ex) {
	            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

	 
	public List<Reclamation> readAll() {
		   List<Reclamation> list=new ArrayList<>();
           String requete="select * from reclamation";
       try {
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
               UserController uc=new UserController();
               Service_Commande sc=new Service_Commande();
               ServiceProduit sp=new ServiceProduit();
               ServiceTypeReclamation str=new ServiceTypeReclamation();
        	   Reclamation r=new Reclamation(
                           rs.getInt("id")
                           , uc.readById(rs.getInt("admin_id"))
                           , uc.readById(rs.getInt("client_id"))
                           ,sp.readById(rs.getInt("produit_id"))
                           ,sc.readById(rs.getInt("commande_id"))
                            ,rs.getString(8)
                           ,rs.getDate("date").toLocalDate()
                          ,rs.getString("etat")
                         ,str.readById(rs.getInt(9)));
                          
                          
               list.add(r);
              
           }
           System.out.println(list);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list; 
	}

	 
	public Reclamation readById(int id) {
		   Reclamation r1=new Reclamation();
           String requete="select * from reclamation WHERE id="+id;
       try {
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
        	UserController uc=new UserController();
               Service_Commande sc=new Service_Commande();
               ServiceProduit sp=new ServiceProduit();
               ServiceTypeReclamation str=new ServiceTypeReclamation();
        	   Reclamation r=new Reclamation(
                           rs.getInt("id")
                           , uc.readById(rs.getInt("admin_id"))
                           , uc.readById(rs.getInt("client_id"))
                           ,sp.readById(rs.getInt("produit_id"))
                           ,sc.readById(rs.getInt("commande_id"))
                            ,rs.getString(8)
                           ,rs.getDate("date").toLocalDate()
                          ,rs.getString("etat")
                         ,str.readById(rs.getInt(9)));
                          
                          
                     r1=r;
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
            System.out.println(r1);
       return r1; 
	}

	 
	public void delete(Reclamation t) {
		// TODO Auto-generated method stub
		
	}

   public List<Reclamation> readByUserId(int id) {
		   List<Reclamation> list=new ArrayList<>();
           String requete="select * from reclamation WHERE client_id="+id;
       try {
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
        	 UserController uc=new UserController();
               Service_Commande sc=new Service_Commande();
               ServiceProduit sp=new ServiceProduit();
               ServiceTypeReclamation str=new ServiceTypeReclamation();
        	   Reclamation r=new Reclamation(
                           rs.getInt("id")
                           , uc.readById(rs.getInt("admin_id"))
                           , uc.readById(rs.getInt("client_id"))
                           ,sp.readById(rs.getInt("produit_id"))
                           ,sc.readById(rs.getInt("commande_id"))
                            ,rs.getString(8)
                           ,rs.getDate("date").toLocalDate()
                          ,rs.getString("etat")
                         ,str.readById(rs.getInt(9)));
                          
                          
                list.add(r);
              
           }
           System.out.println(list);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list; 
	}
 
	public int reclamationPendingCount() {
		// TODO Auto-generated method stub
			int res=-1;
           String requete="select COUNT(*) AS total from reclamation WHERE etat='pending'";
       try {
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if(rs.next()){
           res= rs.getInt("total");
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       return res;
	}

}
