package edu.westga.cs1302.task_tracker.views;

import java.util.Comparator;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Descending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;
import edu.westga.cs1302.task_tracker.model.TaskUtility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Controller class for MainWindow of the Task Tracker system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML private TextArea description;
    @FXML private Label highCount;
    @FXML private Label lowCount;
    @FXML private Label mediumCount;
    @FXML private TextField name;
    @FXML private ComboBox<TaskPriority> priority;
    @FXML private TextArea selectedDescription;
    @FXML private TextField selectedPriority;
    @FXML private ComboBox<Comparator<Task>> order;
    @FXML private ListView<Task> taskListView;

    // Backing ObservableList for the tasks
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    /**
     * Adds a new task to the list.
     * 
     * @param event to add a new task to the list.
     */
    @FXML
    void addTask(ActionEvent event) {
        try {
            Task newTask = new Task(this.name.getText(), this.description.getText(), this.priority.getValue());
            this.tasks.add(newTask);
            this.taskListView.refresh();
        } catch (IllegalArgumentException error) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Displays selected task details.
     * 
     * @param event to display the selected task details.
     */
    @FXML
    void selectTask(MouseEvent event) {
        Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            this.selectedPriority.setText(selectedTask.getPriority().toString());
            this.selectedDescription.setText(selectedTask.getDescription());
        }
    }

    /**
     * Removes the selected task.
     * 
     * @param event to remove the selected tasks.
     */
    @FXML
    void removeTask(ActionEvent event) {
        Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            this.tasks.remove(selectedTask);
            this.taskListView.refresh();
        }
    }

    /**
     * Updates the description of the selected task.
     * 
     * @param event to update the description of the selected task.
     */
    @FXML
    void updateDescription(ActionEvent event) {
        Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            selectedTask.setDescription(this.selectedDescription.getText());
            this.taskListView.refresh();
        }
    }

    /**
     * Counts how many tasks exist per priority level.
     * 
     * @param event to count the number of priorities assigned to task.
     */
    @FXML
    void countPriorities(ActionEvent event) {
        this.highCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.HIGH, this.tasks)));
        this.mediumCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.MEDIUM, this.tasks)));
        this.lowCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.LOW, this.tasks)));
    }

    /**
     * Sorts tasks in ascending or descending order.
     * 
     * @param event to trigger the sorting of tasks.
     */
    @FXML
    void sortTasks(ActionEvent event) {
        Comparator<Task> selectedComparator = this.order.getValue();
        if (selectedComparator != null) {
            FXCollections.sort(this.tasks, selectedComparator);
            this.taskListView.refresh();
        }
    }

    /**
     * Refreshes the task list (if needed).
     */
    private void refreshTaskList() {
        this.taskListView.setItems(this.tasks);
        this.taskListView.refresh();
    }

    /**
     * Initializes UI components and sets up ComboBoxes.
     */
    public void initialize() {
        // Set up priorities
        this.priority.getItems().addAll(TaskPriority.HIGH, TaskPriority.MEDIUM, TaskPriority.LOW);
        this.priority.setValue(TaskPriority.HIGH);

        // Bind list view to observable list
        this.taskListView.setItems(this.tasks);

        // Add comparators to order ComboBox
        this.order.getItems().addAll(new Ascending(), new Descending());
        this.order.setValue(new Ascending());
    }
}
