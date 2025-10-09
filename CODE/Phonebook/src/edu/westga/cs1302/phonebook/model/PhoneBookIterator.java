package edu.westga.cs1302.phonebook.model;

import java.util.Iterator;

public class PhoneBookIterator implements Iterator<Contact>{
	private int currentIndex;
	private Phonebook phonebook;
	
	public PhoneBookIterator(Phonebook phonebook) {
		if (phonebook == null) {
			throw new IllegalArgumentException();
			
		}
		this.phonebook = phonebook;
		this.currentIndex = 0;
	}
	
	@Override
	public boolean hasNext() {
		return this.currentIndex < this.phonebook.getContacts().size();
	}
	
	@Override
	public Contact next() {
		Contact nextContact = this.phonebook.getContacts().get(this.currentIndex);
		this.currentIndex++;
		return nextContact;
	}
}
