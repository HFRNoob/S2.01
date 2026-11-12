package application.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tierlist implements Serializable {
    public static final String PREVIEW_PATH = "/Assets/logo.png";
    private List<Tier> tiers; // Liste triée des tiers, bas de la liste (indice 0) = tier le plus haut
    private List<Item> items;
    private Image previewImage;
    private String name;
    private Item selectedItem;
    private Tier releasedTier;

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }


    public Tier getReleasedTier() {
        return releasedTier;
    }

    public void setReleasedTier(Tier releasedTier) {
        this.releasedTier = releasedTier;
    }


    public List<Item> getItems() {
        return items;
    }


    public Image getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(Image image) {
        previewImage = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }


    public Tierlist(String name) {
        this (name, new Image(PREVIEW_PATH));
    }

    public Tierlist(String name, Image previewImage) {
        this.name = name;
        this.previewImage = previewImage;
        this.tiers = new ArrayList<Tier>();
        this.items = new ArrayList<Item>();

        addDefaultTiers();



    }

    private void addDefaultTiers() {
        // tiers par défaut d'une tierliste vide :
        this.tiers.add(new Tier("S", "#F52727"));
        this.tiers.add(new Tier("A", "#F57D27"));
        this.tiers.add(new Tier("B", "#F5C227"));
        this.tiers.add(new Tier("C", "#8BF527"));
        this.tiers.add(new Tier("D", "#27F5F5"));

        Item defaultItem = new Item("placeholder");
        AddItem(defaultItem);

    }

    public void MoveTierDown(Tier tier){
        int idx = tiers.indexOf(tier);

        if(idx == tiers.size()-1){
            return;
        }
        Tier tierSup = tiers.get(idx+1);
        tiers.set(idx, tierSup);
        tiers.set(idx+1, tier);
    }

    public void MoveTierUp(Tier tier){
        int idx = tiers.indexOf(tier);

        if(idx == 0){
            return;
        }
        Tier tierInf = tiers.get(idx-1);
        tiers.set(idx, tierInf);
        tiers.set(idx-1, tier);
    }

    public void resetTierlist() {
        tiers.clear();
        items.clear();
        addDefaultTiers();
    }

    public List<Tier> getTiers() {
        return tiers;
    }

    public Tier getTierAtIdx(int idx) {
        return getTiers().get(idx);
    }

    public void AddItem(Item item){
        for (Item itemIterated : items) {
            if (itemIterated.getName().equals(item.getName())) {
                return;
            }
        }
        items.add(item);
    }

    public void DelItem(Item item){
        items.remove(item);
    }

    public void AddTier(Tier tier){

        for (Tier tierIterated : tiers) {
            if (tierIterated.getLettre().equals(tier.getLettre())) {
                return;
            }
        }
        tiers.add(tier);
    }
    public void DelTier(Tier tier){
        if(tiers.size() == 1) {
            return;
        }
        tiers.remove(tier);
    }

    public Tier getTierByLetter(String letter) {
        for (Tier tier : getTiers()) {
            if (tier.getLettre().equals(letter)) {
                return tier;
            }
        }
        return null;
    }


    public void selectItem(Item item) {
        if (items.contains(item)) {
            selectedItem = item;
        }
    }
    public void RankItem(Item item, int idx){
        // Item doit se trouver dans la liste d'items
        // Grace au controller il est normalement impossible de déplacer un item non présent
        item.setIndice(idx);


    }

    public Item getItemsByName(String itemName) {
        for (Item item : getItems()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
}
