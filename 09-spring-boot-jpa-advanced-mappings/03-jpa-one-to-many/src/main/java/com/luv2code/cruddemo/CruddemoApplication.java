package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
				// createInstructor(appDAO);
				
				//findInstructor(appDAO);

				// deleteInstructor(appDAO);

				// findInstructorDetail(appDAO);

				//deleteInstructorDetail(appDAO);

				createInstructorWithCourses(appDAO);
		};
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = 
			new Instructor("Harry", "Hekoru", "harry@somewhere.com");

		// create the instructor detail 
		InstructorDetail tempInstructorDetail = 
			new InstructorDetail("http://www.youtube.com", "Racing!!");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		
		// create some courses
		Course tempCourse1 = new Course("Grip - How not to understeer!");
		Course tempCourse2 = new Course("Power - Hitting the red line!");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		// NOTE: this will also save the courses
		// because of CascadeType.PERSIST
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("Saving courses: " + tempCourse1 + ", " + tempCourse2);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		// delete the instructor detail with id 3
		int theId = 8;
		System.out.println("Deleting instructor detail with id: " + theId);
		
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// find the instructor detail by id
		int theId = 3;
		System.out.println("Finding instructor detail with id: " + theId);
		
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		
		System.out.println("Found instructor detail: " + tempInstructorDetail);
		
		System.out.println("Associated instructor: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		// delete the instructor with id 6
		int theId = 2;
		System.out.println("Deleting instructor with id: " + theId);
		
		appDAO.deleteInstructorById(theId);
		
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		// find the instructor by id
		int theId = 6;
		System.out.println("Finding instructor with id: " + theId);
		
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		
		System.out.println("Found instructor: " + tempInstructor);
		
		System.out.println("Associated instructor detail: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		
		/* 
		// create the instructor
		Instructor tempInstructor = 
			new Instructor("John", "Doe", "john.doe@somewhere.com");

		// create the instructor detail 
		InstructorDetail tempInstructorDetail = 
			new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!");
		*/

				// create the instructor
		Instructor tempInstructor = 
			new Instructor("Madhus", "Patels", "madhus@luv2code.com");

		// create the instructor detail 
		InstructorDetail tempInstructorDetail = 
			new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		// NOTE: this will also save the details object
		// because of CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Saved instructor: " + tempInstructor);
		System.out.println("Saved instructor detail: " + tempInstructorDetail);
		System.out.println("Done!");
	}
}
