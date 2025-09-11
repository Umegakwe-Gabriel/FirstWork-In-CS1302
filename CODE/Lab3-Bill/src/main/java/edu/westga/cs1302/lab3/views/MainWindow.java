package edu.westga.cs1302.lab3.views;

import edu.westga.cs1302.lab3.model.Bill;
import edu.westga.cs1302.lab3.model.BillItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML
    private TextField amount;

    @FXML
    private TextField name;

    @FXML
    private TextArea output;

    @FXML
    private void addItem(ActionEvent event) {
    	try {
    		//(a) Retrieve name and amount
    		String name = this.name.getText();
    		double amount = Double.parseDouble(this.amount.getText());
    		
    		//(b) Create BillItem and add it to the bill
    		BillItem item = new BillItem(name, amount);
    		this.bill.addItem(item);
    		
    		//(c) Generate the Bill text
    		String billText = this.billView.getText(this.bill);
    		
    		//(d) Display the text in the TextArea
    		this.output.setText(billText);
    		
    		//Clear input fields for next entry
    		this.name.clear();
    		this.amount.clear();
    		
    	} catch(NumberFormatException e) {
    		this.amount.setText("Error: Amount must be a number.");
    	} catch (IllegalArgumentException e) {
    		this.amount.setText("Error: " + e.getMessage());
    	}
    }
    
    private Bill bill;
    private BillView billView;
    
    /**
     * Perform any needed initialization of UI components and underlying objects.
     */
    public void initialize() {
    	this.bill = new Bill();
    	this.billView = new BillView();
    }
}
