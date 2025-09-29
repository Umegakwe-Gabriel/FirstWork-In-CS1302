package edu.westga.cs1302.project1.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test class for Task.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TaskTest {

    @Test
    void testValidTaskCreation() {
        Task task = new Task("Homework", "Finish math problems", 3);
        assertEquals("Homework", task.getName());
        assertEquals("Finish math problems", task.getDescription());
        assertEquals(3, task.getPriority());
    }

    @Test
    void testInvalidTaskNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "Some description", 2);
        });
    }

    @Test
    void testInvalidTaskDescriptionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Chores", null, 2);
        });
    }

    @Test
    void testInvalidPriorityTooLowThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Chores", "Clean the house", 0);
        });
    }

    @Test
    void testInvalidPriorityTooHighThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Chores", "Clean the house", 6);
        });
    }

    @Test
    void testSetDescriptionUpdatesDescription() {
        Task task = new Task("Study", "Review notes", 2);
        task.setDescription("Review chapters 1-3");
        assertEquals("Review chapters 1-3", task.getDescription());
    }

    @Test
    void testSetDescriptionWithNullThrowsException() {
        Task task = new Task("Study", "Review notes", 2);
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
    }

    @Test
    void testToStringReturnsCorrectFormat() {
        Task task = new Task("Laundry", "Wash clothes", 1);
        assertEquals("Laundry (Priority: 1)", task.toString());
    }
}
