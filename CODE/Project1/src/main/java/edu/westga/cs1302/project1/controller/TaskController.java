package edu.westga.cs1302.project1.controller;

import edu.westga.cs1302.project1.model.Task;
import edu.westga.cs1302.project1.model.TaskList;

public class TaskController {
	private  final TaskList taskList;
	
	public TaskController(TaskList taskList) {
		this.taskList = taskList;
	}
	
	public void addTask(String name, String description, int priority) {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Task name cannot be empty");
		}
		if (priority < 1 || priority > 5) {
			throw new IllegalArgumentException("Prority maut be between 1 and 5");
		}
		Task task = new Task(name, description, priority);
		taskList.addTask(task);
	}
	
	public TaskList getTaskList() {
		return taskList;
	}
}
