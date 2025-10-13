package edu.westga.cs1302.task_tracker.model.ascending;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Task;
//import javafx.scene.layout.Priority;

/**
 * Test for Ascending comparator.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class AscendingTest {

	@Test
	void testCompareLowerPriorityFirst() {
		Task task1 = new Task("Clean", "Clean the room", Task.TaskPriority.LOW);
		Task task2 = new Task("Study", "Prepare for exam", Task.TaskPriority.HIGH);
		
		Ascending comparator = new Ascending();
		assertTrue(comparator.compare(task2, task1) < 0, "LOW should come before HIGH");
	}
	
	@Test
	void testCompareEqualPriority() {
		Task task1 = new Task("Laundry", "Wash clothes", Task.TaskPriority.HIGH);
		Task task2 = new Task("Cooking", "Prepare lunch", Task.TaskPriority.HIGH);
		
		Ascending comparator = new Ascending();
		assertEquals(0, comparator.compare(task1, task2), "Equal priorities should return to 0");
	}
	
	@Test
	void testCompareNullTaskThrowsException() {
		Ascending comparator = new Ascending();
		Task task = new Task("Excerise", "Morning workout", Task.TaskPriority.LOW);
		
		assertThrows(IllegalArgumentException.class, () ->{
			comparator.compare(task, null);
		});
	}
	
	@Test
	void testSortingListAscending() {
		Task high = new Task("Study", "Math homework", Task.TaskPriority.HIGH);
		Task meduim = new Task("Dishes", "Wash Plates", Task.TaskPriority.MEDIUM);
		Task low = new Task("Relax", "Watch TV", Task.TaskPriority.LOW);
		
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(high);
		tasks.add(meduim);
		tasks.add(low);
		
		Collections.sort(tasks, new Ascending());
		
		assertEquals(low, tasks.get(2), "LOW should be first");
		assertEquals(high, tasks.get(0), "HIGH should be last");
	}

}
