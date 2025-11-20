package edu.westga.cs1302.password_generator.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	
	@FXML private MenuBar menuBar;
	@FXML private MenuItem saveMenuItem;
    @FXML private MenuItem aboutMenuItem;
    @FXML private MenuItem closeMenuItem;
    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private Label errorTextLabel;
    @FXML private Label minLengthErrorText;
    @FXML private Button generatePasswordButton;
    @FXML private ListView<String> passwordHistory;
    
    private ViewModel vm;
    
    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
    	this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
    	this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
    	this.minimumLength.setText(this.vm.getMinimumLength().getValue());
    	this.vm.getMinimumLength().bind(this.minimumLength.textProperty());
    	
    	this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
    	this.passwordHistory.setItems(this.vm.getPasswordHistory());
    	
    	this.minimumLength.textProperty().addListener((observable, newValue, oldValue) -> {
    		this.minLengthErrorText.setVisible(!newValue.matches("\\d+") || Integer.parseInt(newValue) == 0);
    	});
    	
    	this.generatePasswordButton.disableProperty().bind(
    			this.vm.getLengthValid().not().or(this.vm.getSelectionValid().not())
    			);
    	
    	this.generatePasswordButton.setOnAction(
    			(event) -> { 
    				this.vm.generatePassword();
    			} 
    	);
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Generated Passwords");
        chooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = chooser.showSaveDialog(
            ((Node) event.getSource()).getScene().getWindow());

        if (file == null) {
            return; // user cancelled
        }

        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            for (String pwd : this.vm.getPasswordHistory()) {
                writer.println(pwd);
            }
        } catch (IOException ioEx) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Unable to Save Passwords");
            alert.setContentText(ioEx.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About Password Generator");
        alert.setHeaderText("Password Generator – Labs 8 and 9");
        alert.setContentText(
            "This project generates random passwords based on user-selected\n"
          + "requirements and keeps a history of generated passwords.\n\n"
          + "Author: Gabriel"
        );
        alert.showAndWait();
    }

    @FXML
    private void handleClose(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    /**Optional FXML onAction hook. */
    @FXML
    public void generatePassword() {
    	this.vm.generatePassword();
    }
}
