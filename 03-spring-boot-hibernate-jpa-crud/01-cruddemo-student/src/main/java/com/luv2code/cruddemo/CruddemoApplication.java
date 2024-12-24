package com.luv2code.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return _ -> {
			// createStudent(studentDAO);
			
			// createMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			// queryForStudentsByLastName(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			deleteAllStudents(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		//create a student object
		System.out.println("Creating a new student object...");
		Student tempoStudent = new Student("Daffy", "Duck", "daffy.duck@warnerbros.com");

		//save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempoStudent);

		//display id of the student
		System.out.println("Saved student. Generated id: " + tempoStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating 3 student objects...");
		Student student1 = new Student("Bugs", "Bunny", "bugs.bunny@warnerbros.com"); 
		Student student2 = new Student("Elmer", "Fudd", "elmer.fudd@warnerbros.com"); 
		Student student3 = new Student("Porky", "Pig", "proky.pig@warnerbros.com"); 

		// save the students
		System.out.println("Saving the students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		Student tempStudent = new Student("Bugs", "Bunny", "bugs.bunny@warnerbros.com"); 

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);
		
		// find the student
		System.out.println("Finding the student with id[" + theId + "]...");
		Student theStudent = studentDAO.findById(theId);

		// display the student
		System.out.println("Found student: " + theStudent);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		//	display the students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {	
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Bunny");

		//	display the students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve the student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student theStudent = studentDAO.findById(studentId);
		
		// change the first name to Scooby
		System.out.println("Updating student...");
		theStudent.setFirstName("Buggs");

		// update the student
		studentDAO.update(theStudent);

		// display the updated student
		System.out.println("Updated student: " + theStudent);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// retrieve the student based on the id: primary key
		int studentId = 3;
		System.out.println("Deleting student id:" + studentId);
		studentDAO.delete(studentId);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		// delete all students
		System.out.println("Deleting all students...");
		int numRowsDelete = studentDAO.deleteAll();
		System.out.println("Number of rows deleted: " + numRowsDelete);
	}
}