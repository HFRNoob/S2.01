package application.controller;

import application.model.MyTierLists;

public interface PersistentModelManager {

	public void save(MyTierLists model);

	public MyTierLists load();

}