package application.controller;

import application.model.MyTierLists;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class itemController {
    @FXML
    private ImageView item;

    private String itemName;


    public void setImage(Image image){
        item.setImage(image);
    }
    @FXML
    public void selectItem() {
        System.out.println("SELECTED ITEM : " + itemName);
        MyTierLists.getFocus().selectItem(MyTierLists.getFocus().getItemsByName(itemName));
        // TODO
    }
}
