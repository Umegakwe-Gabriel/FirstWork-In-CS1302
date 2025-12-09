package edu.westga.cs1302.comic_manager.model;

public class Comic {
	private String title;
	private int issueNumber;
	
	public Comic(String title, int issueNumber) {
		this.title = title;
		this.issueNumber = issueNumber;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getIssueNumber() {
		return issueNumber;
	}
}
