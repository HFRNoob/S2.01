package application.controller;

import application.model.MyTierLists;
import application.model.Tier;
import application.model.Tierlist;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class tierlistController extends MainController implements Initializable {
    @FXML
    private Button saveButton;
    @FXML
    private Label tierlistName;
    @FXML
    private VBox tierlistContainer;
    @FXML
    private Button retourButton;

    private backgroundTierlistController parentController;

    public void setParentController(backgroundTierlistController parent) {
        this.parentController = parent;
    }

    public void chargerVue(Parent vue, Object controller) {

        if (controller instanceof tierIndividuelController c) {
            c.setParentController(this);
        }
        tierlistContainer.getChildren().add(vue);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        refresh();
        //String importedName = tierlist.getName();
        //if(importedName.isEmpty()){
        //      erreure jsp
        //}
        //else{
        //      tierlistName.setText(importedName);
        //}
    }

    public void afficherPopupQuitter() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL); // bloque la fenêtre principale
        popup.setTitle("Quitter");
        popup.setResizable(false);
        Button sauvegarderQuitter = new Button("Sauvegarder et quitter");
        Button quitterSansSauvegarder = new Button("Quitter sans sauvegarder");
        Button annuler = new Button("Annuler");
        sauvegarderQuitter.setOnAction(e -> {
            System.out.println("Sauvegarde...");
            popup.close();
            persistentModelManager.save(model);
            MyTierLists.unfocus();
            parentController.refresh();
        });
        quitterSansSauvegarder.setOnAction(e -> {
            popup.close();
            MyTierLists.unfocus();
            parentController.refresh();
        });
        annuler.setOnAction(e -> {
            popup.close();
        });
        Label message = new Label("Voulez-vous sauvegarder avant de quitter ?");
        VBox boutons = new VBox(10, sauvegarderQuitter, quitterSansSauvegarder, annuler);
        boutons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(20, message, boutons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        popup.setScene(new Scene(layout, 300, 180));
        popup.showAndWait();
    }

    @FXML
    void savePressEvent(ActionEvent event){
        persistentModelManager.save(model);
    }

    @FXML
    void retourButtonPressEvent(ActionEvent event){
        afficherPopupQuitter();
    }

    @FXML
    void resetTierlist(ActionEvent event) {
        MyTierLists.getFocus().resetTierlist();
    }



    @Override
    public void refresh() {
        System.out.println("Refreshing tierlist view");
        tierlistContainer.getChildren().clear();
        System.out.println(MyTierLists.getFocus().getTiers());
        tierlistName.setText(MyTierLists.getFocus().getName());

        if(MyTierLists.getFocus().getTiers().isEmpty()) {
            return;
        }
        for (Tier tier: MyTierLists.getFocus().getTiers()) {
//            System.out.println("Iterating tiers...");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/TierIndividuel.fxml"));
                Parent vue = loader.load();
                tierIndividuelController controller = loader.getController();
                controller.setTierColor(tier.getCouleur());
                controller.setTierLetter(tier.getLettre());
//                System.out.println(controller.getTierLabel().getStyle());
                chargerVue(vue, controller);
                controller.refresh();



            } catch (IOException e) {
                e.printStackTrace();
            }

//            System.out.println("refreshed");
        }
        // Liste des éléments
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/TierListChoix.fxml"));
            Parent vue = loader.load();
            tierlistChoixController controller = loader.getController();
            chargerVue(vue, controller);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
