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
    private int priority;

    /**
     * Creates a new Task.
     *
     * @precondition name != null && !name.isBlank()
     * @precondition description != null
     * @precondition priority >= 1 && priority <= 5
     *
     * @param name        the name of the task
     * @param description the description of the task
     * @param priority    the priority (1–5)
     */
    public Task(String name, String description, int priority) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Task name cannot be null or blank");
        }
        if (description == null) {
            throw new IllegalArgumentException("Task description cannot be null");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
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
    
    /**
     * Updates the priority of this task.
     *
     * @precondition newPriority >= 1 && newPriority <= 5
     * @param newPriority the updated priority
     */
    public void setPriority(int newPriority) {
        if (newPriority < 1 || newPriority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = newPriority;
    }


    @Override
    public String toString() {
        return this.name + " (Priority: " + this.priority + ")";
    }
}
