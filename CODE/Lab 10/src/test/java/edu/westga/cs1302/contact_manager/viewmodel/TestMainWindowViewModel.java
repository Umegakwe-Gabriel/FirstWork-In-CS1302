package edu.westga.cs1302.contact_manager.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMainWindowViewModel {

	private MainWindowViewModel vm;
	
	@BeforeEach
	public void setUp() {
		this.vm = new MainWindowViewModel();
	}
	
	@Test
	public void testAddVlaidContactAddsToList() {
		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("123-4567");
		
		this.vm.addContact();
		
		assertEquals(1, this.vm.getContacts().size());
		assertEquals("Alice", this.vm.getContacts().get(0).getName());
		assertEquals("123-4567", this.vm.getContacts().get(0).getPhoneNumber());
	}
	
	@Test
	public void testAddContactWithInvalidNameThrows() {
		this.vm.getName().set("Alice1");
		this.vm.getPhoneNumber().set("123-4567");
		
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, 
				() -> this.vm.addContact());
		
		assertEquals("name is not valid", ex.getMessage());
	}
	
	@Test
    public void testAddContactWithInvalidPhoneThrows() {
        this.vm.getName().set("Bob");
        this.vm.getPhoneNumber().set("1234"); // invalid: not ###-#### or #######

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> this.vm.addContact());

        assertEquals("phone number is not valid", ex.getMessage());
    }
	
	@Test
    public void testAddContactWithDuplicateNameThrows() {
        this.vm.getName().set("Carol");
        this.vm.getPhoneNumber().set("111-1111");
        this.vm.addContact();

        this.vm.getName().set("Carol");   // same name
        this.vm.getPhoneNumber().set("222-2222"); // different phone

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> this.vm.addContact());

        assertTrue(ex.getMessage().contains("name already exists"));
    }
	
	@Test
    public void testAddContactWithDuplicatePhoneThrows() {
        this.vm.getName().set("Dave");
        this.vm.getPhoneNumber().set("333-3333");
        this.vm.addContact();

        this.vm.getName().set("Eve");     // different name
        this.vm.getPhoneNumber().set("333-3333"); // same phone

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> this.vm.addContact());

        assertTrue(ex.getMessage().contains("phone number already exists"));
    }
	
	@Test
    public void testFindContactByNameSuccess() {
        this.vm.getName().set("Frank");
        this.vm.getPhoneNumber().set("444-4444");
        this.vm.addContact();

        this.vm.getSearchCriteria().set("Frank");
        String result = this.vm.findContact();

        assertEquals("Frank, 444-4444", result);
    }
	
	@Test
    public void testFindContactByPhoneSuccess() {
        this.vm.getName().set("Grace");
        this.vm.getPhoneNumber().set("555-5555");
        this.vm.addContact();

        this.vm.getSearchCriteria().set("555-5555");
        String result = this.vm.findContact();

        assertEquals("Grace, 555-5555", result);
    }
	
	@Test
    public void testFindContactInvalidCriteriaThrows() {
        this.vm.getSearchCriteria().set("???");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> this.vm.findContact());

        assertTrue(ex.getMessage().contains("not a valid name or phone number"));
    }
	
	@Test
    public void testFindContactNotFoundReturnsNoContactFound() {
        this.vm.getSearchCriteria().set("Henry");
        String result = this.vm.findContact();

        assertEquals("No contact found.", result);
    }

}
