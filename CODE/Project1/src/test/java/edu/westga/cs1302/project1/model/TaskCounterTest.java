package edu.westga.cs1302.project1.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TaskCounterTest {

	@Test
	void testCountByPrioirtyWithValidList() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task("Homework", "Math problems", 1));
		tasks.add(new Task("Chores", "Clean room", 2));
		tasks.add(new Task("Study", "Review notes", 1));
		
		int result = TaskCounter.countByPriority(tasks, 1);
		assertEquals(2, result);
	}
	
	@Test
	void testCountByPriorityReturnsZeroWhenNoMatches() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task("Homework", "Math problems", 2));
		
		int result = TaskCounter.countByPriority(tasks, 5);
		assertEquals(0, result);
	}
	
	@Test
	void testCountByPriorityWithEmptyList() {
		List<Task> tasks = new ArrayList<>();
		int result = TaskCounter.countByPriority(tasks, 3);
		assertEquals(0, result);
	}
	
	@Test
	void testCountByPriorityWithNullListThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			TaskCounter.countByPriority(null, 2);
		});
	}
	
	@Test
	void testCountByPriorityWithInvalidPriorityThrowsException() {
		List<Task> tasks = new ArrayList<>();
		assertThrows(IllegalArgumentException.class, () -> {
			TaskCounter.countByPriority(tasks, 6);
		});
	}

}
