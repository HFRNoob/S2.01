package application;

import application.controller.backgroundTierlistController;

import application.service.persistence.PersistenceBySerialization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiStarter extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/BackgroundTierlist.fxml"));
            Parent root = loader.load();
            backgroundTierlistController myController = loader.getController();
            Scene scene = new Scene(root, 1600, 1000);
            primaryStage.setScene(scene);
            primaryStage.show();
            myController.setPersistentModelManager(new PersistenceBySerialization());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
