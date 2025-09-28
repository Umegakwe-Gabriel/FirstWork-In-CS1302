package edu.westga.cs1302.project1.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

	@FXML
	private TextField taskNameField;

	/** Text area for entering the task description. */
	@FXML
	private TextArea taskDescriptionArea;

	/** Combo box for selecting task priority. */
	@FXML
	private ComboBox<String> priorityComboBox;

	/** List view to display all tasks. */
	@FXML
	private ListView<String> taskListView;

	/**
	 * Perform any needed initialization of UI components and underlying objects.
	 */
	public void initialize() {
//		
	}
	
	@FXML
	private void handlePriorityChange() {
//		String selected = priorityComboBox.getValue();
//		System.out.println("Priority selected: " + selected);
		priorityComboBox.getItems().addAll("High", "Meduim", "Low");
	}
	
	/**
	 * Handles the Add Task Button
	 */
	@FXML
	private void handleAddTask() {
		System.out.println("Add Task Button Clicked");
	}
}
