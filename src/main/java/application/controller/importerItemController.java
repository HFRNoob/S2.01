package application.controller;

import application.model.Item;
import application.model.MyTierLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class importerItemController implements Initializable {
    @FXML
    private RadioButton importerImageRadioButton;
    @FXML
    private RadioButton importerApiRadioButton;
    @FXML
    private RadioButton importerTextRadioButton;
    @FXML
    private Button ouvrirImage;
    @FXML
    private Label etatFichierCharge;
    @FXML
    private Button addItemImportImage;
    @FXML
    private Button addItemImportAPI;
    @FXML
    private Button addItemImportText;
    @FXML
    private TextField importApiTextField;
    @FXML
    private TextField importTextTextField;
    @FXML
    private Button terminerButton;
    private Image imageSelectionnee;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup groupe = new ToggleGroup();
        importerImageRadioButton.setToggleGroup(groupe);
        importerApiRadioButton.setToggleGroup(groupe);
        importerTextRadioButton.setToggleGroup(groupe);

        groupe.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == importerImageRadioButton) {
                RadioButton selected = (RadioButton) newVal;
                ouvrirImage.setDisable(false);
                addItemImportImage.setDisable(false);
                importApiTextField.setDisable(true);
                addItemImportAPI.setDisable(true);
                importTextTextField.setDisable(true);
                addItemImportText.setDisable(true);
            }
            else if (newVal == importerApiRadioButton) {
                RadioButton selected = (RadioButton) newVal;
                ouvrirImage.setDisable(true);
                addItemImportImage.setDisable(true);
                importApiTextField.setDisable(false);
                addItemImportAPI.setDisable(false);
                importTextTextField.setDisable(true);
                addItemImportText.setDisable(true);
            }
            else if (newVal == importerTextRadioButton) {
                RadioButton selected = (RadioButton) newVal;
                ouvrirImage.setDisable(true);
                addItemImportImage.setDisable(true);
                importApiTextField.setDisable(true);
                addItemImportAPI.setDisable(true);
                importTextTextField.setDisable(false);
                addItemImportText.setDisable(false);
            }
        });

    }

    @FXML
    private void ouvrirImagePressEvent(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images PNG", "*.png")
        );
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images JPEG", "*.jpeg")
        );
        //Fichier récupéré
        File fichier = fileChooser.showOpenDialog(null);
        if (fichier != null){
            etatFichierCharge.setText(fichier.getName());
            imageSelectionnee = new Image(fichier.toURI().toString());
        }
        else{
            System.out.println("Pas de fichier selctionnés");
        }

    }

    @FXML
    private void addItemImportImagePressEvent(ActionEvent event){
        System.out.println("addItemImportImagePressEvent");
        Item item = new Item("item", imageSelectionnee);
        MyTierLists.getFocus().AddItem(item);
        //j'etait la
    }

    @FXML
    private void addItemImportApiPressEvent(ActionEvent event){
        System.out.println("addItemImportApiPressEvent");
        //TODO
    }

    @FXML
    private void addItemImportText(ActionEvent event){
        System.out.println("addItemImportTextPressEvent");
        String Texte = importTextTextField.getText();
        //TODO
    }

    @FXML
    private void terminerButtonPressEvent(ActionEvent event){
        System.out.println("terminerButtonPressEvent");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
