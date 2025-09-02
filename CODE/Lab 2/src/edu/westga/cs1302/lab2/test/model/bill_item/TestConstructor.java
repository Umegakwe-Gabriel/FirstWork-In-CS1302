package edu.westga.cs1302.lab2.test.model.bill_item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.BillItem;

public class TestConstructor {

	@Test
	public void testValidBillItem() {
		BillItem item = new BillItem("Coffee", 2.50);
		assertEquals("Coffee", item.getName());
		assertEquals(2.50, item.getAmount());
	}

	@Test
	public void testNullNameThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new BillItem(null, 2.50);
		});
		assertEquals("name must not be null.", exception.getMessage());
	}

	@Test
	public void testNegativeAmountThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new BillItem("Coffee", -5.0);
		});
		assertTrue(exception.getMessage().contains("amount must be positive"));
	}

//	@Test
//	public void testZeroAmountThrowsException() {
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			new BillItem("Coffee", 0);
//		});
//		assert
//	}
}
