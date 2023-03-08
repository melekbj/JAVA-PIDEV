package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connexion.ConnexionSource;
import entity.Commande;
import entity.Detail_Commande;
import entity.Produit;
import entity.Store;
import entity.TypeReclamation;
import java.sql.PreparedStatement;


public class ServiceTypeReclamation  {
    private Connection conn;

	public ServiceTypeReclamation() {
		 conn = ConnexionSource.getInstance().getCnx();
	}

	 
	public void insert(TypeReclamation t) {
		String requete = "INSERT INTO type_reclamation (nom) VALUES ('"+t.getTypeName()+"')";
        try {
        	System.out.println(requete);
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	 
	public void delete(TypeReclamation t) {
		String requete = "DELETE FROM type_reclamation WHERE id = " + t.getId();
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(ServiceTypeReclamation.class.getName()).log(Level.SEVERE, null, ex);
	        }
		
	}

	 
	public void update(TypeReclamation t) {
		// TODO Auto-generated method stub
		
	}

	 
	public List<TypeReclamation> readAll() {
		List<TypeReclamation> list=new ArrayList<>();
        String requete="select * from type_reclamation";
    try {
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(requete);
        while(rs.next()){
     	   TypeReclamation r=new TypeReclamation(rs.getInt("id"),rs.getString("nom"));
            list.add(r);
           
        }
        System.out.println(list);
        
    } catch (SQLException ex) {
        Logger.getLogger(ServiceTypeReclamation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list; 
	}

	 
	public TypeReclamation readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

        public TypeReclamation readByName(String typename) {
            System.out.println("error is here");
                TypeReclamation typre=new TypeReclamation();
        String requete="select * from type_reclamation WHERE  "
                + "nom='"+typename +"'";
              
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){ /*a revisiter le concept d'integeration ici*/
                /*integre Rayen       readByid(Store) ServiceStore
                ServiceStore ss=new ServiceStore();
                Store store=ss.readById(rs.getInt(3));
                
                */
                /*integre Safa       readByid(produit) Serviceproduit
                ServiceProduit sp=new ServiceProduit();
                Produit produit=sp.readById(rs.getInt(4));
                
                */
                System.out.println("adding a Detail_Commande to ListDetail "+rs.getString(1));
              
                 typre=new TypeReclamation(rs.getInt(1),rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return typre;
        
	}
        
        
        public List<TypeReclamation> getstatstype()  {
              try {
    String sql = "SELECT T.nom, COUNT(*) AS count " +
                 "FROM type_reclamation T " +
                 "INNER JOIN Reclamation R ON T.id = R.type_id " +
                 "GROUP BY T.nom";
            PreparedStatement statement = conn.prepareStatement(sql);
    ResultSet result = statement.executeQuery();
    List<TypeReclamation> types = new ArrayList<>();
      
            while (result.next()) {
                String typeName = result.getString("nom");
                int count = result.getInt("count");
                TypeReclamation type = new TypeReclamation(count, typeName);
                types.add(type);
            } 
            return types;
              } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    return null;
}






	
}
