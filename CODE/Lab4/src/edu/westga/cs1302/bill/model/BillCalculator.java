package edu.westga.cs1302.bill.model;

/**
 * Utility class for calculating totals on bills.
 * Provides static methods for sub-total, tax, tip, and total
 * 
 * @author CS
 * @version Fall 2025
 */

public final class BillCalculator {
	
	//Private constructor to prevent instantiation
	private BillCalculator() {
		
	}
	
	//Calculates the sub-total from the array of the BillItems objects
	public static double calculateSubtotal(BillItem[] items) {
		double subtotal = 0.0;
		for (BillItem item : items) {
			if (item != null) {
				subtotal += item.getAmount();
			}
		}
		return subtotal;
	}
	
	// Calculates the tax amount from the array of BillItem objects.	`
	public static double calculateTax(BillItem[] items) {
		double subtotal = calculateSubtotal(items);
		return subtotal * Bill.TAX_RATE;
	}
	
	//Calculate the tip amount from the array of BillItems objects
	public static double calculateTip(BillItem[] items) {
		double subTotal = calculateSubtotal(items);
		return subTotal * Bill.TIP_RATE;
	}
	
	// Calculates the total from the array of BillItem objects.
    public static double calculateTotal(BillItem[] items) {
        double subtotal = calculateSubtotal(items);
        return subtotal + calculateTax(items) + calculateTip(items);
    }
}
