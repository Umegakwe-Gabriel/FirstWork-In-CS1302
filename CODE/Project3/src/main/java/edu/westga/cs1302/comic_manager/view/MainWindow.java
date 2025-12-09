package edu.westga.cs1302.comic_manager.view;

import edu.westga.cs1302.comic_manager.model.Collection;
import edu.westga.cs1302.comic_manager.model.Comic;
import edu.westga.cs1302.comic_manager.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

/**
 * Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

	@FXML
	private TextField collectionNameField;
	@FXML
	private ListView<Collection> collectionsListView;
	@FXML
	private Button addCollectionButton;
	@FXML
	private Button removeCollectionButton;
	@FXML
	private TextField comicTitleField;
	@FXML
	private TextField comicIssueField;
	@FXML
	private Button addComicButton;
	@FXML
	private ListView<Comic> comicListView;
	@FXML
	private TextField searchField;
	@FXML
	private Button findComicButton;

	private ViewModel viewModel;

	/**
	 * Initializes the controller and binds the ViewModel's properties to the UI
	 * components. It also sets up the button actions for adding and finding
	 * contacts.
	 */
	@FXML
	void initialize() {
		this.viewModel = new ViewModel();
		this.collectionNameField.textProperty().bindBidirectional(this.viewModel.collectionNameProperty());

		this.collectionsListView.setItems(this.viewModel.collectionsProperty());

		this.addCollectionButton.setOnAction(event -> this.viewModel.addCollection());

		this.removeCollectionButton.setOnAction(event -> this.viewModel.removeCollection());

		this.addCollectionButton.disableProperty().bind(this.viewModel.collectionNameProperty().isEmpty());

		this.addComicButton.setOnAction(e -> this.viewModel.addComic());

		this.comicTitleField.textProperty().bindBidirectional(this.viewModel.comicTitleProperty());

		this.comicIssueField.textProperty().bindBidirectional(this.viewModel.comicIssueNumberProperty(),
				new NumberStringConverter());

		this.comicListView.setItems(this.viewModel.comicsProperty());

		this.addComicButton.setOnAction(e -> this.viewModel.addComic());

		this.addComicButton.disableProperty()
				.bind(this.viewModel.comicTitleProperty().isEmpty().or(this.viewModel.comicIssueNumberProperty().isEqualTo(0)));
		
		this.findComicButton.setOnAction(e -> this.viewModel.findComic());
	}

}
