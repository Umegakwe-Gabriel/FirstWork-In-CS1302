package edu.westga.cs1302.password_generator.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;
import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestViewModelSelectionAndGeneration {

	private ViewModel vm;

	@BeforeEach
	public void setup() {
		this.vm = new ViewModel();
	}
	
	@Test
    public void selectionInvalidWhenNoOptionsChecked() {
        this.vm.getRequireDigits().set(false);
        this.vm.getRequireLowercase().set(false);
        this.vm.getRequireUppercase().set(false);

        assertFalse(this.vm.getSelectionValid().get());
        assertTrue(this.vm.getErrorText().get().contains("Select at least one"));
    }

    @Test
    public void selectionValidWhenAnyOptionChecked() {
        this.vm.getRequireDigits().set(true);
        this.vm.getRequireLowercase().set(false);
        this.vm.getRequireUppercase().set(false);

        assertTrue(this.vm.getSelectionValid().get());
    }

    @Test
    public void generateBlockedIfSelectionInvalid() {
        this.vm.getMinimumLength().set("8");
        this.vm.getRequireDigits().set(false);
        this.vm.getRequireLowercase().set(false);
        this.vm.getRequireUppercase().set(false);

        this.vm.generatePassword();

        assertEquals("", this.vm.getPassword().get());
        assertEquals(0, this.vm.getPasswordHistory().size());
    }

    @Test
    public void generateAddsToHistoryWhenValid() {
        this.vm.getMinimumLength().set("8");
        this.vm.getRequireDigits().set(true);
        this.vm.getRequireLowercase().set(true);
        this.vm.getRequireUppercase().set(false);

        this.vm.generatePassword();

        assertNotEquals("", this.vm.getPassword().get());
        assertEquals(1, this.vm.getPasswordHistory().size());
        assertEquals(this.vm.getPassword().get(), this.vm.getPasswordHistory().get(0));
    }

}
