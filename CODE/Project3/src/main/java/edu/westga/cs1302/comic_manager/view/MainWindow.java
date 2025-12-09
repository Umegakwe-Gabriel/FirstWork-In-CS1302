package edu.westga.cs1302.comic_manager.view;

import edu.westga.cs1302.comic_manager.model.Comic;
import edu.westga.cs1302.comic_manager.viewmodel.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	
	@FXML private TextField collectionNameField;
	@FXML private ListView<String> collectionsListView;
	@FXML private Button addCollectionButton;
	@FXML private Button removeCollectionButton; 
	@FXML private TextField comicTitleField;
	@FXML private TextField comicIssueField;
	@FXML private Button addComicButton;
	@FXML private ListView<Comic> comicListView;
	
	private ViewModel viewModel;
    
    /**
     * Initializes the controller and binds the ViewModel's properties to
     * the UI components. It also sets up the button actions for adding and finding
     * contacts.
     */
    @FXML
    void initialize() {
    	viewModel =  new ViewModel();
    	collectionNameField.textProperty().bindBidirectional(viewModel.collectionNameProperty());
    	collectionsListView.setItems(viewModel.collectionsProperty());
    	addCollectionButton.setOnAction(event -> 
    	viewModel.addCollection());
    	removeCollectionButton.setOnAction(event -> 
    	viewModel.removeCollection());
    	addCollectionButton.disableProperty()
    	.bind(viewModel.collectionNameProperty().isEmpty());
    	addComicButton.setOnAction(e -> viewModel.addComic());
    }
    	
}
