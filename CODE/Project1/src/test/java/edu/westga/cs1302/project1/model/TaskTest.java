package edu.westga.cs1302.project1.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaskTest {

	@Test
	public void testValidTaskCreation() {
		Task task = new Task("Homework", "Math exercises", 3);
		
		assertEquals("Homework", task.getName());
		assertEquals("Math exercises", task.getDescription());
		assertEquals(3, task.getPrioirty());
	}
	
	@Test
	public void testNullNameThrowsException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(null, "desc", 2) );
	}
	
	@Test
	public void testBlankNameThrowsException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task("  ", "description", 3));
	}
	
	@Test
	public void testInvalidPriorityThrowsException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task("Task", "description", 0));
		
		assertThrows(IllegalArgumentException.class, 
				() -> new Task("Task2", "description1", 6));
	}
	
	@Test 
	void testToStringReturnsName() {
		Task task = new Task("Read Book", "Chapter 1", 2);
		assertEquals("Read Book", task.toString());
	}
}
