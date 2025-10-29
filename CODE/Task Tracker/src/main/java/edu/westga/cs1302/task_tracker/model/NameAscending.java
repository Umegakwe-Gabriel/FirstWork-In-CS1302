package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Comparator that sorts task by name in ascending (A->Z) order
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class NameAscending implements Comparator<Task> {
	
	@Override
	public int compare(Task aa, Task bb) {
		if (aa == null && bb == null) {
			return 0;
		}
		if (aa == null) {
			 return -1;
		}
		if (bb == null) {
			 return 1;
		}
		
		String n1;
		if (aa.getName() == null) {
			n1 = "";
		} else {
			n1 = aa.getName();
		}
		
		String n2;
		if (bb.getName() == null) {
			n2 = "";
		} else {
			n2 = bb.getName();
		}
		
		int cmp = n1.compareToIgnoreCase(n2);
		if (cmp != 0) {
			return cmp;
		}
		if (n1.equals(n2)) {
			return Integer.compare(aa.getPriority().getValue(), bb.getPriority().getValue());
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "Name (A->Z)";
	}
}
