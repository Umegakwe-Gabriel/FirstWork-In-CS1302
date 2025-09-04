package edu.westga.cs1302.lab2.test.viewbill_view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.view.BillView;

class TestGetText {
	@Test
	public void testNullBill() {
        BillView view = new BillView();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            view.getText(null);
        });
        assertEquals("bill must not be null.", exception.getMessage());
    }
	
	@Test
	public void testBillWithSingleItem() {
		Bill bill = new Bill();
		bill.addItem(new BillItem("Coffee", 10.0));
		BillView view = new BillView();
		
		String text = view.getText(bill);
		
		//Check sub total
		assertTrue(text.contains("SUBTOTAL - $10.0"));
		
		//Check tax and tip using constants
		double expectedTax = 10.0 * Bill.TAX_RATE;
		double expectedTip = 10.0 * Bill.TIP_RATE;
		double expectedTotal = 10.0 +expectedTax + expectedTip;
		
		assertTrue(text.contains("TAX - $" + expectedTax));
		assertTrue(text.contains("TIP - $" + expectedTip));
		assertTrue(text.contains("TOTAL - $" + expectedTotal));
	}

}
