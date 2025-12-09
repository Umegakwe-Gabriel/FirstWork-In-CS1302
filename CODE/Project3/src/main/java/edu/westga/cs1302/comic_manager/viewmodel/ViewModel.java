package edu.westga.cs1302.comic_manager.viewmodel;

import edu.westga.cs1302.comic_manager.model.Comic;
import edu.westga.cs1302.comic_manager.model.Collection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.HashMap;
import java.util.Map;

/**
 * ViewModel class for the Comic Manager system.
 * 
 * THe viewModel acts as the intermediary between the View (UI) and the Model
 * (data). It holds the business logic, including the methods for
 * adding/removing comics from the collection.
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
	private StringProperty searchCriteria;

	private Map<String, Collection> collectionsByName;
	private Map<String, Comic> comicsByTitle;
	private Map<Integer, Comic> comicByIssue;

	/**
	 * Constructor for the ViewModel. Initializes all properties and sets up the
	 * maps to store collections and comics.
	 */
	public ViewModel() {
		this.collectionName = new SimpleStringProperty("");
		this.collections = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.collectionsByName = new HashMap<>();
		this.comicTitle = new SimpleStringProperty("");
		this.comicIssueNumber = new SimpleIntegerProperty(0);
		this.comics = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.comicsByTitle = new HashMap<>();
		this.comicByIssue = new HashMap<>();
		this.searchCriteria = new SimpleStringProperty("");
	}

	/**
	 * The comic title property, bound to a text field for input
	 * 
	 * @return the comic title.
	 */
	public StringProperty comicTitleProperty() {
		return this.comicTitle;
	}

	/**
	 * The comic issue number property, bound to a text field for input
	 * 
	 * @return comic issue number.
	 */
	public IntegerProperty comicIssueNumberProperty() {
		return this.comicIssueNumber;
	}

	/**
	 * List property of comics. Holds the list of all comics in the current
	 * collection
	 * 
	 * @return comics
	 */
	public ListProperty<Comic> comicsProperty() {
		return this.comics;
	}

	/**
	 * Adds a new comic to the list of comics. Uses the data from comicTitle and
	 * comicIssueNumber properties.
	 */
	public void addComic() {
		String title = this.comicTitle.get();
		int issueNumber = this.comicIssueNumber.get();

		Comic newComic = new Comic(title, issueNumber);
		this.comics.add(newComic);
	}

	/**
	 * The collection name property, bound to a text field for input
	 * 
	 * @return the collection name.
	 */
	public StringProperty collectionNameProperty() {
		return this.collectionName;
	}

	/**
	 * List property of collections. Holds all the comic collections
	 * 
	 * @return collections.
	 */
	public ListProperty<Collection> collectionsProperty() {
		return this.collections;
	}

	/**
	 * Adds a new collection. Checks if the collection already exists by name.
	 * Throws an exception if a collection with the same name already exists.
	 */
	public void addCollection() {
		String nameValue = this.collectionName.get();
		if (this.collectionsByName.containsKey(nameValue)) {
			throw new IllegalArgumentException("Collection already exists");
		}
		Collection newCollection = new Collection();
		this.collections.add(newCollection);
		this.collectionsByName.put(nameValue, newCollection);
	}

	/**
	 * Removes the selected collection. Removes from both the list and the map for
	 * collections.
	 */
	public void removeCollection() {
		Collection selectedCollection = this.collections.get(0);
		this.collections.remove(selectedCollection);
		this.collectionsByName.remove(selectedCollection.getComics());
	}

	/**
	 * Finds a comic based on search criteria (either title or issue number). If the
	 * criteria is a valid title, it searches by title. If the criteria is a valid
	 * issue number, it searches by issue number. If the comic is found, it returns
	 * the comic's string representation. If no comic is found, it returns a "No
	 * comic found" message.
	 * 
	 * @return String saying "No comic found"
	 */
	public String findComic() {
	    String criteria = this.searchCriteria.get();
	    Comic foundComic = null;

	    // Validate criteria for either title or issue number
	    if (this.comicsByTitle.containsKey(criteria)) {
	        foundComic = this.comicsByTitle.get(criteria);
	    } else {
	        try {
	            int issueNumber = Integer.parseInt(criteria); 
	            foundComic = this.comicByIssue.get(issueNumber);
	        } catch (NumberFormatException error) {
	            // Handle invalid search criteria
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setContentText("Invalid search criteria: " + error.getMessage());
	            alert.showAndWait();
	            return "No comic found.";
	        }
	    }

	    // If found, return the comic's string representation, else show no comic found
	    if (foundComic != null) {
	        return foundComic.toString();
	    } else {
	        return "No comic found.";
	    }
	}
}
