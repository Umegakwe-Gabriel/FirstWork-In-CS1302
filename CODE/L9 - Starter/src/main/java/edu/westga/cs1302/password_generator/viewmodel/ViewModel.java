package edu.westga.cs1302.password_generator.viewmodel;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages utilizing the model and makes properties available to bind the UI
 * elements.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class ViewModel {
	private StringProperty minimumLength;
	private BooleanProperty requireDigits;
	private BooleanProperty requireLowercase;
	private BooleanProperty requireUppercase;
	private final StringProperty password;
	private final BooleanProperty lengthValid;
	private final BooleanProperty selectionValid;

	private final ObservableList<String> passwordHistory;

	private StringProperty errorText;

	private PasswordGenerator generator;

	/*
	 * Accepts positive integers (no zero or negative).
	 */
	private static final Pattern LENGTH_PATTERN = Pattern.compile("^[1-9][0-9]*$");

	/**
	 * Initialize the properties for the viewmodel
	 */
	public ViewModel() {
		this.minimumLength = new SimpleStringProperty("1");
		this.requireDigits = new SimpleBooleanProperty(false);
		this.requireLowercase = new SimpleBooleanProperty(false);
		this.requireUppercase = new SimpleBooleanProperty(false);
		
		this.password = new SimpleStringProperty("");
		this.errorText = new SimpleStringProperty("");

		this.lengthValid = new SimpleBooleanProperty(true);
		this.selectionValid = new SimpleBooleanProperty(false);

		this.passwordHistory = FXCollections.observableArrayList();

		Random randomNumberGenerator = new Random();
		this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());

		this.minimumLength.addListener((obs, oldV, newV) -> {
			if (newV == null || !LENGTH_PATTERN.matcher(newV.trim()).matches()) {
				this.lengthValid.set(false);
				this.errorText.set("Minimum length must be a positive integer.");
			} else {
				this.lengthValid.set(true);
				this.errorText.set("");
			}
		});
		
		this.requireDigits.addListener((obs, ov, nv) -> this.updateSelectionValid());
		this.requireLowercase.addListener((obs, ov, nv) -> this.updateSelectionValid());
		this.requireUppercase.addListener((obs, ov, nv) -> this.updateSelectionValid());
		
		this.updateSelectionValid();
	}
	
	private void updateSelectionValid() {
		boolean anySelected = this.requireDigits.get() || this.requireLowercase.get() || this.requireUppercase.get();
		this.selectionValid.set(anySelected);
		if (!anySelected) {
			this.errorText.set("Select at least one character requirement.");
		} else if (this.lengthValid.get()) {
			this.errorText.set("");
		}
	}

	/**
	 * Return the minimum length property
	 * 
	 * @return the minimum length property
	 */
	public StringProperty getMinimumLength() {
		return this.minimumLength;
	}

	/**
	 * Return the require digits property
	 * 
	 * @return the require digits property
	 */
	public BooleanProperty getRequireDigits() {
		return this.requireDigits;
	}

	/**
	 * Return the require upper case property
	 * 
	 * @return the require upper case property
	 */
	public BooleanProperty getRequireUppercase() {
		return this.requireUppercase;
	}

	/**
	 * Return the require lower case property
	 * 
	 * @return the require lower case property
	 */
	public BooleanProperty getRequireLowercase() {
		return this.requireLowercase;
	}

	/**
	 * Return the password history property
	 * 
	 * @return the password history property
	 */
	public ObservableList<String> getPasswordHistory() {
		return this.passwordHistory;
	}
	
	/**
	 * Return the password property
	 * 
	 * @return the password property
	 */
	public StringProperty getPassword() {
		return this.password;
	}

	/**
	 * Return the error text property
	 * 
	 * @return the error text property
	 */
	public StringProperty getErrorText() {
		return this.errorText;
	}
	
	/**
	 * Return the error text property
	 * 
	 * @return the error text property
	 */
	public BooleanProperty getLengthValid() {
		return this.lengthValid;
	}
	
	/**
	 * Return the error text property
	 * 
	 * @return the error text property
	 */
	public BooleanProperty getSelectionValid() {
		return this.selectionValid;
	}

	/**
	 * Generates a password using the minimum length, require digit, require lower
	 * case, and require upper case property values.
	 * 
	 * If a password is successfully generated, the error text property is set to
	 * empty string and the password property is set to the password generated.
	 * 
	 * If an error is encountered, the password property is set to empty, and the
	 * error text property is populated with a message describing the problem.
	 */
	public void generatePassword() {
		if (!this.lengthValid.get()) {
			this.password.set("");
			this.errorText.set("Cannot generate: invalid minimum length.");
			return;
		}
		if (!this.selectionValid.get()) {
			this.password.set("");
			this.errorText.set("Cannot generate: no character type selected.");
			return;
		}
		
		int minimumLengthValue;
		try {
			minimumLengthValue = Integer.parseInt(this.minimumLength.get().trim());
		} catch (NumberFormatException numberError) {
			this.password.set("");
			this.errorText.set("Invalid Minimum Length: must be a positive integer but was "
					+ this.minimumLength.get());
			return;
		}
		
		try {
			this.generator.setMinimumLength(minimumLengthValue);
		} catch (IllegalArgumentException invalidLengthError) {
			this.password.set("");
			this.errorText.set("Invalid Minimum Length " + invalidLengthError.getMessage());
			return;
		}
		
		this.generator.setMustHaveAtLeastOneDigit(this.requireDigits.get());
		this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.requireLowercase.get());
		this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.requireUppercase.get());
		
		String newPassword = this.generator.generatePassword();
		
		this.errorText.set("");
		this.password.set(newPassword);
		this.passwordHistory.add(0, newPassword);
		
	}
}
