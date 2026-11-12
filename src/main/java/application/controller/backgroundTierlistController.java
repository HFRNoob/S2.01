package application.controller;

import application.model.MyTierLists;
import application.model.Tierlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class backgroundTierlistController extends MainController {
    @FXML
    private VBox mainContainer;
    @FXML
    private ImageView SettingButton;

    private backgroundTierlistController parentController;

    public void setParentController(backgroundTierlistController parent) {
        this.parentController = parent;
    }

    public backgroundTierlistController() throws IOException {
        super();
    }

    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/home.fxml"));
            Parent homeNode = loader.load();

            // Passe la référence du controller parent
            homeController controller = loader.getController();
            controller.setParentController(this);

            mainContainer.getChildren().add(homeNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerVue(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/" + fxmlFile));
            Parent vue = loader.load();

            Object controller = loader.getController();
            if (controller instanceof homeController c) {
                c.setParentController(this);
            }
            else if (controller instanceof creerNewTierlistController c) {
                c.setParentController(this);
            }
            else if (controller instanceof backgroundTierlistController c) {
                c.setParentController(this);
            }
            else if (controller instanceof tierlistController c) {
                c.setParentController(this);
            }

            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(vue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void settingButtonPressEvent(ActionEvent event) {
        System.out.println("settingButtonPressEvent");
    }

    @Override
    public void refresh() {
        mainContainer.getChildren().clear();
        if(MyTierLists.getFocus() == null) {
            chargerVue("home.fxml");
        }

        chargerVue("Tierlist.fxml");


    }
}
