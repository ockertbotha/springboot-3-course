package com.luv2code.cruddemo;

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
			createStudent(studentDAO);
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
}