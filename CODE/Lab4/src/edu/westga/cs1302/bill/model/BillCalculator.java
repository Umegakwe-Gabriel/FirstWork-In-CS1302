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
	
	/** 
	 * Calculates the sub-total of the bill items.
	 * 
	 * @param items the array of the BillItem objects
	 * @return the sub-total of the bill items.
	 */
	public static double calculateSubtotal(BillItem[] items) {
		double subtotal = 0.0;
		for (BillItem item : items) {
			if (item != null) {
				subtotal += item.getAmount();
			}
		}
		return subtotal;
	}
	
	/**
     * Calculates the tax amount from the array of BillItem objects.
     *
     * @param items the array of BillItem objects
     * @return the calculated tax
     */
	public static double calculateTax(BillItem[] items) {
		double subtotal = calculateSubtotal(items);
		return subtotal * Bill.TAX_RATE;
	}
	
	/**
     * Calculates the tip amount from the array of BillItem objects.
     *
     * @param items the array of BillItem objects
     * @return the calculated tip
     */
	public static double calculateTip(BillItem[] items) {
		double subTotal = calculateSubtotal(items);
		return subTotal * Bill.TIP_RATE;
	}
	
	/**
     * Calculates the total from the array of BillItem objects.
     *
     * @param items the array of BillItem objects
     * @return the calculated total
     */
	public static double calculateTotal(BillItem[] items) {
        double subtotal = calculateSubtotal(items);
        return subtotal + calculateTax(items) + calculateTip(items);
    }
}
