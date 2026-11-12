package application.controller;

import application.model.MyTierLists;
import application.model.Tierlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController extends MainController{
    @FXML
    private Button creerButton;
    @FXML
    private Button importerButton;
    @FXML
    private FlowPane homeContainer;

    private backgroundTierlistController parentController;

    public void setParentController(backgroundTierlistController parent) {
        this.parentController = parent;
    }

    @FXML
    public void initialize(){
        refresh();
    }

    public void chargerVue(Parent vue, Object controller) {

        if (controller instanceof previewTierlistController c) {
            c.setParentController(this);
        }
        homeContainer.getChildren().add(vue);

    }

    @FXML
    void creerButtonPressEvent(ActionEvent event){
        System.out.println("creerButtonPressEvent");
        parentController.chargerVue("creerNewTierList.fxml");
        refresh();
    }

    @FXML
    void importerButtonPressEvent(ActionEvent event){
        System.out.println("importerButtonPressEvent");
        refresh();
        //TODO ouvrir un explorateur de fichier, selectionner un fichier, verifier si c'est une tierlist,
        //tu peut récupérer le code et l'adapter a ton besoin dans previewTierlistController
        //TODO Si non -> erreur ou jsp quoi, Si oui -> ouvrir cette table
    }

    @Override
    public void refresh() {
        System.out.println("Refreshing");
        homeContainer.getChildren().clear();
        System.out.println(MyTierLists.getAllTierLists());
        // Tester si uen tierlist est focus, si oui, demander au parent de changer la page
        if(!(MyTierLists.getFocus() == null)) {
            parentController.refresh();
        }

        //
        if(MyTierLists.getAllTierLists().size() == 0) {
            return;
        }
        for (Tierlist tierlist: MyTierLists.getAllTierLists()) {
            System.out.println("Iterating tierlists...");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/previewTierlist.fxml"));
                Parent vue = loader.load();
                previewTierlistController controller = loader.getController();
                controller.setPreviewImage(tierlist.getPreviewImage());
                controller.setTierlistName((tierlist.getName()));

                chargerVue(vue, controller);

            } catch (IOException e) {
                e.printStackTrace();
            }

            // TODO : afficher les tierlist dans la page d'accueil
            // tierlist.getName() pour le nom de la tierlist
            // tierlist.getPreviewImage() pour l'image de preview de la tierlist
            System.out.println("refreshed");
        }

    }
}
