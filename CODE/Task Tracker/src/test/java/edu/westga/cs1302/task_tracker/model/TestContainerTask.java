package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

public class TestContainerTask {
	
	@Test
	public void testAddTaskWrapsBaseTask() {
		Task base = new Task("Parent", "desc", Task.TaskPriority.HIGH);
		Task sub = new Task("SubTask", "child", TaskPriority.LOW);
		Task wrapped = base.addTask(sub);
		
		assertTrue(wrapped instanceof ContainerTask);
		assertEquals("Parent", wrapped.getName());
		assertEquals("desc", wrapped.getDescription());
		assertEquals(Task.TaskPriority.HIGH, wrapped.getPriority());
	}

	@Test
	public void testAddingMultipleSubtasksAccumulates() {
		ContainerTask container = new ContainerTask("P", "desc", Task.TaskPriority.MEDIUM);
		container.addTask(new Task("A", "d", Task.TaskPriority.LOW));
		container.addTask(new Task("B", "d", Task.TaskPriority.LOW));
		
		assertEquals(2, container.getSubTasks().size());
	}
	
	@Test
	public void testGetSUbTasksReturnsUnmodifiableList() {
		ContainerTask container = new ContainerTask("P", "desc", Task.TaskPriority.MEDIUM);
		container.addTask(new Task("A", "d", Task.TaskPriority.LOW));
		
		List<Task> subtasks = container.getSubTasks();
		assertThrows(UnsupportedOperationException.class, ()-> subtasks.add(new Task("B", "d", Task.TaskPriority.LOW)));
	}
	
	@Test
	public void testAddTaskWithNullThrowsException() {
		ContainerTask container = new ContainerTask("P", "desc", Task.TaskPriority.MEDIUM);
		assertThrows(IllegalArgumentException.class, () -> container.addTask(null));
	}
	
	@Test
	public void testBaseTaskGetSubTasksReturnsEmptyList() {
		Task base = new Task("P", "desc", Task.TaskPriority.HIGH);
		assertTrue(base.getSubTasks().isEmpty());
	}
	
	@Test
	public void testToStringShowsPlusSign() {
		ContainerTask container = new ContainerTask("P", "desc", Task.TaskPriority.HIGH);
		assertTrue(container.toString().contains("+"));
	}
}
