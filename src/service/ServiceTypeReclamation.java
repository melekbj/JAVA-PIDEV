package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entite.Reclamation;
import entite.TypeReclamation;
import utils.DataSource;

public class ServiceTypeReclamation implements IService<TypeReclamation> {
    private Connection conn;

	public ServiceTypeReclamation() {
		 conn = DataSource.getInstance().getCnx();
	}

	@Override
	public void insert(TypeReclamation t) {
		String requete = "INSERT INTO typeReclamation (id,typeName) VALUES "
                + "(" + t.getId()+ ",'"+t.getTypeName()+"')";
        try {
        	System.out.println(requete);
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

	@Override
	public void delete(TypeReclamation t) {
		String requete = "DELETE FROM typeReclamation WHERE id = " + t.getId();
		 try {
	            Statement st=conn.createStatement();
	            st.executeUpdate(requete);
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
	        }
		
	}

	@Override
	public void update(TypeReclamation t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TypeReclamation> readAll() {
		List<TypeReclamation> list=new ArrayList<>();
        String requete="select * from typeReclamation";
    try {
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(requete);
        while(rs.next()){
     	   TypeReclamation r=new TypeReclamation(rs.getInt("id"),rs.getString("typeName"));
            list.add(r);
           
        }
        System.out.println(list);
        
    } catch (SQLException ex) {
        Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list; 
	}

	@Override
	public TypeReclamation readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
