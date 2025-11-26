package edu.westga.cs1302.contact_manager.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.contact_manager.model.Contact;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	private Map<String, Contact> contactByPhone;

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
		this.contactByPhone = new HashMap<String, Contact>();
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
		if (Contact.checkPhoneNumber(phoneValue)) {
			throw new IllegalArgumentException("phone number isnot valid");
		}

		if (this.contactsByName.containsKey(nameValue)) {
			throw new IllegalArgumentException("A contact with this name already exists: " + nameValue);
		}

		if (this.contactByPhone.containsKey(phoneValue)) {
			throw new IllegalArgumentException("A contact with this phone number already exists: " + phoneValue);
		}
		
		Contact newContact = new Contact(nameValue, phoneValue);
		
		this.contacts.add(newContact);
		this.contactsByName.put(nameValue, newContact);
		this.contactByPhone.put(phoneValue, newContact);
	}

	/**
	 * Finds a contact with name or phone number matches provide search criteria
	 * 
	 * @precondition none
	 * @postcondition getResultContact().get() is set to the appropriate contact (if
	 *                contact found) OR null (if no contact found)
	 * 
	 * @return A string representation of the contact found.
	 */
	public String findContact() {
		if (!Contact.checkName(this.searchCriteria.get()) && !Contact.checkPhoneNumber(this.searchCriteria.get())) {
			throw new IllegalArgumentException("Search criteria is not a valid name or phone number");
		}
		for (Contact currContact : this.contacts.get()) {
			if (currContact.getName().equals(this.searchCriteria.get())
					|| currContact.getPhoneNumber().equals(this.searchCriteria.get())) {
				return currContact.toString();
			}
		}
		return "No contact found.";
	}

}
