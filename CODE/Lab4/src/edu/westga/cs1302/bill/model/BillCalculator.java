package edu.westga.cs1302.bill.model;

/**
 * Utility class for calculating totals on bills.
 * Provides static methods for sub-total, tax, tip, and total
 * 
 * @author CS
 * @version Fall 2025
 */

public class BillCalculator {
	
	//Private constructor to prevent instantiation
	private BillCalculator() {
		
	}
	
	//Calculates the sub-total from the array of the BillItems objects
	public static double calculateSubtotal(BillItem[] items) {
		double subtotal = 0.0;
		for (BillItem item : items) {
			if(item != null) {
				subtotal += item.getAmount();
			}
		}
		return subtotal;
	}
}
