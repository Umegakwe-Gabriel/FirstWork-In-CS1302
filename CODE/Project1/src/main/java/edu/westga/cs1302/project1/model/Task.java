package edu.westga.cs1302.project1.model;

public class Task {
	private final String name;
	private String description;
	private final String priority; // e.g., 1 = low, 5 = high
	private boolean  completed;
	
	/**
	 * Constructs a Task with the specified name, description, and priority.
	 * 
	 * @param name the name of the task(cannot be null or blank)
	 * @param description the description of the task
	 * @param priority the priority of the task ("High", "Medium", "Low")
	 * 
	 * @precondition name != null && !name.isBlank()
	 *                    description != null
	 *                    priority != null && !priority.isBlank()
	 *  @postcondition getName() == name &&
	 *                    getDescription == description
	 *                    getPriority == priority
	 * @throws IllegalArgumentException if the name is null/blank or priority is invalid
	 */
	public Task(String name, String description, String priority) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Task name cannot be empty");
		}
		if (priority == null || priority.isBlank()) {
			throw new IllegalArgumentException("Priority cannot be null or blank");
		}
		if (description == null) {
			throw new IllegalArgumentException("Description cannot be null");
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
		return this.description;
	}
	
	/**
     * Updates the description of the task.
     *
     * @precondition description != null
     * @postcondition getDescription() == description
     *
     * @param description the new description
     */
	public void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("Descriptio cannot be null");
		}
		this.description = description;
	}
	
	/**
	 * Gets the task priority
	 * 
	 * @return the priority of the task
	*/
	public String getPrioirty() {
		return this.priority;
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
