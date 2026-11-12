package application.service.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import application.controller.PersistentModelManager;
import application.model.MyTierLists;

public class PersistenceBySerialization implements PersistentModelManager {

	private static final String SAVE_FILE = "saveFile";

	@Override
	public void save(MyTierLists model) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) 
		{
			objectOutputStream.writeObject(model);
			objectOutputStream.flush();
			System.out.println("SAVE OK");
		} catch (IOException e) {
			System.err.println("Saving file error");
		}
	}

	@Override
	public MyTierLists load() {
		MyTierLists model = null;
				
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(SAVE_FILE)))
		{
			model = (MyTierLists) input.readObject();
			System.out.println("LOAD OK");
		} catch (IOException e) {
			System.err.println("Save file does not exist");
			System.err.println("Creation of empty model");
			model = new MyTierLists();
		} catch (ClassNotFoundException e) {
			System.err.println("Loading save file error");
		}
		return model;
	}
	
}
