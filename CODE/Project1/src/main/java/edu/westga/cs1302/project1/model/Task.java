package edu.westga.cs1302.project1.model;

public class Task {
	private String name;
	private String description;
	private int priority; // e.g., 1 = low, 5 = high
	private boolean  completed;
	
	/**
	 * Constructs a Task with the specified name, description, and priority.
	 * 
	 * @param name the name of the task(cannot be null or blank)
	 * @param description the description of the task
	 * @param priority the priority of the task (1-5)
	 * @throws IllegalArgumentException if the name is null/blank or priority is invalid
	 */
	public Task(String name, String description, int priority) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Task name cannot be empty");
		}
		if (priority < 1 || priority > 5) {
			throw new IllegalArgumentException("Priority must be between 1 and 5");
		}
		this.name = name;
		this.description = description == null ? "" : description;
		this.priority = priority;
	}
	
	/**
	 * @return the name of the task
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the description of the task
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the priority of the task(1-5)
	 */
	public int getPrioirty() {
		return priority;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	/**
	 * @return the task name as the string representation
	 */
	@Override
	public String toString() {
		//return String.format("%s (priority: %d)", name, priority);
		return name;
	}
}
