package application.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

    @Test
    void createTierlist() {
        Assertions.assertDoesNotThrow(() -> {
                MyTierLists myTierLists = new MyTierLists();
                Tierlist tierlist = new Tierlist("Tierlist test");
                myTierLists.addTierList(tierlist);
        });

    }
    @Test
    void createTierlist2() {
            MyTierLists myTierLists = new MyTierLists();
            Tierlist tierlist = new Tierlist("Tierlist test");
            myTierLists.addTierList(tierlist);
        Assertions.assertEquals(myTierLists.getAllTierLists().get(0).getName(), "Tierlist test");

    }
    @Test
    void deleteTierlist() {
        Assertions.assertDoesNotThrow(() -> {
            MyTierLists myTierLists = new MyTierLists();
            Tierlist tierlist = new Tierlist("Tierlist test");
            myTierLists.addTierList(tierlist);
            myTierLists.removeTierList(tierlist);
        });

    }


    @Test
    void moveTierDownBasic() {
        Assertions.assertDoesNotThrow(() -> {
            Tierlist tierlist = new Tierlist("Tierlist test");
            tierlist.MoveTierDown(tierlist.getTierAtIdx(2));
        });
    }

    @Test
    void moveTierDownOrder() {
        Tierlist tierlist = new Tierlist("Tierlist test");
        tierlist.MoveTierDown(tierlist.getTierAtIdx(2));

        List<String> listeAttendue = new ArrayList<>();
        List<String> listeReelle = new ArrayList<>();

        listeAttendue.add("S");
        listeAttendue.add("A");
        listeAttendue.add("C");
        listeAttendue.add("B");
        listeAttendue.add("D");

        for (Tier tier : tierlist.getTiers()){
            listeReelle.add(tier.getLettre());
        }
        Assertions.assertEquals(listeAttendue, listeReelle);
    }

    @Test
    void moveTierDownEdgeBottom() {
        Assertions.assertDoesNotThrow(() -> {
            Tierlist tierlist = new Tierlist("Tierlist test");
            tierlist.MoveTierDown(tierlist.getTierAtIdx(tierlist.getTiers().size()-1));
        });
    }
    @Test
    void moveTierDownEdgeBottomOrder() {
        Tierlist tierlist = new Tierlist("Tierlist test");
        tierlist.MoveTierDown(tierlist.getTierAtIdx(tierlist.getTiers().size()-1));

        List<String> listeAttendue = new ArrayList<>();
        List<String> listeReelle = new ArrayList<>();

        listeAttendue.add("S");
        listeAttendue.add("A");
        listeAttendue.add("B");
        listeAttendue.add("C");
        listeAttendue.add("D");

        for (Tier tier : tierlist.getTiers()){
            listeReelle.add(tier.getLettre());
        }
        Assertions.assertEquals(listeAttendue, listeReelle);
    }

    @Test
    void moveTierUpBasic() {
        Assertions.assertDoesNotThrow(() -> {
            Tierlist tierlist = new Tierlist("Tierlist test");
            tierlist.MoveTierUp(tierlist.getTierAtIdx(2));
        });
    }
    @Test
    void moveTierUpOrder() {
        Tierlist tierlist = new Tierlist("Tierlist test");
        tierlist.MoveTierUp(tierlist.getTierAtIdx(2));

        List<String> listeAttendue = new ArrayList<>();
        List<String> listeReelle = new ArrayList<>();

        listeAttendue.add("S");
        listeAttendue.add("B");
        listeAttendue.add("A");
        listeAttendue.add("C");
        listeAttendue.add("D");

        for (Tier tier : tierlist.getTiers()){
            listeReelle.add(tier.getLettre());
        }
        Assertions.assertEquals(listeAttendue, listeReelle);
    }

    @Test
    void moveTierUpEdgeTop() {
        Assertions.assertDoesNotThrow(() -> {
            Tierlist tierlist = new Tierlist("Tierlist test");
            tierlist.MoveTierUp(tierlist.getTierAtIdx(0));
        });
    }
    @Test
    void moveTierUpEdgeTopOrder() {
        Tierlist tierlist = new Tierlist("Tierlist test");
        tierlist.MoveTierUp(tierlist.getTierAtIdx(0));

        List<String> listeAttendue = new ArrayList<>();
        List<String> listeReelle = new ArrayList<>();

        listeAttendue.add("S");
        listeAttendue.add("A");
        listeAttendue.add("B");
        listeAttendue.add("C");
        listeAttendue.add("D");

        for (Tier tier : tierlist.getTiers()){
            listeReelle.add(tier.getLettre());
        }
        Assertions.assertEquals(listeAttendue, listeReelle);
    }

    @Test
    void addItem() {
        Assertions.assertDoesNotThrow(() -> {
            Tierlist tierlist = new Tierlist("Tierlist test");
            Item item = new Item();
            tierlist.AddItem(item);
        });
    }
    @Test
    void testIdemIdle() {
        Tierlist tierlist = new Tierlist("Tierlist test");
        Item item = new Item();
        tierlist.AddItem(item);
        Assertions.assertEquals(-1, item.getIndice());
    }

    @Test
    void testRankItem() {
        Tierlist tierlist = new Tierlist("Tierlist test");
        Item item = new Item();
        tierlist.AddItem(item);
        tierlist.RankItem(item, 0);

        Assertions.assertEquals(0, item.getIndice());
    }

}
