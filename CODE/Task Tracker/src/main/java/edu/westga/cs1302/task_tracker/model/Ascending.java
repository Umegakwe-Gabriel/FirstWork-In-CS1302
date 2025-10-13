package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Comparator that sorts tasks by ascending priority.
 * Lower priority values (LOW) comes before higher values (HIGH).
 * 
 *  @author CS 1302
 *  @version Fall 2025
 */
public class Ascending implements Comparator<Task> {
	
	/**
	 * Compares two tasks based on their priority in ascending order.
	 * 
	 * @precondition task1 != null && task2 != null
	 * @postcondition none
	 * 
	 * @param task1 the first task to compare
	 * @param task2 the second task to compare
	 * @return a negative integer if task1 < task2, 
	 *         zero if equal, 
	 *         a positive integer if task1 > task2
	 */
	
	@Override
	public int compare(Task task1, Task task2) {
		if (task1 == null || task2 == null) {
			throw new IllegalArgumentException("Tasks cannot be null");
		}
		return task1.getPriority().compareTo(task2.getPriority());
	}
	
}
