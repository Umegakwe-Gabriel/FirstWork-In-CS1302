package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Task that can contain subtasks
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class ContainerTask extends Task {
	
	private final List<Task> subTasks;
	
	/**
	 * Creates a new ContainerTask object that stores the name, description, and priority
	 * 
	 * @param name the name of the task.
	 * @param description the description of the task.
	 * @param priority the priority of the task.
	 */
	public ContainerTask(String name, String description, TaskPriority priority) {
		super(name, description, priority);
		this.subTasks = new ArrayList<>();
	}
	
	@Override
	public Task addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("task must not be null");
		}
		this.subTasks.add(task);
		return this;
	}
	
	@Override
	public List<Task> getSubTasks() {
		return Collections.unmodifiableList(this.subTasks);
	}
	
	@Override
	public String toString() {
		return super.toString() + " (+)";
	}
}
