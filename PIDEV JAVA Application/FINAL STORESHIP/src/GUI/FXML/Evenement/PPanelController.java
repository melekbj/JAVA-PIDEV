/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Evenement;

import com.mysql.cj.Query;
import connexion.ConnexionSource;
import controller.Evenement_Service;
import entity.Evenement_entite;
import entity.Upload;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class PPanelController implements Initializable {
 private File file;
    String pic;

    private DatePicker fin;
    private DatePicker debut;
    @FXML
    private Button btnimage;
    private TextField lieu;
    private TextField titre;
    private TextField desciprtion;
    private TextField nbr;
    @FXML
    private Button btnInsert;
    @FXML 
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Evenement_entite> tableEV;
    private TextField fnid;
    
    @FXML
    private Button btnreadall;
    @FXML
    private DatePicker btn_fin;
    @FXML
    private DatePicker btn_debut;
    @FXML
    private TextField btnlieu;
    @FXML
    private TextField btntitre;
    @FXML
    private TextField btn_description;
    @FXML
    private TextField btnNb_Max;
    
    
    private TableColumn<Evenement_entite, Integer> idEvenement;
    @FXML
    private TableColumn<Evenement_entite, LocalDate> date_debutEvenement;
    @FXML
    private TableColumn<Evenement_entite, LocalDate> date_finEvenement;
    private TableColumn<Evenement_entite, String> imageEvenement;
    @FXML
    private TableColumn<Evenement_entite, String> lieuEvenement;
    @FXML
    private TableColumn<Evenement_entite, String> titreEvenement;
    @FXML
    private TableColumn<Evenement_entite, String> descriptionEvenement;
    @FXML
    private TableColumn<Evenement_entite, Integer> nbMax_place;
    @FXML
    private Text errorMessage;
    @FXML
    private Label go_back_btn;
    @FXML
    private ImageView img_ev;
    private Evenement_entite localEven;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date_debutEvenement.setCellValueFactory(new PropertyValueFactory<>("date_debutEvenement"));
        date_finEvenement.setCellValueFactory(new PropertyValueFactory<>("date_finEvenement"));
        lieuEvenement.setCellValueFactory(new PropertyValueFactory<>("lieuEvenement"));
        titreEvenement.setCellValueFactory(new PropertyValueFactory<>("titreEvenement"));
        descriptionEvenement.setCellValueFactory(new PropertyValueFactory<>("descriptionEvenement"));
        nbMax_place.setCellValueFactory(new PropertyValueFactory<>("nbMax_place"));


       readingallfromDB();
        
        
    }    

/*
    private void onUdt(ActionEvent event) {
                Evenement_Service es=new Evenement_Service();

                
    }

    private void insert(ActionEvent event) {
        LocalDate n=debut.getValue();
        Evenement_Service es=new Evenement_Service();
        Evenement_entite e=new Evenement_entite(
                debut.getValue(),
                fin.getValue(),
                btnimage.getText(),
                lieu.getText(),
                titre.getText(),
                desciprtion.getText(),
                Integer.parseInt(nbr.getText()));
    
        es.insert(e);
        
                     /*  List<Evenement_entite> list=new ArrayList<>();
                       list=es.readAll();
                       ObservableList<Evenement_entite> ob=FXCollections.observableArrayList(list);
                       

                  listviez.setItems(ob);*/

    @FXML
    private void insert(ActionEvent event) throws IOException {
         LocalDate n=btn_debut.getValue();
            LocalDate n1=btn_fin.getValue();
            
            // save the image inside htdoc
         String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
         
         
String newFileName = "image_" + System.currentTimeMillis() + extension;
        Path destination = Paths.get("C:\\xampp\\htdocs\\ImagePidev\\", newFileName);
        Files.copy(selectedFile.toPath(), destination);

// Get the new file name
String newFilePath = destination.toString();
            
            
        Evenement_Service es=new Evenement_Service();
        Evenement_entite e=new Evenement_entite(
                n,
                n1,
                newFilePath,
                btnlieu.getText(),
                btntitre.getText(),
                btn_description.getText(),
                Integer.parseInt(btnNb_Max.getText()));
       
        es.insert(e);
        JOptionPane.showMessageDialog(null, "Event added");
        
        btnInsert.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Evenement/PPanel.fxml"));
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root);
                        mainStage.setScene(scene);
                        mainStage.show();
        
    }

    @FXML
    private void update(ActionEvent event) {
        System.out.println(btn_debut.getValue().getClass());
        System.out.println("starting");
        Evenement_Service es=new Evenement_Service();
      

        Evenement_entite e=new Evenement_entite(localEven.getIdEvenement(),
                btn_debut.getValue(),
                btn_fin.getValue(),
                btnimage.getText(),
                btnlieu.getText(),
                btntitre.getText(),
                btn_description.getText(),
                Integer.parseInt(btnNb_Max.getText()));
        System.out.println("test");
        es.update(e);
           System.out.println("test2");
           JOptionPane.showMessageDialog(null, "Event updated");
           readingallfromDB();
    }

    @FXML
    private void Delete(ActionEvent event) {
         Evenement_entite ev = tableEV.getSelectionModel().getSelectedItem();
        int n=Integer.parseInt(fnid.getText());
        Evenement_Service es=new Evenement_Service();
        Evenement_entite e=new Evenement_entite(ev.getIdEvenement());
        es.delete(e);
        JOptionPane.showMessageDialog(null, "Event deleted");
    }
    
    
    
  /*   private void loadFour() {
        //To change body of generated methods, choose Tools | Templates.
        Evenement_Service es=new Evenement_Service();
       // connexion= cnx.getInstance().getCnx();
    cnx = ConnexionSource.getInstance().getCnx();
    //refresh();
    idCol.setCellValueFactory(new PropertyValueFactory<>("idEvenement "));
    debutCol.setCellValueFactory(new PropertyValueFactory<>("date_debutEvenement"));
    finCol.setCellValueFactory(new PropertyValueFactory<>("date_finEvenement"));
    imageCol.setCellValueFactory(new PropertyValueFactory<>("imageEvenement"));
    lieuCol.setCellValueFactory(new PropertyValueFactory<>("lieuEvenement"));
    titreCol.setCellValueFactory(new PropertyValueFactory<>("titreEvenement"));
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvenement"));
    nbMacCol.setCellValueFactory(new PropertyValueFactory<>("nbnbMax_place	"));
    
    }*/
    /*
    @FXML
    private void readall(MouseEvent event) {
           try {
            fourList.clear();
            
            query = "select * from fournisseurs";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                fourList.add(new  Fournisseur(
                        resultSet.getInt("idf"),
                        resultSet.getString("nomf"),
                        resultSet.getString("prenomf"),
                        resultSet.getString("catf"),
                        resultSet.getInt("telf"),
                        resultSet.getString("addf")
                        )); 
               tablestock.setItems(fourList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    */
    
    
/*     void handleMouseAction(MouseEvent event) {
        try{
            Evenement ev = tvEvenement.getSelectionModel().getSelectedItem();
            btn_debut.setValue(ev.getDate_debutEvenement().toLocalDate());
             btn_fin.setValue(ev.getDate_finEvenement().toLocalDate());
            btnlieu.setText(ev.getTitreEvenement());
            btnlieu.setText(ev.getLieuEvenement());
          
            btn_description.setText(ev.getDescriptionEvenement());
            
            btnimage.setText(ev.getImageEvenement());
        }catch (Exception e){
            System.out.println("");
        }
    }*/
 
    @FXML
    private void handleMouseAction(MouseEvent event) {
         
    Evenement_entite per = tableEV.getSelectionModel().getSelectedItem();
    localEven=per;
    btn_debut.setValue(per.getDate_debutEvenement());
    btn_fin.setValue(per.getDate_finEvenement());
        try {
                img_ev.setImage(new Image("file:///"+per.getImageEvenement()));

        } catch (Exception e) {
            System.out.println("image PPanel Controller Event FXML");
        }
    btnlieu.setText(String.valueOf(per.getLieuEvenement()));
    btntitre.setText(String.valueOf(per.getTitreEvenement()));
    btn_description.setText(String.valueOf(per.getDescriptionEvenement()));
    btnNb_Max.setText(String.valueOf(per.getNbMax_place()));
        
    }
    
    
    @FXML
    private void readall(ActionEvent event) {
        readingallfromDB();
         
            }
    private void readingallfromDB(){
         List<Evenement_entite>  EvenementList=new ArrayList<>();
          Evenement_Service es=new Evenement_Service();
          EvenementList=es.readAll();
           

        
            ObservableList<Evenement_entite> ez=FXCollections.observableArrayList(EvenementList);
            System.out.println(ez);
               tableEV.setItems(ez);
    }

    private void sendmeupdate(ActionEvent event) {
        reroutetoupdate();
    }
       private void reroutetoupdate(){
           
           btnUpdate.getScene().getWindow().hide();
        try {
             Parent root;
            root = FXMLLoader.load(getClass().getResource("PPanel2.fxml"));
              Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
              
       }
    private File selectedFile = null;
    private Stage primaryStage;

    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
//        FileChooser fileChooser = new FileChooser();
//        file = fileChooser.showOpenDialog(null);
//        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//
//        //pic=(file.toURI().toString());
//        pic = new Upload().upload(file, "\\Images");
//        System.out.println(pic);
//        img_ev.setImage(new Image("file:"+pic));

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload an image");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            selectedFile = fileChooser.showOpenDialog(primaryStage);    
            if (selectedFile != null) 
            {
                btnimage.setText(selectedFile.getName());
                try {
                                    img_ev.setImage(new Image("file:" + selectedFile));

                } catch (Exception e) {
                    System.out.println("error image");
                }

            }else{
                btnimage.setText(selectedFile.getName());
            }
    }


    @FXML
    private void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) go_back_btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML/Admin.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

        
}
    
    
        
  
    

    


