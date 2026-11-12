package application.controller;

import application.model.Item;
import application.model.MyTierLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class tierlistChoixController extends MainController{
    @FXML
    private Button addButton;
    @FXML
    private FlowPane unsortedItemContainer;

    @FXML
    void addButtonPressEvent(ActionEvent event) throws IOException {
        System.out.println("addButtonPressEvent");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/importerItems.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
    }

    public void chargerVue(Parent vue, Object controller) {

        unsortedItemContainer.getChildren().add(vue);

    }

    @Override
    public void refresh() {
        unsortedItemContainer.getChildren().clear();
        for (Item item: MyTierLists.getFocus().getItems()) {
//            System.out.println("Iterating items...");
            if(item.getIndice() == -1) {
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
