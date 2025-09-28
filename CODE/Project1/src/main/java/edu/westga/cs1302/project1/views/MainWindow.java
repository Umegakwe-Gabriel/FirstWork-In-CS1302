package edu.westga.cs1302.project1.views;

import edu.westga.cs1302.project1.model.Task;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.*;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

	@FXML
	private TextField taskNameField;

	/** 
	 * Text area for entering the task description. 
	*/
	@FXML
	private TextArea taskDescriptionArea;

	/** 
	 * Combo box for selecting task priority. 
	*/
	@FXML
	private ComboBox<Integer> displayPriorityField;

	/** 
	 * List view to display all tasks. 
	*/
	@FXML
	private ListView<Task> taskListView;
	
	private final ObservableList<Task> tasks = FXCollections.observableArrayList();

	/**
	 * Perform any needed initialization of UI components and underlying objects.
	 */
	public void initialize() {
		displayPriorityField.getItems().addAll(1, 2, 3, 4, 5);
		taskListView.setItems(tasks);
	}
	
	@FXML
	private void handlePriorityChange() {
//		String selected = priorityComboBox.getValue();
//		System.out.println("Priority selected: " + selected);
		displayPriorityField.getItems().addAll(1, 2, 3, 4, 5);
	}
	
	/**
	 * Handles the Add Task Button click
	 * Creates a Task from user input and adds it to the ListView.
	 */
	@FXML
	private void handleAddTask() {
		try {
			String name = taskNameField.getText();
			String description = taskDescriptionArea.getText();
			Integer priority = displayPriorityField.getValue();
			
			if (priority == null) {
				showAlert("Validation Error", "Please select a priority");
				return;
			}
			
			Task task = new Task(name, description, priority);
			tasks.add(task);
		}catch (IllegalArgumentException error) {
			showAlert("Validation Error", error.getMessage());
		}
	}
	
	/**
	 * Utility method to display error messages in an alert
	 * 
	 * @param title the alert title
	 * @param message the alert message
	 */
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
