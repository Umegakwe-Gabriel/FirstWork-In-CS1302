package edu.westga.cs1302.project1.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaskTest {

	@Test
	public void testValidTaskCreation() {
		Task task = new Task("Homework", "Math exercises", "High");
		
		assertEquals("Homework", task.getName());
		assertEquals("Math exercises", task.getDescription());
		assertEquals("High", task.getPrioirty());
	}
	
	@Test
	public void testNullNameThrowsException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task(null, "desc", "Meduim") );
	}
	
	@Test
	public void testBlankNameThrowsException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task("  ", "description", "Low"));
	}
	
	@Test
	public void testDescriptionCannotBeNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task("Homework", null, "Meduim") );
	}
	
	@Test
    void testSetDescriptionValid() {
        Task task = new Task("Homework", "Old description", "Low");
        task.setDescription("New description");
        assertEquals("New description", task.getDescription());
    }
	
	@Test
    void testSetDescriptionNull() {
        Task task = new Task("Homework", "Old description", "Low");
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
    }
	
	@Test
	public void testPriorityCannotBeNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task("Task", "description", null));
	}
	
	@Test
	public void testPriorityCannotBeBlank() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Task("Task2", "description1", " "));
	}
	
	@Test 
	void testToStringReturnsName() {
		Task task = new Task("Read Book", "Chapter 1", "Low");
		assertEquals("Read Book", task.toString());
	}
}
