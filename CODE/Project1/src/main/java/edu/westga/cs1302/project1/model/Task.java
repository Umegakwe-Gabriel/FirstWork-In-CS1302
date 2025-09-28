package edu.westga.cs1302.project1.model;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
	private final UUID id;
	private String name;
	private String description;
	private int priority; // e.g., 1 = low, 5 = high
	private boolean  completed;
	
	//constructor for new tasks
	public Task (String name, String description, int priority) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.completed = false;
	}
	
	//Constructor for when loading from persistence
	public Task(UUID id, String name, String description, int priority, boolean completed) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.completed = completed;
	}
	
	public UUID getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getPrioirty() {
		return priority;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	@Override
	public String toString() {
		return String.format("%s (priority: %d)", name, priority);
	}
}
