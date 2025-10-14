package edu.westga.cs1302.task_tracker.model.descending;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Descending;
import edu.westga.cs1302.task_tracker.model.Task;

/**
 * Test for Descending comparator
 * 
 * @author CS1302
 * @version Fall 2025
 */
class DescendingTest {

	@Test
	void testCompareHigherPriorityFirst() {
		Task high = new Task("Study", "Prepare for exam", Task.TaskPriority.HIGH);
		Task low = new Task("Clean", "Clean the room", Task.TaskPriority.LOW);
		
		Descending comparator = new Descending();
		assertTrue(comparator.compare(high, low) < 0, "HIGH should come before LOW");
	}
	
	@Test
	void testCompareEqualPriority() {
		Task task1 = new Task("Laundry", "Wash clothes", Task.TaskPriority.MEDIUM);
		Task task2 = new Task("Cooking", "Prepare lunch", Task.TaskPriority.MEDIUM);
		
		Descending comparator = new Descending();
		assertEquals(0, comparator.compare(task1, task2), "Equal priorities should return 0");
	}

	@Test
	void testCompareNullTaskThrowsException() {
		Descending comparator = new Descending();
		Task task = new Task("Excercise", "Morning workout", Task.TaskPriority.LOW);
		
		assertThrows(IllegalArgumentException.class, () -> {
			comparator.compare(null, task);
		});
	}
	
	@Test
	void testSortingListDescending() {
		Task high = new Task("Study", "Math homework", Task.TaskPriority.HIGH);
		Task meduim = new Task("Dishes", "Wash Plates", Task.TaskPriority.MEDIUM);
		Task low = new Task("Relax", "Watch TV", Task.TaskPriority.LOW);
		
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(low);
		tasks.add(meduim);
		tasks.add(high);
		
		Collections.sort(tasks, new Descending());
		
		assertEquals(high, tasks.get(0), "HIGH should be first");
		assertEquals(low, tasks.get(2), "LOW should be last");
	}
}
