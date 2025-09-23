package edu.westga.cs1302.project1.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

	@FXML
	private ComboBox<String> favoriteMovieSelector;
	
	@FXML
	private Button addTaskButton;
	
	@FXML
	private ListView<String> movieList;
	
	@FXML
	private TextArea taskDescriptionArea;

	@FXML
	private TextField title;
	
	@FXML
	void addTitle(ActionEvent event) {
		this.movieList.getItems().add(this.title.getText());
		this.favoriteMovieSelector.getItems().add( this.title.getText());
	}

	@FXML
	void popUpFavorite(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText( this.favoriteMovieSelector.getValue());
		alert.show();
	}

	@FXML
	void selectMovie(MouseEvent event) {
		String selectedTitle = this.movieList.getSelectionModel().getSelectedItem();
		if(selectedTitle != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(selectedTitle);
			alert.show();
		}
	}

	/**
	 * Perform any needed initialization of UI components and underlying objects.
	 */
	public void initialize() {

	}
}
