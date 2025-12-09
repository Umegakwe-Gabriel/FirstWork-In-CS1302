package edu.westga.cs1302.comic_manager.viewmodel;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.comic_manager.model.*;
import edu.westga.cs1302.comic_manager.model.Collection;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel class  for the Comic Manager system.
 * 
 * THe viewModel acts as the intermediary between the View (UI) and the Model (data).
 * It holds the business logic, including the methods for adding/removing comics from the collection.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class ViewModel {
//	private Collection collection;
//	private StringProperty title;
//	private StringProperty author;
//	private StringProperty genre;
	
	private StringProperty collectionName;
	private ListProperty<Collection> collections;
	private Map<String, Collection> collectionsByName;
	
	public ViewModel() {
		this.collectionName = new SimpleStringProperty("");
		this.collections = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.collectionsByName = new HashMap<>();
	}
	
	public StringProperty collectionNameProperty() {
		return collectionName;
	}
	
	public ListProperty<Collection> collectionsProperty(){
		return collections;
	}
	
	public void addCollection() {
		String nameValue = collectionName.get();
		if (collectionsByName.containsKey(nameValue)) {
			throw new IllegalArgumentException("Collection already exists");
		}
		Collection newCollection = new Collection(nameValue);
		collections.add(newCollection);
		collectionsByName.put(nameValue, newCollection);
	}
	
	public void removeCollection() {
		Collection selectedCollection = collections.get(0);
		collections.remove(selectedCollection);
		collectionsByName.remove(selectedCollection.getName());
	}
}
