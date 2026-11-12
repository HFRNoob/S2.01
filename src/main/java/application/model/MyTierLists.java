package application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MyTierLists implements Serializable {

	private static List<Tierlist> allTierLists = new ArrayList<>();
    private static Tierlist focusedTierList;
	
	public MyTierLists() {
		super();
		allTierLists = new ArrayList<>();
	}

	public static void addTierList(Tierlist newTierList) {
        for (Tierlist tierlistIterated : allTierLists) {
            if (tierlistIterated.getName().equals(newTierList.getName())) {
                return;
            }
        }
		allTierLists.add(newTierList);
        System.out.println(getAllTierLists());
	}

	public static List<Tierlist> getAllTierLists() {
		return allTierLists;
	}

    public static Tierlist getTierlist(String nom) {
        for (Tierlist tierlist : getAllTierLists()) {
            if (tierlist.getName().equals(nom)) {
                return tierlist;
            }
        }
        return null;
    }

    public static void removeTierList(Tierlist tierList) {
		allTierLists.remove(tierList);
	}

    public static void focus(Tierlist tierlist) {
        focusedTierList = tierlist;
    }
    public static void unfocus() {
        focusedTierList = null;
    }

    public static Tierlist getFocus() {
        return focusedTierList;
    }
}
