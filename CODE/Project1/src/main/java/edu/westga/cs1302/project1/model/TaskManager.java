package edu.westga.cs1302.project1.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages a list of tasks.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TaskManager {

    private final ObservableList<Task> tasks;

    /**
     * Creates a new TaskManager with an empty task list.
     */
    public TaskManager() {
        this.tasks = FXCollections.observableArrayList();
    }

    /**
     * Adds a task to the list.
     * 
     * @param task the task to add
     * @precondition task != null
     * @postcondition task is added to tasks
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        this.tasks.add(task);
    }
    
    /**
     * Remove a task from the manager
     * 
     * 
     * @precondition task != null
     * @postcondition !getTasks().contains(task)
     * 
     * @param task the task to remove
     */
    public void removeTask(Task task) {
    	if (task == null) {
    		throw new IllegalArgumentException("Task cannot be null");
    	}
    	this.tasks.remove(task);
    }

    /**
     * Returns the observable list of tasks.
     * 
     * @return the list of tasks
     */
    public ObservableList<Task> getTasks() {
        return this.tasks;
    }
}

