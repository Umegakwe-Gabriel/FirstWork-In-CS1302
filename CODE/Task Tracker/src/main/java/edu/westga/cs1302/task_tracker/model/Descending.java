package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Comparator for sorting tasks in descending order of priority (HIGH -> MEDUIM -> LOW).
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Descending implements Comparator<Task> {
	
	@Override
	public int compare(Task first, Task second) {
		if (first == null || second == null) {
			throw new IllegalArgumentException("Task to compare must not be null");
		}
		
		return second.getPriority().compareTo(first.getPriority());
	}
}
