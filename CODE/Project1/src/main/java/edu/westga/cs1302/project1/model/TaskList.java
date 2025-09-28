package edu.westga.cs1302.project1.model;

import java.util.ArrayList; 
import java.util.List;

public class TaskList {
	private final List<Task> tasks;
	
	public TaskList() {
		this.tasks = new ArrayList<>();
	}
	
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot null");
		}
		tasks.add(task);
	}
	
	public List<Task> getTasks() {
		return new ArrayList<>(tasks); //defensive copy
	}
}
