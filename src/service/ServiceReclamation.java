package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entite.Personne;
import entite.Reclamation;
import utils.DataSource;

public class ServiceReclamation implements IService<Reclamation> {
    private Connection conn;

	public ServiceReclamation() {
        conn = DataSource.getInstance().getCnx();
	}

	@Override
	public void insert(Reclamation t) {
		 String requete = "INSERT INTO reclamation (id_client,id_comm,etat,date,description,id_produit,id_admin,type) VALUES "
	                + "(" + t.getIdUser() + "," + t.getIdCommande() + ",'pending', '" + LocalDate.now()+ "','"+t.getContenu()+ "'," + t.getIdProduit()+"," + t.getIdAdmin()+ ",'"+t.getType()+"' )";
	        try {
	        	System.out.println(requete);
	            Statement st = conn.createStatement();
	            st.executeUpdate(requete);
	        } catch (SQLException ex) {
	            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
	        }
		
	}

	
	public void delete(int id) {
		String requete = "DELETE FROM Reclamation WHERE id = " + id;
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
                    System.out.println(requete);
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

	@Override
	public void update(Reclamation t) {
		// TODO Auto-generated method stub
		
	}
	public void accepter(int id) {
		String requete = "UPDATE reclamation SET etat = 'accepted' where id =  " + id;
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
	            System.out.println(requete);
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	public void refuser(int id) {
		String requete = "UPDATE reclamation SET etat = 'refused' where id =  " + id;
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
                    System.out.println(requete);
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

	@Override
	public List<Reclamation> readAll() {
		   List<Reclamation> list=new ArrayList<>();
           String requete="select * from reclamation";
       try {
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
        	   Reclamation r=new Reclamation(rs.getInt("id"), rs.getInt("id_client"),rs.getInt("id_comm"),rs.getString("etat"),rs.getDate("date").toLocalDate(),
        			   rs.getString("description"), rs.getInt("id_produit"),rs.getInt("id_admin"),rs.getString("type"));
               list.add(r);
              
           }
           System.out.println(list);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
       }
       return list; 
	}

	@Override
	public Reclamation readById(int id) {
		   Reclamation r1=new Reclamation();
           String requete="select * from reclamation WHERE id="+id;
       try {
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
        	   Reclamation r=new Reclamation(rs.getInt("id"), rs.getInt("id_client"),rs.getInt("id_comm"),rs.getString("etat"),rs.getDate("date").toLocalDate(),
        			   rs.getString("description"), rs.getInt("id_produit"),rs.getInt("id_admin"),rs.getString("type"));
              r1=r;
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
       }
            System.out.println(r1);
       return r1; 
	}

	@Override
	public void delete(Reclamation t) {
		// TODO Auto-generated method stub
		
	}

   public List<Reclamation> readByUserId(int id) {
		   List<Reclamation> list=new ArrayList<>();
           String requete="select * from reclamation WHERE id_client="+id;
       try {
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery(requete);
           while(rs.next()){
        	   Reclamation r=new Reclamation(rs.getInt("id"), rs.getInt("id_client"),rs.getInt("id_comm"),rs.getString("etat"),rs.getDate("date").toLocalDate(),
        			   rs.getString("description"), rs.getInt("id_produit"),rs.getInt("id_admin"),rs.getString("type"));
               list.add(r);
              
           }
           System.out.println(list);
           
       } catch (SQLException ex) {
           Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
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
           Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       return res;
	}

}
