package edu.westga.cs1302.comic_manager.model;

/**
 * Represents a comic in the collection.
 * Each comic has a title and an issue number.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Comic {
	private String title;
	private int issueNumber;
	
	/**
     * Creates a new comic with the given title and issue number.
     * 
     * @param title the title of the comic
     * @param issueNumber the issue number of the comic
     */
	public Comic(String title, int issueNumber) {
		if (!Comic.checkName(title)) {
            throw new IllegalArgumentException("Title is not valid. It must contain only letters and spaces.");
        }
        
        if (issueNumber < 1) {
            throw new IllegalArgumentException("Issue number must be greater than 0.");
        }
        
		this.title = title;
		this.issueNumber = issueNumber;
	}
	
	/**
     * Checks if the title of the comic contains only letters and spaces.
     * Valid title must contain only alphabetic characters (a-z, A-Z) or spaces.
     * 
     * @param title the title of the comic to be checked
     * @return true if the title is valid, otherwise false
     */
    public static boolean checkName(String title) {
        if (title == null) {
            return false;
        }
        // Title must only contain letters and spaces
        return title.matches("[a-zA-Z ]+");
    }
    
    /**
     * Checks if the phone number matches the valid format for phone numbers.
     * The phone number must be either in the form of "###-####" or "#######".
     * Format without dashes (e.g., 1234567)
     * Format with dashes (e.g., 123-4567)
     * 
     * @param phoneNumber the phone number to be checked
     * @return true if the phone number matches valid formats, otherwise false
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        String noDashFormat = "\\d{7}";      
        String dashFormat = "\\d{3}-\\d{4}";
        
        return phoneNumber != null && (phoneNumber.matches(noDashFormat) || phoneNumber.matches(dashFormat));
    }

	/**
     * Returns the title of the comic.
     * 
     * @return the title of the comic
     */
	public String getTitle() {
		return this.title;
	}
	
	/**
     * Returns the issue number of the comic.
     * 
     * @return the issue number of the comic
     */
	public int getIssueNumber() {
		return this.issueNumber;
	}
	
	/**
	 * Returns a string representation of the comic's details.
	 * 
	 * @return the string representation of the comic.
	 */
    @Override
    public String toString() {
    	return this.title + " (Issue: " + this.issueNumber + ")";
    }
}
