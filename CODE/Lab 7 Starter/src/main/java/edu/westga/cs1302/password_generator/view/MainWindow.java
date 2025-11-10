package edu.westga.cs1302.password_generator.view;

import java.util.Random;

import edu.westga.cs1302.password_generator.viewmodel.PasswordStrength;
import edu.westga.cs1302.password_generator.viewmodel.PasswordViewModel;
import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private TextArea output;
    
    @FXML private TextField lengthField;
    @FXML private CheckBox upperCheck;
    @FXML private CheckBox lowerCheck;
    @FXML private CheckBox digitsCheck;
    @FXML private CheckBox symbolsCheck;
    
    @FXML private Label strengthLabel;
    @FXML private ProgressBar strengthBar;
    
    private PasswordGenerator generator;
    private PasswordViewModel viewModel;

    @FXML
    void generatePassword(ActionEvent event) {
    	int minimumLength = -1;
    	
    	try {
    		minimumLength = Integer.parseInt(this.minimumLength.getText());
    	} catch (NumberFormatException numberError) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Invalid Minimum Length: must be a positive integer, but was " + this.minimumLength.getText());
    		alert.show();
    		return;
    	}
    	
    	try {
    		this.generator.setMinimumLength(minimumLength);
    	} catch (IllegalArgumentException invalidLengthError) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Invalid Minimum Length: " + invalidLengthError.getMessage());
    		alert.show();
    		return;
    	}
    	
    	this.generator.setMustHaveAtLeastOneDigit(this.mustIncludeDigits.isSelected());
    	this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustIncludeLowerCaseLetters.isSelected());
    	this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustIncludeUpperCaseLetters.isSelected());
    	
    	String password = this.generator.generatePassword();
    	
    	this.output.setText(password);
    }

    @FXML
    void initialize() {
        assert this.mustIncludeDigits != null : "fx:id=\"mustIncludeDigits\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.mustIncludeLowerCaseLetters != null : "fx:id=\"mustIncludeLowerCaseLetters\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.mustIncludeUpperCaseLetters != null : "fx:id=\"mustIncludeUpperCaseLetters\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.minimumLength != null : "fx:id=\"minimumLength\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.output != null : "fx:id=\"output\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.minimumLength.setText("1");
        Random randomNumberGenerator = new Random();
        this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());
        
        this.viewModel = new PasswordViewModel();

     // Bind length with an integer TextFormatter
        IntegerStringConverter conv = new IntegerStringConverter();
        TextFormatter<Integer> lengthFormatter =
                new TextFormatter<>(conv, 12, this::lengthFilter);
        this.lengthField.setTextFormatter(lengthFormatter);

        // WORKAROUND FOR JAVA FX TYPE MISMATCH
        Property<Number> numberProperty = new SimpleObjectProperty<>();
        numberProperty.bindBidirectional(lengthFormatter.valueProperty());

        // NOW THIS WORKS
        this.viewModel.lengthProperty().bindBidirectional(numberProperty);

        // Bind checkboxes
        this.upperCheck.selectedProperty().bindBidirectional(this.viewModel.useUpperProperty());
        this.lowerCheck.selectedProperty().bindBidirectional(this.viewModel.useLowerProperty());
        this.digitsCheck.selectedProperty().bindBidirectional(this.viewModel.useDigitsProperty());
        this.symbolsCheck.selectedProperty().bindBidirectional(this.viewModel.useSymbolsProperty());

        // Bind output
        this.output.textProperty().bind(this.viewModel.passwordProperty());

        // Bind strength label
        this.viewModel.strengthProperty().addListener((obs, oldV, newV) -> {
            if (newV == null) {
                this.strengthLabel.setText("WEAK");
            } else {
                this.strengthLabel.setText(newV.name());
            }
        });

        // Bind strength progress if present (0..1)
        if (this.strengthBar != null) {
            this.strengthBar.progressProperty().bind(
                this.viewModel.strengthScoreProperty().divide(100.0)
            );
        }
    }
    
    private TextFormatter.Change lengthFilter(TextFormatter.Change change) {
        String newText = change.getControlNewText();
        if (newText == null || newText.isEmpty()) {
            return change;
        }
        for (int i = 0; i < newText.length(); i++) {
            char c = newText.charAt(i);
            if (!Character.isDigit(c)) {
                return null; // reject non-digit
            }
        }
        return change;
    }
    
    /** Generate button click handler. */
    @FXML
    public void handleGenerate(ActionEvent event) {
        try {
            this.viewModel.generate();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            Alert a = new Alert(AlertType.ERROR, ex.getMessage());
            a.setHeaderText("Cannot Generate Password");
            a.showAndWait();
        }
    }
}
