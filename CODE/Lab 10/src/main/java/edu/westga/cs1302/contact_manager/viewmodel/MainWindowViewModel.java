package edu.westga.cs1302.contact_manager.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.contact_manager.model.Contact;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * View model for the MainWindow view
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindowViewModel {
	private StringProperty name;
	private StringProperty phoneNumber;
	private StringProperty searchCriteria;
	private ListProperty<Contact> contacts;

	private Map<String, Contact> contactsByName;
	private Map<String, Contact> contactsByPhone;

	/**
	 * Initialize the MainWindowViewModel
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public MainWindowViewModel() {
		this.name = new SimpleStringProperty("");
		this.phoneNumber = new SimpleStringProperty("");
		this.searchCriteria = new SimpleStringProperty("");
		this.contacts = new SimpleListProperty<Contact>(FXCollections.observableList(new ArrayList<Contact>()));
		this.contactsByName = new HashMap<String, Contact>();
		this.contactsByPhone = new HashMap<String, Contact>();
	}

	/**
	 * Return the name property used when adding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name property used when adding a contact
	 */
	public StringProperty getName() {
		return this.name;
	}

	/**
	 * Return the phone number property used when adding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the phone number property used when adding a contact
	 */
	public StringProperty getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Return the search criteria property used when finding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the search criteria property used when finding a contact
	 */
	public StringProperty getSearchCriteria() {
		return this.searchCriteria;
	}

	/**
	 * Return the list property containing all contacts added to the system
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list property containing all contacts added to the system
	 */
	public ListProperty<Contact> getContacts() {
		return this.contacts;
	}

	/**
	 * Adds a new contact with name and phone number set by the appropriate property
	 * 
	 * @precondition none
	 * @postcondition a new contact with name and phone number provided has been
	 *                added if valid and not a duplicate.
	 * 
	 * @throws IllegalArgumentException if either name or phone number are invalid
	 *                                  (see Contact class) or if a duplicate name
	 *                                  or phone number already exists.
	 */
	public void addContact() throws IllegalArgumentException {
		String nameValue = this.name.get();
		String phoneValue = this.phoneNumber.get();

		if (!Contact.checkName(nameValue)) {
			throw new IllegalArgumentException("name is not valid");
		}
		if (!Contact.checkPhoneNumber(phoneValue)) {
			throw new IllegalArgumentException("phone number is not valid");
		}

		if (this.contactsByName.containsKey(nameValue)) {
			throw new IllegalArgumentException("name already exists");
		}

		if (this.contactsByPhone.containsKey(phoneValue)) {
			throw new IllegalArgumentException("phone number already exists");
		}
		
		Contact newContact = new Contact(nameValue, phoneValue);
		
		this.contacts.add(newContact);
		this.contactsByName.put(nameValue, newContact);
		this.contactsByPhone.put(phoneValue, newContact);
	}

	/**
	 * Finds a contact with name or phone number matches provide search criteria
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return A string representation of the contact found,
	 * 			or, "No contact found." if none exists.
	 * 
	 * @throws IllegalArgumentException if the search criteria is not a valid name 
	 * 									or phone number.
	 */
	public String findContact() {
		String criteria = this.searchCriteria.get();
		
		boolean validName = Contact.checkName(criteria);
		boolean validPhone = Contact.checkPhoneNumber(criteria);
		
		if (!validName && !validPhone) {
			throw new IllegalArgumentException("Search criteria is not a valid name or phone number");
		}
		
		Contact found = null;
		
		if (validName) {
			found = this.contactsByName.get(criteria);
		} else if (validPhone) {
			found = this.contactsByPhone.get(criteria);
		}
		
		if (found != null) {
			return found.toString();
		}
		return "No contact found.";
	}

}
