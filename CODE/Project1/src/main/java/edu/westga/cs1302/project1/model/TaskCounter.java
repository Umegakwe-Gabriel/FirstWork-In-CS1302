package edu.westga.cs1302.project1.model;

import java.util.List;

/**
 * Utility class for counting tasks by priority.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TaskCounter {
	
	private TaskCounter() {
		
	}
	
	/**
	 * Counts the number of tasks by priority.
	 * 
	 * @param tasks the lists of tasks
	 * @param priority the priority level to count
	 * @param the number of tasks with the given priority
	 * 
	 * @precondition tasks != null && priority between 1 and 5
	 * @postcondition none
	 */
	public static int countByPriority(List<Task> tasks, int priority) {
		if (tasks == null) {
			throw new IllegalArgumentException("Tasks list cannot be null");
		}
		if (priority < 1 || priority > 5) {
			throw new IllegalArgumentException("Prioirity must be between 1 and 5");
		}
		
		int count = 0;
		for (Task task : tasks) {
			if (task.getPriority() == priority) {
				count++;
			}
		}
		return count;
	}
	
}
