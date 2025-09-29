package edu.westga.cs1302.project1.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskManagerTest {

	@Test
	void testNewManagerIsEmpty() {
		TaskManager manager = new TaskManager();
		assertTrue(manager.getTasks().isEmpty());
	}
	
	@Test
	void testAddValidTask() {
		TaskManager manager = new TaskManager();
		Task task = new Task("Homework", "Finish math problmes", 3);
		manager.addTask(task);
		assertTrue(manager.getTasks().contains(task));
	}
	
	@Test 
	void testAddNullTaskThrowsException() {
		TaskManager manager = new TaskManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addTask(null);
		});
	}
	
	@Test
	void testRemoveTask() {
		TaskManager manager = new TaskManager();
		Task task = new Task("Chores", "Clean kitchen", 2);
		manager.addTask(task);
		manager.removeTask(task);
		assertFalse(manager.getTasks().contains(task));
	}
	
	@Test
	void testRemoveNullTaskThrowsException() {
		TaskManager manager = new TaskManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.removeTask(null);
		});
	}
}
