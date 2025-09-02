package edu.westga.cs1302.lab2.test.model.bill;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.lab2.model.Bill;

/**
 * Test class for Bill
 */

public class TestAddItem {

@Test
public void testNewBillIsEmpty() {
	Bill bill = new Bill();
	assertTrue(bill.getItems().isEmpty());
}

}
