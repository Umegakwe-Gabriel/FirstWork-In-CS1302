//package edu.westga.cs1302.lab5.persistence.student_data_persistence_manager;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.FileNotFoundException;
//import java.util.List;
//
//import edu.westga.cs1302.lab5.model.Student;
//import edu.westga.cs1302.lab5.persistence.StudentManager;
//
//import org.junit.jupiter.api.Test;
//
//class TestLoadStudentData {
//	
//	private File file;
//
//	@Test
//	void testLoadStudentsReadsCorrectFormat() throws IOException {
//		this.file = File.createTempFile("students", ".csv");
//		FileWriter writer = new FileWriter(this.file);
//		writer.write("Alice,90\nBob,75\n");
//		writer.close();
//
//		StudentManager manager = new StudentManager();
//		manager.loadStudents(this.file);
//
//		List<Student> students = manager.getStudents();
//		assertEquals(2, students.size());
//		assertEquals("Alice", students.get(0).getName());
//		assertEquals(90, students.get(0).getGrade());
//		assertEquals("Bob", students.get(1).getName());
//		assertEquals(75, students.get(1).getGrade());
//	}
//
//	@Test
//	void testLoadStudentsClearsPreviousData() throws IOException {
//		StudentManager manager = new StudentManager();
//		manager.addStudent(new Student("OldStudent", 100));
//
//		this.file = File.createTempFile("students", ".csv");
//		FileWriter writer = new FileWriter(this.file);
//		writer.write("NewStudent,70\n");
//		writer.close();
//
//		manager.loadStudents(this.file);
//
//		List<Student> students = manager.getStudents();
//		assertEquals(1, students.size());
//		assertEquals("NewStudent", students.get(0).getName());
//		assertEquals(70, students.get(0).getGrade());
//	}
//
//	@Test
//	void testLoadStudentsWithNullFileThrowsException() {
//		StudentManager manager = new StudentManager();
//
//		assertThrows(IllegalArgumentException.class, () -> {
//			manager.loadStudents(null);
//		});
//	}
//
//	@Test
//	void testLoadStudentsFromEmptyFileLoadsZeroStudents() throws IOException {
//		this.tempFile = File.createTempFile("students", ".csv");
//		// file is empty
//
//		StudentManager manager = new StudentManager();
//		manager.loadStudents(this.tempFile);
//
//		assertTrue(manager.getStudents().isEmpty());
//	}
//
//	@Test
//	void testLoadStudentsFromMalformedFileThrowsException() throws IOException {
//		this.file = File.createTempFile("students", ".csv");
//		FileWriter writer = new FileWriter(this.file);
//		writer.write("Alice,90\nInvalidLine\nBob,85\n");
//		writer.close();
//
//		StudentManager manager = new StudentManager();
//
//		assertThrows(IllegalArgumentException.class, () -> {
//			manager.loadStudents(this.file);
//		});
//	}
//
//	@Test
//	void testLoadStudentsWithNonExistentFileThrowsException() {
//		StudentManager manager = new StudentManager();
//		File nonExistent = new File("does_not_exist.csv");
//
//		assertThrows(IOException.class, () -> {
//			manager.loadStudents(nonExistent);
//		});
//	}
//
//	@Test
//	void testLoadStudentsWithNegativeGradeThrowsException() throws IOException {
//		this.file = File.createTempFile("students", ".csv");
//		FileWriter writer = new FileWriter(this.file);
//		writer.write("Alice,-10\n");
//		writer.close();
//
//		StudentManager manager = new StudentManager();
//
//		assertThrows(IllegalArgumentException.class, () -> {
//			manager.loadStudents(this.file);
//		});
//	}
//}
//
//}
