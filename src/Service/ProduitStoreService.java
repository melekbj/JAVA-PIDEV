package Service;

import Entities.Produit;
import Entities.ProduitStore;
import Entities.Store;
import Entities.User;
import Util.DataSource;
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
public class ProduitStoreService implements IService<ProduitStore>{
        private Connection conn;

    public ProduitStoreService (){
            conn = DataSource.getInstance().getCnx();
}
    @Override
    public void insert(ProduitStore pst) {
        
    }

    public void insertProduitStore(Produit prd,Store St) {
        String requete = "INSERT INTO `produitstore`(`store_id`, `produit_id`) VALUES (?,?)"
                + "(" + St.getId() + ","+prd.getId()+")";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println(prd.getNom()+ "has been added to"+St.getNameSt());
                     
        } catch (SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
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

    @Override
    public void update(ProduitStore t) {
    }
    /*@Override
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
@Override
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
@Override
public ProduitStore readById(int id) {
    ProduitStore PS = new ProduitStore();
    String requete = "SELECT `produit_id` FROM `store_produit` WHERE `store_id` = ?";

    try (PreparedStatement st = conn.prepareStatement(requete)) {
        st.setInt(1, id);
        try (ResultSet rs = st.executeQuery()) {
            ServiceProduit pdt = new ServiceProduit();
            List<Produit> LPDT = new ArrayList<>();
            StoreService SS = new StoreService();
            Store St = SS.readById(id);

            while (rs.next()) {
                int e = rs.getInt("produit_id");
                Produit p = pdt.readById(e);
                LPDT.add(new Produit(p.getNom(), p.getDescription(), p.getCategorie(), p.getPrix(), p.getQuantite(), p.getEtat()));
            }

            PS.setSt(St);
            PS.setProd(LPDT);
        }
    } catch (SQLException ex) {
        Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return PS;
}

   
   
}
