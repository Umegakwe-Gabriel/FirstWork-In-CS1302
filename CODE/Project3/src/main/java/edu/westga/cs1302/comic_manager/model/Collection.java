package edu.westga.cs1302.comic_manager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a collection of comics in the system.
 * 
 * This class is used to store and manage all the comics added by the user.
 * It provides methods to add, remove, and retrieve comics from the collection.
 * 
 * @author CS 1032
 * @version FALL 2025
 */
public class Collection {
	private ObservableList<Comic> comics;
	
	/**
	 * Constructor for the Collection class.
	 * Initializes the comics lists to be an empty list.
	 */
	public Collection() {
		this.comics = FXCollections.observableArrayList();
	}
	
	/**
     * Retrieves all comics in the collection.
     * 
     * @return the list of comics in the collection
     */
	public ObservableList<Comic> getComics() {
		return this.comics;
	}
	
	/**
     * Adds a new comic to the collection.
     * 
     * @param comic the comic to be added to the collection
     */
	public void addComic(Comic comic) {
		this.comics.add(comic);
	}
	
	/**
     * Removes a comic from the collection.
     * 
     * @param comic the comic to be removed
     */
	public void removeComic(Comic comic) {
		this.comics.remove(comic);
	}
	
	/**
     * Checks if the collection contains the given comic.
     * 
     * @param comic the comic to check for
     * @return true if the comic is in the collection, false otherwise
     */
	public boolean contains(Comic comic) {
		return this.comics.contains(comic);
	}
	
}
