package edu.westga.cs1302.password_generator.viewmodel;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel for a password generator using the MVVM pattern. Exposes properties
 * for binding and implements generation/strength behavior.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class PasswordViewModel {

	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String DIGITS = "0123456789";
	private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.?/";

	private static final int DEFAULT_LENGTH = 12;
	private static final int MIN_LENGTH = 4;
	private static final int MAX_LENGTH = 128;

	private final IntegerProperty length;
	private final BooleanProperty useUpper;
	private final BooleanProperty useLower;
	private final BooleanProperty useDigits;
	private final BooleanProperty useSymbols;

	private final StringProperty password;
	private final ObjectProperty<PasswordStrength> strength;
	private final IntegerProperty strengthScore;

	private final SecureRandom random;

	/**
	 * Creates a new PasswordViewModel with sensible defaults.
	 */
	public PasswordViewModel() {
		this.length = new SimpleIntegerProperty(DEFAULT_LENGTH);
		this.useUpper = new SimpleBooleanProperty(true);
		this.useLower = new SimpleBooleanProperty(true);
		this.useDigits = new SimpleBooleanProperty(true);
		this.useSymbols = new SimpleBooleanProperty(false);

		this.password = new SimpleStringProperty("");
		this.strength = new SimpleObjectProperty<>(PasswordStrength.WEAK);
		this.strengthScore = new SimpleIntegerProperty(0);

		this.random = new SecureRandom();
	}

	// === Properties ===
	/**
	 * the length of the password.
	 * 
	 * @return the password length property.
	 */
	public IntegerProperty lengthProperty() {
		return this.length;
	}

	/**
	 * involves upper case characters.
	 * 
	 * @return whether upper case characters are included
	 */
	public BooleanProperty useUpperProperty() {
		return this.useUpper;
	}

	/**
	 * involves lower case characters.
	 * 
	 * @return whether lower case characters are included
	 */
	public BooleanProperty useLowerProperty() {
		return this.useLower;
	}

	/**
	 * involves digits characters.
	 * 
	 * @return whether digits characters are included
	 */
	public BooleanProperty useDigitsProperty() {
		return this.useDigits;
	}

	/**
	 * involves symbols characters.
	 * 
	 * @return whether symbols characters are included
	 */
	public BooleanProperty useSymbolsProperty() {
		return this.useSymbols;
	}

	/**
	 * involves the generated password characters.
	 * 
	 * @return the generated password property.
	 */
	public StringProperty passwordProperty() {
		return this.password;
	}

	/**
	 * involves the category strength of the password.
	 * 
	 * @return the strength category property.
	 */
	public ObjectProperty<PasswordStrength> strengthProperty() {
		return this.strength;
	}

	/**
	 * involves the strength score of the characters.
	 * 
	 * @return strength score (0..100)
	 */
	public IntegerProperty strengthScoreProperty() {
		return this.strengthScore;
	}

	/**
	 * Generates a password according to the current properties. Ensures at least
	 * one character from each selected category.
	 * 
	 * @throws IllegalArgumentException if no character categories are selected
	 * @throws IllegalArgumentException if length is out of allowed range.
	 */
	public void generate() {
		int target = this.length.get();
		this.validateLength(target);

		List<String> pools = new ArrayList<>();
		if (this.useUpper.get()) {
			pools.add(UPPER);
		}
		if (this.useLower.get()) {
			pools.add(LOWER);
		}
		if (this.useDigits.get()) {
			pools.add(DIGITS);
		}
		if (this.useSymbols.get()) {
			pools.add(SYMBOLS);
		}

		if (pools.isEmpty()) {
			throw new IllegalStateException("Select at least one character category.");
		}

		// Build aggregate pool
		StringBuilder aggregate = new StringBuilder();
		for (String pool : pools) {
			aggregate.append(pool);
		}

		// Guarantee at least one from each selected pool.
		List<Character> chars = new ArrayList<>();
		for (String pool : pools) {
			chars.add(this.randomChar(pool));
		}

		// Fill remaining
		while (chars.size() < target) {
			chars.add(this.randomChar(aggregate.toString()));
		}

		// Shuffle for randomness
		Collections.shuffle(chars, this.random);

		// Build final string
		StringBuilder sb = new StringBuilder();
		for (char cc : chars) {
			sb.append(cc);
		}
		String result = sb.toString();
		this.password.set(result);

		this.updateStrength(result, pools.size());
	}

	/**
	 * Computes strength score/category and updates properties.
	 * 
	 * @param pwd        generated password
	 * @param categories number of selected categories (1..4)
	 */
	private void updateStrength(String pwd, int categories) {
		int len = pwd.length();
		int score = 0;

		// Length scoring
		if (len >= 16) {
			score += 50;
		} else if (len >= 12) {
			score += 35;
		} else if (len >= 8) {
			score += 20;
		} else {
			score += 5;
		}

		// Variety scoring
		if (categories == 4) {
			score += 50;
		} else if (categories == 3) {
			score += 35;
		} else if (categories == 2) {
			score += 20;
		} else {
			score += 5;
		}

		if (score > 100) {
			score = 100;
		}

		this.strengthScore.set(score);
		if (score >= 70) {
			this.strength.set(PasswordStrength.STRONG);
		} else if (score >= 40) {
			this.strength.set(PasswordStrength.MEDIUM);
		} else {
			this.strength.set(PasswordStrength.WEAK);
		}
	}

	private char randomChar(String pool) {
		int idx = this.random.nextInt(pool.length());
		return pool.charAt(idx);
	}

	private void validateLength(int len) {
		if (len < MIN_LENGTH || len > MAX_LENGTH) {
			throw new IllegalArgumentException("Length must be between " + MIN_LENGTH + " and" + MAX_LENGTH + ".");
		}
	}
}
