package application.controller;

import application.model.Item;
import application.model.MyTierLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class tierIndividuelController extends MainController {
    @FXML
    private Label tierName;
    @FXML
    private Pane tierPane;
    @FXML
    private Button tierButton;
    private tierlistController parentController;

    public void setParentController(tierlistController parent) {
        this.parentController = parent;
    }

    @FXML
    private void ouvrirPagePressEvent(ActionEvent event) throws IOException {
        ///Ouvre le fxml du menu de configuration des tiers
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/tierCustomization.fxml"));
        Parent popup = loader.load();
        tierCustomizationController controller = loader.getController();
        controller.setParentController(this);
        controller.setDefaults();

        Stage popupStage = new Stage();
        popupStage.setScene(new Scene(popup));
        popupStage.setResizable(false);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Personnalisation du tier "+getTierLetter());
        popupStage.showAndWait();

    }

    public void chargerVue(Parent vue, Object controller) {

        tierPane.getChildren().add(vue);

    }

    public void setTierLetter(String letter) {
        tierName.setText(letter);
    }
    public String getTierLetter() {
        return tierName.getText();
    }
    public void setTierColor(String hexColor) {
        tierName.setStyle("-fx-background-color: "+hexColor+"; -fx-font-size: 20;");
    }
    public Label getTierLabel() {
        return tierName;
    }
    @FXML
    public void setReleaseIndex() {
        System.out.println("RELEASE INDEX : "+getTierLetter());
        MyTierLists.getFocus().setReleasedTier(MyTierLists.getFocus().getTierByLetter(getTierLetter()));
    }

    public void refreshParent() {
        parentController.refresh();
    }


    @Override
    public void refresh() {
//        System.out.println("Refreshing items in tier "+getTierLetter());
        tierPane.getChildren().clear();
        for (Item item: MyTierLists.getFocus().getItems()) {
//            System.out.println("Iterating items...");
            if (item.getIndice() < 0) {
                continue;
            } else if(MyTierLists.getFocus().getTiers().get(item.getIndice()) == MyTierLists.getFocus().getTierByLetter(tierName.getText())) {

//                System.out.println("Found Item in tier...");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/item.fxml"));
                    Parent vue = loader.load();
                    itemController controller = loader.getController();
                    controller.setImage(item.getImage());
                    chargerVue(vue, controller);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
