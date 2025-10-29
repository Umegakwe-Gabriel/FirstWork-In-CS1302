package edu.westga.cs1302.task_tracker.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestNameComparators {
	
	@Test
	public void testAscendingAlphabeticalOrder() {
		Task t1 = new Task("Alpha", "desc", Task.TaskPriority.HIGH);
		Task t2 = new Task("Beta", "desc", Task.TaskPriority.HIGH);
		NameAscending comp = new NameAscending();
		assertTrue(comp.compare(t1, t2) < 0);
	}
	
	
	@Test
	public void testEqualNamesUsesPriorityTieBreakerAscending() {
		Task t1 = new Task("Same", "desc", Task.TaskPriority.HIGH);
		Task t2 = new Task("Same", "desc", Task.TaskPriority.LOW);
		NameAscending comp = new NameAscending();
		assertTrue(comp.compare(t1, t2) < 0);
	}
	
	@Test
	public void testEqualNamesUsesPriorityTieBreakerDescending() {
		Task t1 = new Task("Same", "desc", Task.TaskPriority.HIGH);
		Task t2 = new Task("Same", "desc", Task.TaskPriority.LOW);
		NameDescending comp = new NameDescending();
		assertTrue(comp.compare(t1, t2) < 0);
	}
	
	@Test
	public void testNullTasksBothNullGivesZero() {
		NameAscending asc = new NameAscending();
		assertEquals(0, asc.compare(null, null));
	}
	
	@Test
	public void testNullTaskFirstAscending() {
		Task t2 = new Task("Alpha", "desc", Task.TaskPriority.HIGH);
		NameAscending asc = new NameAscending();
		assertTrue(asc.compare(null, t2) < 0);
	}
	
	@Test
	public void testNullTaskSecondAscending() {
		Task t1 = new Task("Alpha", "desc", Task.TaskPriority.HIGH);
		NameAscending comp = new NameAscending();
		assertTrue(comp.compare(t1, null) > 0);
	}
	
	@Test
	public void testNullTaskFirstDescending() {
		Task t2 = new Task("Alpha", "desc", Task.TaskPriority.HIGH);
		NameDescending asc = new NameDescending();
		assertTrue(asc.compare(null, t2) > 0);
	}
	
	@Test
	public void testNullTaskSecondDescending() {
		Task t1 = new Task("Alpha", "desc", Task.TaskPriority.HIGH);
		NameDescending comp = new NameDescending();
		assertTrue(comp.compare(t1, null) < 0);
	}
	
	@Test
	public void testCaseInsensitiveComparisonAscending() {
		Task t1 = new Task("alpha", "desc", Task.TaskPriority.HIGH);
		Task t2 = new Task("Alpha", "desc", Task.TaskPriority.LOW);
		NameAscending comp = new NameAscending();
		assertEquals(0, comp.compare(t1, t2));
	}
	
	@Test
	public void testCaseInsensitiveComparisonDescending() {
		Task t1 = new Task("beta", "desc", Task.TaskPriority.HIGH);
		Task t2 = new Task("BETA", "desc", Task.TaskPriority.LOW);
		NameDescending comp = new NameDescending();
		assertEquals(0, comp.compare(t1, t2));
	}
	
	@Test
	public void testToStringLabels() {
		NameAscending comp = new NameAscending();
		NameAscending desc = new NameAscending();
		assertTrue(comp.toString().contains("Name"));
		assertTrue(desc.toString().contains("Name"));
	}

}
