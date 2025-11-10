package edu.westga.cs1302.password_generator.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

class TestGeneratePassword {
	
	 private ViewModel vm;

	    @BeforeEach
	    public void setup() {
	        this.vm = new ViewModel();
	    }

//	@Test
//	void testMinimumLengthNotANumber() {
//		ViewModel vm = new ViewModel();
//		vm.getMinimumLength().setValue("apple");
//		
//		vm.generatePassword();
//		
//		assertEquals("", vm.getPassword().getValue(), "checking the password property");
//		assertEquals("Invalid Minimum Length: must be a positive integer, but was apple", vm.getErrorText().getValue(), "checking the error text property");
//	}
	
//	@Test
//	void testMinimumLengthNotAValidNumber() {
//		ViewModel vm = new ViewModel();
//		vm.getMinimumLength().setValue("-2");
//		
//		vm.generatePassword();
//		
//		assertEquals("", vm.getPassword().getValue(), "checking the password property");
//		assertEquals("Invalid Minimum Length: minimum length must be at least 1", vm.getErrorText().getValue(), "checking the error text property");
//	}
	
	@Test
	void testValidInputProvided() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");
		
		vm.generatePassword();
		
		assertTrue(vm.getPassword().getValue().length() >= 2, "checking the password property has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue(), "checking the error text property");
	}
	
	@Test
    public void lengthInvalidWhenNonDigits() {
        this.vm.getMinimumLength().set("12a");
        assertFalse(this.vm.getLengthValid().get());
        assertTrue(this.vm.getErrorText().get().contains("positive integer"));
    }

    @Test
    public void lengthValidWhenPositiveNumber() {
        this.vm.getMinimumLength().set("10");
        assertTrue(this.vm.getLengthValid().get());
        assertEquals("", this.vm.getErrorText().get());
    }

    @Test
    public void generateBlockedWhenInvalidLength() {
        this.vm.getMinimumLength().set("0"); // invalid by regex -> listener sets invalid
        this.vm.generatePassword();
        assertEquals("", this.vm.getPassword().get());
        assertTrue(this.vm.getErrorText().get().length() > 0);
        assertEquals(0, this.vm.getPasswordHistory().size());
    }
    
    @Test
    public void historyAccumulatesNewestFirst() {
        this.vm.getMinimumLength().set("6");
        this.vm.generatePassword();
        String p1 = this.vm.getPassword().get();

        this.vm.generatePassword();
        String p2 = this.vm.getPassword().get();

        assertEquals(2, this.vm.getPasswordHistory().size());
        assertEquals(p2, this.vm.getPasswordHistory().get(0));
        assertEquals(p1, this.vm.getPasswordHistory().get(1));
    }

    @Test
    public void generationRespectsInputs() {
        this.vm.getMinimumLength().set("8");
        this.vm.getRequireDigits().set(true);
        this.vm.getRequireLowercase().set(true);
        this.vm.getRequireUppercase().set(true);

        this.vm.generatePassword();
        String pwd = this.vm.getPassword().get();

        assertNotNull(pwd);
        assertFalse(pwd.isEmpty());
        assertTrue(this.vm.getPasswordHistory().contains(pwd));
    }
}



