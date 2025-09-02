package edu.westga.cs1302.lab2.test.model.bill;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.lab2.model.*;
import java.util.ArrayList;

/**
 * Test class for Bill
 */

public class TestAddItem {

	@Test
	public void testNewBillIsEmpty() {
		Bill bill = new Bill();
		assertTrue(bill.getItems().isEmpty());
	}
	
	@Test
	public void testAddValidItem() {
		Bill bill = new Bill();
		BillItem item = new BillItem("Sandwich", 5.0);
		bill.addItem(item);
		
		ArrayList<BillItem> items = bill.getItems();
		assertEquals(1, items.size());
		assertEquals("Sandwich", items.get(0).getName());
		assertEquals(5.0, items.get(0).getAmount());
	}

//	@Test
//	public void testAddNullItem() {
//		Bill bill = new Bill();
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			bill.addItem(null);
//		});
//		assertEquals("items must not be null.", exception.getMessage());
//	}

	@Test
	public void testConstants() {
		assertEquals(0.10, Bill.TAX_RATE);
		assertEquals(0.20, Bill.TIP_RATE);
	}
	
}
