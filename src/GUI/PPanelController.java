/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.mysql.cj.Query;
import connexion.ConnexionSource;
import controller.Evenement_Service;
import entity.Evenement_entite;
import entity.Upload;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
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
    Evenement_entite Evenement_entite = new Evenement_entite();
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
    ObservableList<Evenement_entite> obsEvs = FXCollections.observableArrayList();

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

    @FXML
    private TableColumn<Evenement_entite, Integer> idEvenement;
    @FXML
    private TableColumn<Evenement_entite, LocalDate> date_debutEvenement;
    @FXML
    private TableColumn<Evenement_entite, LocalDate> date_finEvenement;
    @FXML
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Evenement_Service ev = new Evenement_Service();
        ev.readAll().stream().forEach((p) -> {
            obsEvs.add(p);

        });
        idEvenement.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
        date_debutEvenement.setCellValueFactory(new PropertyValueFactory<>("date_debutEvenement"));
        date_finEvenement.setCellValueFactory(new PropertyValueFactory<>("date_finEvenement"));
        lieuEvenement.setCellValueFactory(new PropertyValueFactory<>("lieuEvenement"));
        titreEvenement.setCellValueFactory(new PropertyValueFactory<>("titreEvenement"));
        descriptionEvenement.setCellValueFactory(new PropertyValueFactory<>("descriptionEvenement"));
        nbMax_place.setCellValueFactory(new PropertyValueFactory<>("nbMax_place"));

        tableEV.setItems(obsEvs);

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
    private void insert(ActionEvent event) {
        LocalDate n = btn_debut.getValue();
        LocalDate n1 = btn_fin.getValue();
 if (btnlieu.getText().isEmpty() || btntitre.getText().isEmpty() || btn_description.getText().isEmpty() || btnNb_Max.getText().isEmpty()) {
            errorMessage.setText("Veuillez remplir tous les champs");
        // controlle saisie dte debut et date fin
         
        } // controlle saisie de tous les autre attribut
 else if(n.isAfter(n1)) {
            errorMessage.setText("Date debut doit etre inferieur a date fin");
        } else {
            
        Evenement_Service es = new Evenement_Service();
        Evenement_entite e = new Evenement_entite(
                n,
                n1,
                pic,
                btnlieu.getText(),
                btntitre.getText(),
                btn_description.getText(),
                Integer.parseInt(btnNb_Max.getText()));

            es.insert(e);
            JOptionPane.showMessageDialog(null, "Event added");
            readingallfromDB();
        }

    }

    @FXML
    private void update(ActionEvent event) {
    
        Evenement_Service es = new Evenement_Service();
      Evenement_entite.setLieuEvenement(btnlieu.getText());

        Evenement_entite.setTitreEvenement(btntitre.getText());
        Evenement_entite.setDescriptionEvenement(btn_description.getText());
        Evenement_entite.setNbMax_place(Integer.parseInt(btnNb_Max.getText()));
        Evenement_entite.setDate_debutEvenement(btn_debut.getValue());
        Evenement_entite.setDate_finEvenement(btn_fin.getValue());
        Evenement_entite.setImageEvenement(pic);
        es.update(new Evenement_entite(
                Evenement_entite.getIdEvenement(),
                Evenement_entite.getDate_debutEvenement(),
                Evenement_entite.getDate_finEvenement(),
                Evenement_entite.getImageEvenement(),
                Evenement_entite.getLieuEvenement(),
                Evenement_entite.getTitreEvenement(),
                Evenement_entite.getDescriptionEvenement(),
                Evenement_entite.getNbMax_place()
        ));
            JOptionPane.showMessageDialog(null, "Event updated");
            obsEvs.set(tableEV.getSelectionModel().getFocusedIndex(),Evenement_entite);
    }
public void clearFields() {
        btn_debut.getEditor().clear();
        btn_fin.getEditor().clear();
        btnlieu.clear();
        btntitre.clear();
        btn_description.clear();
        btnNb_Max.clear();


       
    }
    
    @FXML
    private void Delete(ActionEvent event) {
        Evenement_Service es = new Evenement_Service();
        Evenement_entite Evenement_entite = new Evenement_entite();
        ObservableList obsEvs, Pointage;
        obsEvs=tableEV.getItems();
        Pointage = tableEV.getSelectionModel().getSelectedItems();
        Evenement_entite = tableEV.getSelectionModel().getSelectedItems().get(0);
        es.delete(new Evenement_entite(Evenement_entite.getIdEvenement()));
        Pointage.forEach(obsEvs::remove);

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
//sysout per
        System.out.println(per.toString());
        btn_debut.setValue(per.getDate_debutEvenement());
        btn_fin.setValue(per.getDate_finEvenement());
        btnlieu.setText(per.getLieuEvenement());
        btntitre.setText(per.getTitreEvenement());
        btn_description.setText(per.getDescriptionEvenement());
        btnNb_Max.setText(String.valueOf(per.getNbMax_place()));
        btnimage.setText(per.getImageEvenement());
    }

    @FXML
    private void readall(ActionEvent event) {
        readingallfromDB();

    }

    private void readingallfromDB() {
        List<Evenement_entite> EvenementList = new ArrayList<>();
        Evenement_Service es = new Evenement_Service();
        EvenementList = es.readAll();

        ObservableList<Evenement_entite> ez = FXCollections.observableArrayList(EvenementList);
        System.out.println(ez);
        tableEV.setItems(ez);
    }

    private void sendmeupdate(ActionEvent event) {
        reroutetoupdate();
    }

    private void reroutetoupdate() {
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

    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //pic=(file.toURI().toString());
        pic = new Upload().upload(file, "\\Images");
        System.out.println(pic);
    }
}
