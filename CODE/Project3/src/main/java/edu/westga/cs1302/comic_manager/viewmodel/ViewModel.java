package edu.westga.cs1302.comic_manager.viewmodel;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.comic_manager.model.*;
import edu.westga.cs1302.comic_manager.model.Collection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
	private StringProperty comicTitle;
	private IntegerProperty comicIssueNumber;
	private ListProperty<Comic> comics;
	private StringProperty collectionName;
	private ListProperty<Collection> collections;
	private Map<String, Collection> collectionsByName;
	private Map<String, Comic> comicsByTitle;
	private Map<Integer, Comic> comicByIssue;
	
	public ViewModel() {
		this.collectionName = new SimpleStringProperty("");
		this.collections = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.collectionsByName = new HashMap<>();
		this.comicTitle = new SimpleStringProperty("");
		this.comicIssueNumber = new SimpleIntegerProperty(0);
		this.comics = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.comicsByTitle = new HashMap<>();
		this.comicByIssue = new HashMap<>();
	}
	
	public StringProperty comicTitleProperty() {
		return comicTitle;
	}
	
	public IntegerProperty comicIssueNumberProperty() {
		return comicIssueNumber;
	}
	
	public ListProperty<Comic> comicsProperty() {
		return comics;
	}
	
	public void addComic() {
		String title = comicTitle.get();
		int issueNumber = comicIssueNumber.get();
		
		Comic newComic = new Comic(title, issueNumber);
		comics.add(newComic);
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
	
	public String findComic() {
		String criteria = searchField.getText();
		Comic foundComic = comicsByTitle.get(criteria);
		if(foundComic == null) {
			try {
				int issueNumber = Integer.parseInt(criteria);
				foundComic = comicsByIssue.get(issueNumber);
			} catch (NumberFormatExcpetion e) {
			
			}
		}
		
		if (foundComic != null) {
			return foundComic.toString();
		} else {
			return "No comic found.";
		}
	}
}
