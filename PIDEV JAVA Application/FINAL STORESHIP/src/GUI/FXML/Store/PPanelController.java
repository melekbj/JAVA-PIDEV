/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Store;

import GUI.Controllers.PartnerContenuController;
import GUI.Controllers.PartnerMainController;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.ProduitStoreService;
import controller.ServiceCategorie;
import controller.ServiceProduit;
import controller.StoreService;
import entity.Categorie;
import entity.Produit;
import entity.ProduitStore;
import entity.Store;
import entity.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Bejaoui
 */
public class PPanelController implements Initializable {

    @FXML
    private Label fxnom;
    @FXML
    private Label fxphoto;
    @FXML
    private Label fxprix;
    @FXML
    private Label fxquantite;
    private int txtid;
    @FXML
    private TextField txtnom;
     @FXML
    private Button imageSt;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtquantite;
    private int txtetat;
    @FXML
    private TableView<Produit> table;
    private TableColumn<Produit, Integer> idcolumn;
    @FXML
    private TableColumn<Produit, String> nomcolumn;
    private TableColumn<Produit, String> photocolumn;
    @FXML
    private TableColumn<Produit, Float> prixcolumn;
    @FXML
    private TableColumn<Produit, Integer> quantitecolumn;
    @FXML
    private TableColumn<Produit, String> etatcolumn;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnReadAll;
    @FXML
    private ImageView image_St;
    @FXML
    private Button pdf;
    @FXML
    private Button categorie;
    @FXML
    private Button Search;
    @FXML
    private TextField txtsearch;
            private File selectedFile = null;

    ObservableList<Produit> ProduitListSearch;
    @FXML
    private ComboBox<String> combo;
    private Store localStore;
    private static PPanelController instance;
    public static PPanelController getINSTANCE(){
    return instance;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantitecolumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
         etatcolumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
         
         //combo
         ServiceCategorie sg=new ServiceCategorie();
        List<Categorie> list=new ArrayList<>();
        list=sg.readAll();
        List<String> list2=list.stream().map(e->e.getNom()).collect(Collectors.toList());
        System.out.println(list2);
        combo.setItems(FXCollections.observableArrayList(list2));
        localStore=MainController.getINSTANCE().getStore();
    }    

    @FXML
    private void insert(ActionEvent event) {
        //travaille de combo
        ServiceCategorie sa=new ServiceCategorie();
        Categorie g= new  Categorie();
        int intCategorie=sa.getCategorieByName(combo.getValue());

        g.setId(intCategorie);
       ProduitStoreService PSS=new ProduitStoreService();
        
        
        //controle de saisie
 
         if (txtnom.getText().isEmpty() || imageSt.getText().isEmpty() || txtprix.getText().isEmpty() || txtquantite.getText().isEmpty()  ) {
                  String titre="Error";
String message = "required fields are empty";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));
    }
         else if ( txtnom.getText().matches(".*[0-9].*")) {
                               String titre="Error";
String message = "Please enter only letters";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));
        }
        else { String titre="Produit Added Successfully";
String message = txtnom.getText();
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));}
        ServiceProduit es=new ServiceProduit();
        
        String newFilePath="";
        try {
            
       
           // save the image inside htdoc
         String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
 String newFileName = "image_" + System.currentTimeMillis() + extension;
        Path destination = Paths.get("C:\\xampp\\htdocs\\ImagePidev\\", newFileName);
        Files.copy(selectedFile.toPath(), destination);
// Get the new file name
 newFilePath = destination.toString();
 } catch (Exception e) {
        }
        Produit e=new Produit(
                txtnom.getText(),
                newFilePath,
                 Double.parseDouble(txtprix.getText()),
                Integer.parseInt(txtquantite.getText())
           
                 );
        //el i_cat te5ou el
        Categorie c=new Categorie();
        ServiceCategorie sc=new ServiceCategorie();
        String categorienom=combo.getValue();
        e.setCategorie(new Categorie(sc.getCategorieByName(categorienom)));
        StoreService SS=new StoreService();
            System.out.println("MainController.getINSTANCE().getlocaluser().getId()"+PartnerMainController.getInstance().getUser().getId());
        Store st=SS.readById(PartnerMainController.getInstance().getUser().getId());
 es.insert(e,st);
MainController.getINSTANCE().ajouterlistproduit();
    }

    @FXML
    private void delete(ActionEvent event) {
        int n=txtid;
        ServiceProduit  es=new ServiceProduit ();
        Produit e=new Produit(txtid);
        es.delete(e);
               String titre="SUCCESS";
String message ="Produit deleted Successfully";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));
        MainController.getINSTANCE().ajouterlistproduit();
    }
 
    @FXML
    private void update(ActionEvent event) {
        
         if (txtnom.getText().isEmpty() || imageSt.getText().isEmpty() || txtprix.getText().isEmpty() || txtquantite.getText().isEmpty() /*|| txtetat.getText().isEmpty()*/ ) {
                  String titre="Error";
String message = "required fields are empty";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));
MainController.getINSTANCE().ajouterlistproduit();

    }
         else if ( txtprix.getText().matches(".*[a-z].*")||txtquantite.getText().matches(".*[a-z].*"))
            {
                 String titre="Error";
                String message = "Please enter only Numbers !";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));

        }
        
       //controle de saisie 
              if (txtnom.getText().isEmpty() || imageSt.getText().isEmpty() || txtprix.getText().isEmpty() || txtquantite.getText().isEmpty() /*|| txtetat.getText().isEmpty()*/ ) {
                  String titre="Error";
String message = "required fields are empty";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));
    }
         else if ( txtprix.getText().matches(".*[a-z].*")||txtquantite.getText().matches(".*[a-z].*"))
            {
                 String titre="Error";
                String message = "Please enter only Numbers !";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.ERROR);
tray.showAndDismiss(Duration.millis(3000));

        }
            String  newFilePath="";
              try {
            
       
                 // save the image inside htdoc
         String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
 String newFileName = "image_" + System.currentTimeMillis() + extension;
        Path destination = Paths.get("C:\\xampp\\htdocs\\ImagePidev\\", newFileName);
        Files.copy(selectedFile.toPath(), destination);
// Get the new file name
 newFilePath = destination.toString();
 } catch (Exception e) {
        }
     
        //update
        System.out.println("starting");
        ServiceProduit es=new ServiceProduit();
        ServiceCategorie SC=new ServiceCategorie();
        Produit e=new Produit(
               txtid,
              txtnom.getText(),
            newFilePath,
             Double.parseDouble(txtprix.getText()),
          Integer.parseInt(txtquantite.getText()),
                new Categorie(SC.getCategorieByName(combo.getValue()))
            
        );    
        System.out.println("test");
        es.update(e);
           System.out.println("test2");
           String titre="SUCCESS";
           //controlee de saisie
String message ="Produit updated Successfully";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));
        }

    @FXML
    private void readAll(ActionEvent event) {
        List<Produit>  ProduitList=new ArrayList<>();
          ServiceProduit es=new ServiceProduit();
          Store store=MainController.getINSTANCE().getStore();
          System.out.println("afficher this store"+store);
          ProduitStoreService pss=new ProduitStoreService();
           ProduitList=pss.readProduitbyStore(store);
           ObservableList<Produit> ez=FXCollections.observableArrayList(ProduitList);
            System.out.println(ez);
               table.setItems(ez);
        
        
    }
        private Stage primaryStage;

    @FXML
     void insertimage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload an image");
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);
    selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null)
    {
        imageSt.setText(selectedFile.getName());
        try {
            image_St.setImage(new javafx.scene.image.Image("file:" + selectedFile));
        } catch (Exception e) {
            System.out.println("error image");
        }
    }
}
@FXML
    private void pdf(ActionEvent event) {
                         String titre="SUCCESS";
                String message = "Pdf download successfully !";
TrayNotification tray = new TrayNotification();
AnimationType type = AnimationType.POPUP;
tray.setAnimationType(type);
tray.setTitle(titre);
tray.setMessage(message);
tray.setNotificationType(NotificationType.SUCCESS);
tray.showAndDismiss(Duration.millis(3000));


         ServiceProduit vs=new ServiceProduit();
           Document document = new Document() ;
        try { 
            PdfWriter.getInstance(document, new FileOutputStream("C://pdf/vols.pdf"));
            document.open();
           // Image img = Image.getInstance("C://img/logo.png") ;
            //document.add(img);
            Paragraph ph1 = new Paragraph("liste des produits!");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(6);
            //On créer l'objet cellule.
            PdfPCell cell;
            //contenu du tableau.
            table.addCell("id : ");
            table.addCell("nom : ");
            table.addCell("photo : ");  
            table.addCell("prix : ");
            table.addCell("quantite : ");
            table.addCell("etat : ");
            vs. readAll().forEach(e
                    -> {
         table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(String.valueOf(e.getId()));
        table.addCell(e.getNom());      
        table.addCell(e.getPhoto());
        table.addCell(String.valueOf(e.getPrix()));
        table.addCell(String.valueOf(e.getQuantite()));
        table.addCell(String.valueOf(e.getEtat()));
    //Scale to new height and new width of image
    //Add to document
  }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            document.addAuthor("list produit");
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
            
        
    }

//    @FXML
//    private void categorie(ActionEvent event) {
//        try {
//                Parent page = FXMLLoader.load(getClass().getResource("PPanel2.fxml"));
////                Scene scene = new Scene(page);
////                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////                stage.setScene(scene);
////                stage.setResizable(false);
////                stage.show();
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
         Produit per = table.getSelectionModel().getSelectedItem();
    txtid=per.getId();
    txtnom.setText(String.valueOf(per.getNom()));
    imageSt.setText(String.valueOf(per.getPhoto()));
    txtprix.setText(String.valueOf(per.getPrix()));
    txtquantite.setText(String.valueOf(per.getQuantite()));
   txtetat=per.getEtat();
    }

    @FXML
    private void Search(ActionEvent event) {
               ServiceProduit st= new  ServiceProduit();
        ProduitListSearch = st.likeByProduit(txtsearch.getText());
        table.setItems(ProduitListSearch);
    }


    @FXML
    private void categorie(ActionEvent event) {
    }

    @FXML
    private void go_back(MouseEvent event) {
    
    MainController LPC=new MainController();
        LPC.noupdateinfoStore();
                MainController.getINSTANCE().ajouterlistproduit();

    }

   
}
    



