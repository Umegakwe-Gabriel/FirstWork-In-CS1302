package edu.westga.cs1302.lab1.model;

import java.util.ArrayList;

/** Stores information for a bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Bill {
	// Constants for the tax and tip rates
	public static final double TIP_RATE = 0.20;
	public static final double TAX_RATE = 0.10;
	
	private ArrayList<BillItem> items;
	
	/** Create a new empty Bills
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public Bill() {
		this.items = new ArrayList<BillItem>();
	}
	
	/** Adds the item to the bill
	 * 
	 * @precondition item != null
	 * @postcondition item is added to the list of items in the bill
	 * 
	 * @param item the item to be added to the bill
	 */
	public void addItem(BillItem item) {
		if (item == null) {
			throw new IllegalArgumentException("item must not be null.");
		}
		this.items.add(item);
	}
	
	/**
	 * Get the list of bill items.
	 * 
	 * @return the list of items
	 */
	public ArrayList<BillItem> getItems() {
		return this.items;
	}

}
