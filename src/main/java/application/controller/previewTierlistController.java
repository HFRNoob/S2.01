package application.controller;

import application.model.MyTierLists;
import application.service.api.APIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class previewTierlistController extends MainController {
    @FXML
    private MenuButton settingsButton;
    @FXML
    private ImageView previewImage;
    @FXML
    private Label tierlistName;
    @FXML
    private MenuItem changerImageMenuItem;
    @FXML
    private MenuItem changerTitreMenuItem;
    @FXML
    private MenuItem supprimerTierList;
    @FXML
    private VBox clickZone;

    private homeController parentController;
    public void setParentController(homeController parent) {
        this.parentController = parent;
    }


    @FXML
    void changerImagePressEvent(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Images PNG", "*.png")
        );
        File fichier = fileChooser.showOpenDialog(null);
        if (fichier != null) {
            Image image = new Image(fichier.toURI().toString());
            MyTierLists.getTierlist(tierlistName.getText()).setPreviewImage(image);
            refresh();
        }
    }

    @FXML
    void changerTitrePressEvent(ActionEvent event) {
        ///Ouvre une popup permettant de changer le titre de la tierliste
        Stage popup = new Stage();
        popup.setTitle("Modifier le texte");
        TextField textField = new TextField(tierlistName.getText());
        Button valider = new Button("Valider");
        valider.setOnAction(e -> {

            MyTierLists.getTierlist(tierlistName.getText()).setName(textField.getText());
            tierlistName.setText(textField.getText());

            popup.close();
            refresh();
        });
        Button annuler = new Button("Annuler");
        annuler.setOnAction(e -> popup.close());

        HBox boutons = new HBox(10, valider, annuler);
        VBox layout = new VBox(10, new Label("Nouveau titre :"), textField, boutons);
        layout.setPadding(new Insets(15));

        popup.setScene(new Scene(layout));
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.showAndWait();
    }

    @FXML
    void supprimerTierListPressEvent(ActionEvent event){
        System.out.println("supprimerTierlistPressEvent");
        MyTierLists.removeTierList(MyTierLists.getTierlist(tierlistName.getText()));
        parentController.refresh();
    }

    @FXML
    void ouvrirTierlistPressEvent(){
        MyTierLists.focus(MyTierLists.getTierlist(tierlistName.getText()));
        System.out.println("ouvrirTierlistPressEvent");
        parentController.refresh();
    }

    @Override
    public void refresh() {
        setPreviewImage(MyTierLists.getTierlist(tierlistName.getText()).getPreviewImage());
    }

    public void setPreviewImage(Image image) {
        previewImage.setImage(image);
    }

    public void setTierlistName(String name) {
        tierlistName.setText(name);
    }
}
