package application.model;

import javafx.scene.image.Image;

public class Item{

    public static final String IMAGE_URL = "https://www.startpage.com/av/proxy-image?piurl=https%3A%2F%2Flogocreator.io%2Fwp-content%2Fuploads%2F2023%2F11%2Fmc_case_cover-scaled.jpg&sp=1780425110T6def1ef6be1ed649a03ecf89e5eea53bebb830cd2c84bc13ad4b136edec88557";
    private Image image;
    private int idx;
    private String name;

    public String getName() {
        return name;
    }


    public Item(String name) {

        this(name, new Image(IMAGE_URL));
    }
    public Item(String name, Image image){
        this.idx = -1;
        this.image = image;
        this.name = name;
    }


    public Image getImage() {
        return image;
    }

    public void setImage(String imageURL){
        Image newImage = new Image(imageURL);
        image = newImage;
    }

    public int getIndice(){
        return idx;
    }

    public void setIndice(int idx ){
        this.idx = idx;
    }
}
