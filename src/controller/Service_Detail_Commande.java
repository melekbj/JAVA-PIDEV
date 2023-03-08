/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connexion.ConnexionSource;
import entity.Commande;
import entity.Detail_Commande;
import entity.Produit;
import entity.Store;
import entity.User;
import entity.Util.State;
import entity.Util.TunisieMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Plop
 */
public class Service_Detail_Commande {
  private Connection connection; 
    /* Pour faire une seule instance de notre CONNECTION a la BD*/
  
  
    public Service_Detail_Commande(){
        connection=ConnexionSource.getInstance().getCnx();
    }
    /*
    Implemented methodes 
    insert (    insertion basique never to be used )
    insertDetail_commande(detail_commande)   insert detail_commande
    update    NEVER TO BE USED StANDLONE not implemented
    updateEtat( detail_commande ) faire la miseajour sur l'etat seuelement
    updateDetail_commande_store(detail_commande)  affecte un detail_commande a un store specifique
                        NB: ca se fait aprés un calcul précise qui donne le store
                            qui a bien le produit a delivre pour le client depend de commande
    readAll()   select * from detail_command
    readById()  read by id de detail commande
    readByStoreId(Store)  select * from detail_commande where store=store
                            NB:  tout les commandes affecter au store params
    readByStoreIdByEtat(store,etat)    select * from detail_commande where store=store and etat=etat
                            NB:  tout les commandes affecter au store params 
                            NB: en considerant letat mentionnée en params
    getIdFromDatabase fait le calcul de mentionnée un store specifique qui delivre le produit
                            mentionnée dans detail_commande toutes en considerant la map de tunis
    */
    
    public void insert(Detail_Commande detail_commande) {
                        System.out.println("this is current Detail and what they require fuck u "+detail_commande);

              String requete = "insert into detail_commande (commande_id,store_id,produit_id,quantite,prix_total,etat) values "
                + "(" +
                detail_commande.getCommande().getId()+ "," +
                detail_commande.getStore().getId()+ "," +
                detail_commande.getProduit().getId() + ","+
                detail_commande.getQuantite() + ","+
                detail_commande.getPrix_total() + ",'"+
                detail_commande.getEtat() +"')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(requete);
            System.out.println("Detail_Commande Ajouté avec Success");
        } catch (SQLException ex) {
                System.out.println("Insertion Detail_Commande Failure");
                System.out.println(ex);
                        
        }


    }
    public Detail_Commande insertDetail_commande(Detail_Commande detail_commande){
          String requete = "insert into detail_commande (commande_id,store_id,produit_id,quantite,prix_total,etat) values "
                + "(" +
                detail_commande.getCommande().getId()+ "," +
                detail_commande.getStore().getId()+ "," +
                detail_commande.getProduit().getId() + ",'"+
                detail_commande.getQuantite() + ",'"+
                detail_commande.getPrix_total() + ",'"+
                detail_commande.getEtat() +"')";
        try {
            PreparedStatement st = connection.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            st.executeUpdate();
            ResultSet generatedKeys=st.getGeneratedKeys();
            if (generatedKeys.next()) {
    // retrieve the auto-generated ID from the ResultSet
            detail_commande.setId(generatedKeys.getInt(1));
            System.out.println("Commande Ajouté avec Success");
        }} catch (SQLException ex) {
                System.out.println("Insertion Commande Failure");
                System.out.println(ex);
                        
        }
        return detail_commande;
    }
    
    public void delete(Detail_Commande detail_commande) {
        
  String requete = "DELETE FROM commande WHERE id = '"+detail_commande.getId()+"'";
        try {
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commande: "+detail_commande.getId()  +"    from Store :" +detail_commande.getStore().getId()+ "has been deleted");
        } catch (SQLException ex) {
               System.out.println(ex);
        }
    }

    
    public void update(Detail_Commande detail_commande)
    {
        /* Never to be used as a STAND ALONE */
        System.out.println("NOT IMPLEMENTED CAUSE NEVER TO BE USED");
    }
    public void updateEtat(Detail_Commande detail_commande) {
        /* Mise A jour de letat  de la commande */
        String requete = "UPDATE detail_commande SET Etat = '"+detail_commande.getEtat()+"' WHERE id="+ detail_commande.getId();
        try {
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commande Modifier avec Etat:: "+detail_commande.getEtat());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void updateDetail_Commande_Store(Detail_Commande detail_commande){
         /* Mise A jour de letat  de la commande */
        String requete = "UPDATE detail_commande SET store_id = '"
                + ""+detail_commande.getStore().getId()+""
                + "' WHERE id="
                + "'"+ detail_commande.getId()+"'";
        try {
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commande Modifier avec Store:: "+detail_commande.getStore());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public List<Detail_Commande> readAll() {
        List<Detail_Commande> list_detail_commande=new ArrayList<>();
        String requete="select * from detail_commande";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                /*integre Rayen       readByid(Store) ServiceStore
                ServiceStore ss=new ServiceStore();
                Store store=ss.readById(rs.getInt(3));
                
                */
                /*integre Safa       readByid(produit) Serviceproduit
                ServiceProduit sp=new ServiceProduit();
                Produit produit=sp.readById(rs.getInt(4));
                
                */
                Service_Commande sc=new Service_Commande();
                Commande commande=sc.readById(rs.getInt(2));
                Detail_Commande detail_commande=new Detail_Commande(
                        rs.getInt(1),
                        commande,
                        new Store(rs.getInt(3)),  // a replace par store a l'integration    integre
                        new Produit(rs.getInt(4)),  // a replace par Produiet a l'integeration  integre
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7));
                list_detail_commande.add(detail_commande);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_detail_commande;
    }
    
    public Detail_Commande readById(int id) {
         Detail_Commande detail_comande=new Detail_Commande();
        String condition = ("id ='"+id+"'");
        String requete="SELECT * FROM detail_comande WHERE " + condition +"LIMIT 1"; /*Limit 1    limite la requete a un seul ligne de retour*/ 
        try {
            PreparedStatement st=connection.prepareStatement(requete);
            ResultSet rs=st.executeQuery(requete);
            if(rs.next()){
                 /*integre Rayen       readByid(Store) ServiceStore
                ServiceStore ss=new ServiceStore();
                Store store=ss.readById(rs.getInt(3));
                
                */
                /*integre Safa       readByid(produit) Serviceproduit
                ServiceProduit sp=new ServiceProduit();
                Produit produit=sp.readById(rs.getInt(4));
                
                */
                Service_Commande sc=new Service_Commande();
                Commande commande=sc.readById(rs.getInt(2));
                detail_comande=new Detail_Commande(
                        rs.getInt(1),
                        commande,
                        new Store(rs.getInt(3)),  // a replace par store a l'integration    integre
                        new Produit(rs.getInt(4)),  // a replace par Produit a l'integeration  integre
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7));
                 
            }
        } catch (SQLException e) {
            System.out.println("Error ReadByID Detail_commande Exception = \n"+e );
        }
        return detail_comande;

    }
    public List<Detail_Commande> readByStoreId(Store store){
        List<Detail_Commande> list_detail_commande=new ArrayList<>();
        String requete="select * from detail_commande WHERE  store_id='"+store.getId()+"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                /*integre Rayen       readByid(Store) ServiceStore
                ServiceStore ss=new ServiceStore();
                Store store=ss.readById(rs.getInt(3));
                
                */
                /*integre Safa       readByid(produit) Serviceproduit
                ServiceProduit sp=new ServiceProduit();
                Produit produit=sp.readById(rs.getInt(4));
                
                */
                Service_Commande sc=new Service_Commande();
                Commande commande=sc.readById(rs.getInt(2));
                Detail_Commande detail_commande=new Detail_Commande(
                        rs.getInt(1),
                        commande,
                        new Store(rs.getInt(3)),  // a replace par store a l'integration    integre
                        new Produit(rs.getInt(4)),  // a replace par Produit a l'integeration  integre
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7));
                list_detail_commande.add(detail_commande);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_detail_commande;
    }
    public List<Detail_Commande> readByStoreIdByEtat(Store store,String etat){
        /* return list detail commande     depends de store entré et etat entré 
            example Store(1)   etat="Pending"  pending a afficher as a side bar for the store to start working on the commands
            example Store(2)   etat="Terminer"
        */
        List<Detail_Commande> list_detail_commande=new ArrayList<>();
        String requete="select * from detail_commande WHERE  "
                + "store_id='"+store.getId()+"' AND "
                + "etat='"+etat+"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                /*integre Rayen       readByid(Store) ServiceStore
                ServiceStore ss=new ServiceStore();
                Store store=ss.readById(rs.getInt(3));
                
                */
                /*integre Safa       readByid(produit) Serviceproduit
                ServiceProduit sp=new ServiceProduit();
                Produit produit=sp.readById(rs.getInt(4));
                
                */
                Service_Commande sc=new Service_Commande();
                Commande commande=sc.readById(rs.getInt(2));
                Detail_Commande detail_commande=new Detail_Commande(
                        rs.getInt(1),
                        commande,
                        new Store(rs.getInt(3)),  // a replace par store a l'integration    integre
                        new Produit(rs.getInt(4)),  // a replace par Produit a l'integeration  integre
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7));
                list_detail_commande.add(detail_commande);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_detail_commande;
    }
    /*for use in the program and works to find the first store containing a specific product*/
    /*this works dont know how but it does DONT TOUTCH IT*/
    public Store getIdFromDatabase(Detail_Commande detail_commande,State state) {
    Store store=new Store();
    String stateName = state.getKey();
    ArrayList<State> tunisiemap=TunisieMap.getInstance().getAdjacencyList();
    // Attempt to retrieve the ID from the database using the state name
    int id = getStorefromDatabase(detail_commande,stateName);

    if (id != -1) {
        // ID found, return it
         store.setId(id);
         return store;
    } else {
        // ID not found, search recursively in adjacent states
        for (int adjacentStateIndex : state.getValue()) {
            State adjacentState = tunisiemap.get(adjacentStateIndex);
            store = getIdFromDatabase(detail_commande,adjacentState);

            if (id != -1) {
                // ID found in adjacent state, return it
                store.setId(id);
            return store;
            }
        }
    }

    // ID not found in this state or its adjacent states
    return store;
}

 /*NEver to be used outside of here and can only be called by 'getIdFromDatabase'*/
    private int getStorefromDatabase(Detail_Commande detail_commande,String stateName) {
    int id = -1;
    try {
        /* must integre to work with only find the store containing the correct produit
            so far waiting for table produit_store qui contient (store,produit_id,ref_produit)
            search for reference produit toute en accomendant a la STATE de tunisieMap
        et retour le store
        
        nouvelle requete a inserer 
            SELECT s.id FROM Store as s , Produit_store as ps,reference as r 
            where ( s.location LIKE ? 
            AND ps.ref_Produit= ? 
            AND s.id= ps.store_id 
            AND r.id_ref=detail_commande.getProduit().getId()
            AND ps.produit_id=r.id
            ) LIMIT 1
        to also include 
                requete.setInt(2,detail_commande.getProduit().getId());
        integre
       
        */
        PreparedStatement requete = connection.prepareStatement("SELECT id FROM Store WHERE location LIKE ? LIMIT 1");
        requete.setString(1, "%" + stateName + "%");
        ResultSet rs = requete.executeQuery();

        if (rs.next()) {
            id= rs.getInt("id");
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return id;
}
 
    public List<Detail_Commande> readallByCommande(int intcommande)
    {/* return list detail commande     depends de commande
           
        */
        List<Detail_Commande> list_detail_commande=new ArrayList<>();
        String requete="select * from detail_commande WHERE  "
                + "commande_id="+intcommande ;
              
        try {
            Statement st=connection.createStatement();
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
                Service_Commande sc=new Service_Commande();
                Commande commande=sc.readById(intcommande);
                Detail_Commande detail_commande=new Detail_Commande(
                        rs.getInt(1),
                        commande,
                        new Store(rs.getInt(3)),  // a replace par store a l'integration    integre
                        new Produit(rs.getInt(4)),  // a replace par Produit a l'integeration  integre
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getString(7));
                list_detail_commande.add(detail_commande);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_detail_commande;
        
    }
    public int getnumberuniqueproducts(Commande commande){
        int count=0;
     String requete="select count(*) from detail_commande where commande_id= "+commande.getId();
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            if(rs.next())
            count=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }
    public int getnumberallproducts(Commande commande){
        int count=0;
     String requete="select SUM(quantite) from detail_commande where commande_id= "+commande.getId();
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            if(rs.next())
            count=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

}
