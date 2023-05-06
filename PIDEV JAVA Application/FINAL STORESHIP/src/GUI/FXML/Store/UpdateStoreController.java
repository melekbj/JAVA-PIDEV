/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.FXML.Store;

import entity.Store;
import entity.User;
import controller.StoreService;
import entity.Util.TunisieMap;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.stream.Collectors;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
/**
 * FXML Controller class
 *
 * @author Jmili
 */
public class UpdateStoreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button imageSt;
    @FXML
    private TextField STName;
    @FXML
    private TextField STAdresse;
    @FXML
    private ImageView image_St;
    @FXML
    private Button confirmer;
        private File selectedFile = null;
    private Stage primaryStage;
    @FXML
    private ComboBox<String> choose_state;
    private Store localstore;
    @FXML
    private Button anuler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        states();
        localstore=StoreController.getInstance().getlocalStore();
 }    
private void states(){
    TunisieMap list=TunisieMap.getInstance().getInstance();
    List<String> listS=list.getAdjacencyList().stream().map(e->e.getKey()).collect(Collectors.toList());
for (String s:listS){
choose_state.getItems().add(s);
}
    
    
    }
    @FXML
    private void modifyStore(ActionEvent event) throws IOException {
        StoreService SS=new StoreService();
     
           // save the image inside htdoc
         String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
 String newFileName = "image_" + System.currentTimeMillis() + extension;
        Path destination = Paths.get("C:\\xampp\\htdocs\\ImagePidev\\", newFileName);
        Files.copy(selectedFile.toPath(), destination);
// Get the new file name
String newFilePath = destination.toString();

        
        
        
//        Store st=new Store(STName.getText(),STAdresse.getText(),u);
        localstore.setNameSt(STName.getText());
        localstore.setLocation(STAdresse.getText()+" "+choose_state.getSelectionModel().getSelectedItem());
        localstore.setPhoto(newFilePath);
        SS.update(localstore);
        StoreController.getInstance().setLocalstore(localstore);

      //  Stage stage = (Stage) confirmer.getScene().getWindow();
       // stage.close();
       // Stage primaryStage = new Stage();
       MainController LPC=new MainController();
    
       // FXMLLoader root =new FXMLLoader(getClass().getResource("/GUI/FXML/ListProduit.fxml"));
       // primaryStage.setScene(new Scene(root));
    LPC.noupdateinfoStore();
       // primaryStage.show();
    }
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
                            image_St.setImage(new Image("file:" + selectedFile));

            } catch (Exception e) {
                System.out.println("error image update Stroe Controller ");
            }
              //ImageView.setImage(new Image("file:" + selectedFile));
        }
    }

    @FXML
    private void go_back(ActionEvent event) {
        MainController LPC=new MainController();
        LPC.noupdateinfoStore();
    }


}

