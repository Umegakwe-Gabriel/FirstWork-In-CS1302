package edu.westga.cs1302.comic_manager.model;

import java.util.ArrayList;
import java.util.List;

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
	private String name;
	private List<Comic> comics;
	
	public Collection(String name) {
		this.name = name;
		this.comics = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public List<Comic> getComics() {
		return comics;
	}
	
	public void addContact(Comic comic) {
		comics.add(comic);
	}
	
	public void removeComic(Comic comic) {
		comics.remove(comic);
	}
	
}
