package application.controller;

import application.model.Tierlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class creerNewTierlistController extends MainController {
    @FXML
    private ImageView imagePreviewImageView;
    @FXML
    private Button importImageButton;
    @FXML
    private TextField nomListTextField;
    @FXML
    private Button creerTierlistButton;
    @FXML
    private Button annulerButton;

    private backgroundTierlistController parentController;

    public void setParentController(backgroundTierlistController parent) {
        this.parentController = parent;
    }

    @FXML
    void importImageButtonPressEvent(ActionEvent event) {
        ///Ouvre un explorer de fichier qui filtre uniquement les images png
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Images PNG", "*.png")
        );
        File fichier = fileChooser.showOpenDialog(null);
        if (fichier != null) {
            ///l'image de preview de la tierliste devient celle qui est selectionnée.
            System.out.println(fichier.toPath());
            Image image = new Image(fichier.toURI().toString());
            imagePreviewImageView.setImage(image);
        }
    }

    @FXML
    void creerTierlistPressEvent(ActionEvent event){
        Tierlist newTierlist = new Tierlist(nomListTextField.getText(), imagePreviewImageView.getImage());
        model.addTierList(newTierlist);
        parentController.chargerVue("home.fxml");

    }

    @Override
    public void refresh() {
    }

    @FXML
    void annulerButtonPressEvent(ActionEvent event){
        parentController.chargerVue("home.fxml");
    }


}
