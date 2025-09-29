package edu.westga.cs1302.project1.model;

/**
 * Represents a Task with a name, description, and priority.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Task {

    private final String name;
    private String description;
    private final int priority;

    /**
     * Creates a new Task.
     * 
     * @precondition name != null && !name.isBlank()
     * @precondition description != null
     * @precondition priority > 0
     * @postcondition new task is created
     * 
     * @param name the task name
     * @param description the task description
     * @param priority the task priority
     */
    public Task(String name, String description, int priority) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Task name cannot be null or blank");
        }
        if (description == null) {
            throw new IllegalArgumentException("Task description cannot be null");
        }
        if (priority <= 0) {
            throw new IllegalArgumentException("Priority must be greater than zero");
        }

        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPriority() {
        return this.priority;
    }

    /**
     * Updates the description of this task.
     *
     * @precondition newDescription != null
     * @postcondition getDescription() == newDescription
     * 
     * @param newDescription the new task description
     */
    public void updateDescription(String newDescription) {
        if (newDescription == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
