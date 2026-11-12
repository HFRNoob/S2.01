package application.controller;

import application.model.MyTierLists;
import application.service.api.APIManager;

public abstract class MainController{
    protected MyTierLists model;
    protected PersistentModelManager persistentModelManager;
    protected APIManager apiManager;

    public MainController(){
        apiManager = new APIManager();
    }
    public void setPersistentModelManager(PersistentModelManager persistentModelManager) {
        this.persistentModelManager = persistentModelManager;
        model = persistentModelManager.load();

    }


    public abstract void refresh();
}
