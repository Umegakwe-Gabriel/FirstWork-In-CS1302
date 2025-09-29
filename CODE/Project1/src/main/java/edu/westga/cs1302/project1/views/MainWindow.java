package edu.westga.cs1302.project1.views;

import edu.westga.cs1302.project1.model.Task;
import edu.westga.cs1302.project1.model.TaskManager;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
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
	private ComboBox<Integer> priorityComboBox;

	/** 
	 * List view to display all tasks. 
	*/
	@FXML
	private ListView<Task> taskListView;
	
	@FXML
	private TextArea selectedTaskDescriptionArea;
	
	@FXML
	private TextField selectedTaskPriorityField;
	
	private final TaskManager taskManager = new TaskManager();
	
	private final ObservableList<Task> tasks = FXCollections.observableArrayList();

	/**
	 * Initializes the MainWindow
	 * 
	 * @precondition none 
	 * @postcondition the priorityComboBox is populated, 
	 *                the task list is observable, and
	 *                task selection updates display fields.
	 */
	@FXML
	public void initialize() {
		this.priorityComboBox.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
		this.taskListView.setItems(this.tasks);
		
//		//Ensure the ListView is backed by an observable list
//		taskListView.setItems(this.taskManager.getTasks());
//		
//		//Listener for task selection
//		this.taskListView.getSelectionModel().selectedItemProperty().addListener(
//			(observable, oldTask, newTask) -> {
//				if (newTask != null) {
//					this.selectedTaskDescriptionArea.setText(newTask.getDescription());
//					this.selectedTaskPriorityField.setText(newTask.getPriority());
//				} else {
//					this.selectedTaskDescriptionArea.clear();
//					this.selectedTaskPriorityField.clear();
//				}
//			}
//				);
	}
	
	@FXML
	private void handlePriorityChange() {
//		String selected = priorityComboBox.getValue();
//		System.out.println("Priority selected: " + selected);
	}
	
	/**
	 * Handles the Add Task Button click
	 * Creates a Task from user input and adds it to the ListView.
	 */
	@FXML
    private void handleAddTask() {
        try {
            String name = this.taskNameField.getText();
            String description = this.taskDescriptionArea.getText();
            Integer priority = this.priorityComboBox.getValue();

            if (priority == null) {
                this.showAlert("Validation Error", "Please select a priority.");
                return;
            }

            Task task = new Task(name, description, priority);
            this.tasks.add(task);

            this.taskNameField.clear();
            this.taskDescriptionArea.clear();
            this.priorityComboBox.getSelectionModel().clearSelection();

        } catch (IllegalArgumentException error) {
            this.showAlert("Validation Error", error.getMessage());
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
