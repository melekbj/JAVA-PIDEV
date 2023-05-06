package controller;

import entity.Categorie;
import entity.Produit;
import entity.ProduitStore;
import entity.Store;
import entity.User;
import connexion.ConnexionSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jmili
 */
public class ProduitStoreService{
        private Connection conn;

    public ProduitStoreService (){
            conn = ConnexionSource.getInstance().getCnx();
}
 
    public void insert(ProduitStore pst) {
        
    }

public void insertProduitStore(Produit prd, Store St) {
    String requete = "INSERT INTO `store_produit`(`store_id`, `produit_id`) VALUES (?,?)";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, St.getId());
        pst.setInt(2, prd.getId());
        pst.executeUpdate();
        System.out.println(prd.getNom() + " has been added to " + St.getNameSt());
    } catch (SQLException ex) {
        Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 
    public void delete(ProduitStore t) {
    }
    //suppression d'un produit d'un store
        public void deleteProduitST(Produit t) {
            ServiceProduit pts=new ServiceProduit();
            pts.delete(t);
    }   
        //suppression d'un store et toute les produit dans se site
      //public void deleteStorePDT(Store st) {
        //StoreService str=new StoreService();
        //str.delete(st);
    //}

 
    public void update(ProduitStore t) {
    }
    /*  
    public List<ProduitStore> readAll() {
                 ProduitStore PS=new ProduitStore();
                List<ProduitStore> listpdt=new ArrayList<>();
                String rqt ="SELECT store_id, GROUP_CONCAT(produit_id) as produits " +
                     "FROM `store_produit` GROUP BY store_id;";


            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(rqt);
                StoreService SS=new StoreService();
                Store S=new Store(rs.getInt("Store_id"),SS.readById(rs.getInt(1)).getNameSt(),SS.readById(rs.getInt(1)).getLocation(),SS.readById(rs.getInt(1)).getOwner());
ServiceProduit pdt=new ServiceProduit();
while(rs.next()){
//listpdt.addAll(pdt.readALL());

                  String str= rs.getString(2);
String[] parts=str.split(",");
List<String> list=Arrays.asList(parts);

for(String e:list){
 PS.ajouterproduit(new Produit(pdt.readById(Integer.parseInt(e)).getNom(),pdt.readById(Integer.parseInt(e)).getDescription(),pdt.readById(Integer.parseInt(e)).getCategorie(),pdt.readById(Integer.parseInt(e)).getPrix(),pdt.readById(Integer.parseInt(e)).getQuantite(),pdt.readById(Integer.parseInt(e)).getEtat()));
         }
listpdt.add(PS);
}
            } catch (SQLException ex) {
                Logger.getLogger(ProduitStoreService.class.getName()).log(Level.SEVERE, null, ex);
            }
        return  listpdt;   
    }*/
    //retourne plusieur store et pour chaque store plusieur produit
  
public List<ProduitStore> readAll() {
    List<ProduitStore> listpdt = new ArrayList<>();
    String rqt = "SELECT store_id, GROUP_CONCAT(produit_id) as produits " +
                 "FROM `store_produit` GROUP BY store_id";

    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(rqt);
        StoreService SS = new StoreService();

        while (rs.next()) {
            ProduitStore PS = new ProduitStore();
            int storeId = rs.getInt("store_id");

            // Create a new Store object for each row in the result set
            Store store = new Store(storeId, SS.readById(storeId).getNameSt(),
                                     SS.readById(storeId).getLocation(),
                                     SS.readById(storeId).getOwner());
            PS.setSt(store);

            // Split the comma-separated string of product IDs and add each product to the ProduitStore object
            String str = rs.getString("produits");
            String[] parts = str.split(",");
            List<String> list = Arrays.asList(parts);
            ServiceProduit pdt = new ServiceProduit();

            for (String e : list) {
                PS.ajouterproduit(pdt.readById(Integer.parseInt(e)));
            }

            listpdt.add(PS);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProduitStoreService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return listpdt;
}
//retourne list de produit d un store specifique
  
public ProduitStore readById(int storeId) {
    String query = "SELECT `produit_id` FROM `store_produit` WHERE `store_id` = ?";
    ProduitStore produitStore = new ProduitStore();
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, storeId);
        try (ResultSet resultSet = statement.executeQuery()) {
            ServiceProduit produitService = new ServiceProduit();
            List<Produit> produits = new ArrayList<>();
            StoreService storeService = new StoreService();
            Store store = storeService.readById(storeId);
            while (resultSet.next()) {
                int produitId = resultSet.getInt("produit_id");
                Produit produit = produitService.readById(produitId);
                System.out.println("base de donne"+produit);
                produits.add(produit);
            }
            produitStore.setSt(store);
            produitStore.setProd(produits);
        }
    } catch (SQLException exception) {
        Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, exception);
    }
    return produitStore;
}

public Store readStoreById(Produit produit) {
    System.out.println("ent er");
    String query = "SELECT  * FROM `store_produit` WHERE `produit_id` = ?";
    StoreService storeService = new StoreService();
    Store store = null;
    try {;
            
            PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setInt(1, produit.getId());
        ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int storeid = rs.getInt("store_id");
                System.out.println("sotreid=========="+ storeid);
                store = storeService.readByStoreId(storeid);
                System.out.println("base de donne"+store);
            }
                    } catch (SQLException ex) {
                Logger.getLogger(ProduitStoreService.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    return store;
}

public List<Produit> readProduitbyStore(Store store) {
    List<Produit> listproduit=new ArrayList<>();
    System.out.println("ent er" + store);
    String query = "SELECT  * FROM `store_produit` WHERE `store_id` = ?";
    ServiceProduit produitService = new ServiceProduit();
    Produit produit = null;
    try {
            
            PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setInt(1, store.getId());
        ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                int produitid = rs.getInt("produit_id");
                produit = produitService.readById(produitid);
                listproduit.add(produit);
                System.out.println("base de donne"+store);
            }
                    } catch (SQLException ex) {
                Logger.getLogger(ProduitStoreService.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    return listproduit;
}
 /*public Map<Store, List<Produit>> getAllProduitsByStore() throws SQLException {
   // Build the query to retrieve all Produit objects for each Store
    String sql = "SELECT s.*, p.* FROM Store s JOIN store_produit ps ON s.id_store = ps.id_store JOIN Produit p ON ps.id_produit = p.id_produit";
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();
    
    // Create a Map to hold the results
    Map<Store, List<Produit>> resultMap = new HashMap<>();
    
    // Loop through the result set and add the Store and its corresponding Produit objects to the Map
    while (rs.next()) {
        int id_store = rs.getInt("id_store");
        String nom_store = rs.getString("nom_store");
        Store store = new Store(id_store, nom_store);
        
        int id_produit = rs.getInt("id_produit");
        String nom_produit = rs.getString("nom_produit");
        double prix_produit = rs.getDouble("prix_produit");
        Produit produit = new Produit( id,  nom,  description,  categorie,  prix,  quantite,  photo,  etat);
        
        if (!resultMap.containsKey(store)) {
            resultMap.put(store, new ArrayList<>());
        }
        resultMap.get(store).add(produit);
    }
    
    return resultMap;
}*/
   
}
