package application.controller;

import application.model.MyTierLists;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class tierCustomizationController implements Initializable {
    @FXML
    private Button upArrowButton;
    @FXML
    private Button downArrowButton;
    @FXML
    private TextField tierTextField;
    @FXML
    private Button okButton;
    @FXML
    private ColorPicker tierColorPicker;
    @FXML
    private Label warningText;

    private tierIndividuelController parent;

    public void setParentController(tierIndividuelController parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ///Affecte les images de flèches aux boutons pour faire monter et descendre les tiers
        Image imageArrow = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/arrowIcon.png")));
        ImageView upArrowView = new ImageView(imageArrow);
        ImageView downArrowView = new ImageView(imageArrow);
        upArrowView.setFitWidth(32);
        upArrowView.setFitHeight(32);
        upArrowView.setPreserveRatio(true);
        downArrowView.setFitWidth(32);
        downArrowView.setFitHeight(32);
        downArrowView.setRotate(180.0);
        downArrowView.setPreserveRatio(true);
        upArrowButton.setGraphic(upArrowView);
        downArrowButton.setGraphic(downArrowView);

        warningText.setOpacity(0.0);
    }

    public void setDefaults() {
        tierTextField.setText(parent.getTierLetter());
        Color currentColor = Color.web(MyTierLists.getFocus().getTierByLetter(parent.getTierLetter()).getCouleur());
        tierColorPicker.setValue(currentColor);
    }

    @FXML
    void okButtonPressEvent(ActionEvent event){
        String result = tierTextField.getText();
        if(result.isEmpty()){
            warningText.setText("/!\\ Vous ne pouvez pas laisser le nom du tier vide !");
            warningText.setOpacity(1.0);
        } else if (result.length() > 1) {
            warningText.setText("/!\\ Le nom du tier doit être composé d'un seul caractère !");
            warningText.setOpacity(1.0);

        } else{
            Color couleur = tierColorPicker.getValue();

            String hexColor = "#"+couleur.toString().substring(2, 8);
            MyTierLists.getFocus().getTierByLetter(parent.getTierLetter()).setCouleur(hexColor);

            MyTierLists.getFocus().getTierByLetter(parent.getTierLetter()).setLetter(result);

//            System.out.println(result);
            warningText.setOpacity(0.0);
            parent.refreshParent();
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void upArrowButtonPressEvent(ActionEvent event){
        //moveTierUp();
        MyTierLists.getFocus().MoveTierUp(MyTierLists.getFocus().getTierByLetter(parent.getTierLetter()));
        System.out.println("upArrowButtonPressEvent");
        parent.refreshParent();
    }

    @FXML
    void downArrowButtonPressEvent(ActionEvent event){
        //moveTierDown();
        MyTierLists.getFocus().MoveTierDown(MyTierLists.getFocus().getTierByLetter(parent.getTierLetter()));
        System.out.println("domwArrowButtonPressEvent");
        parent.refreshParent();
    }






}