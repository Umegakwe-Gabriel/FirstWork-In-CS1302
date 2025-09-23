package edu.westga.cs1302.cms.persistence;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.cms.model.Student;

/** Supports saving and loading student data,
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class StudentDataPersistenceManager {
	
	/** Save the students!
	 * 
	 * @precondition students != null
	 * @postcondition none
	 * 
	 * @param students the set of students to save
	 * @throws IOException When unable to write to default file location.
	 */
	public static void saveStudentData(Student[] students) throws IOException {
		try (FileWriter writer = new FileWriter("names.txt")) {
		for (Student currStudent : students) {
			writer.write(currStudent.getName() + System.lineSeparator());
			writer.write(currStudent.getGrade());
			writer.write(System.lineSeparator());
		}
		}
	}

	/** Load the students!
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of students loaded
	 * @throws FileNotFoundException unable to find default file
	 * @throws NumberFormatException file is not properly formatted, failed while loading a grade value to integer when reading 
	 * @throws NoSuchElementException file is not formatted properly, failed to read line when trying to read a grade value
	 */
	public static Student[] loadStudentData() throws IOException {
		ArrayList<Student> students = new ArrayList<Student>();
		File inputFile = new File("Names.txt");
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String name = reader.nextLine();
				int grade = Integer.parseInt(reader.nextLine());
				Student nextStudent = new Student(name, grade);
				students.add(nextStudent);
			}
		} catch (NumberFormatException error) {
			throw new IOException(error.getMessage());
		} catch (NoSuchElementException error) {
			throw new IOException(error.getMessage());
		}
		
		return (Student[]) students.toArray();
	}
	
}
