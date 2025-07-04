package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createCourseAndReviews(appDAO);

			// retrieveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		// delete the course and associated reviews
		int theId = 10;
		System.out.println("Deleting course with id: " + theId);
		
		appDAO.deleteCourseById(theId);
		
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// retrieve the course
		int theId = 10;
		System.out.println("Finding course with id: " + theId);
		
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		
		// display the course and associated reviews
		System.out.println("Found course: " + tempCourse);
		System.out.println("Associated reviews: " + tempCourse.getReviews());
		
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("How to drift like a pro!");

		// add some reviews
		tempCourse.addReview(new Review("Great course!"));
		tempCourse.addReview(new Review("Loved it!"));
		tempCourse.addReview(new Review("I want my money back!"));

		// save the course... and leverage the cascade all
		System.out.println("Saving course: " + tempCourse);
		System.out.println("With reviews: " + tempCourse.getReviews());
		appDAO.save(tempCourse);
		
		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		// delete the course
		int theId = 13;
		System.out.println("Deleting course with id: " + theId);
		
		appDAO.deleteCourseById(theId);
		
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		// find the course by id
		int theId = 12;
		System.out.println("Finding course with id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);
		
		// update the course
		System.out.println("Updating course: " + tempCourse);
		tempCourse.setTitle("Advanced Racing Techniques");
		appDAO.update(tempCourse);

		System.out.println("Updated course: " + tempCourse);
		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		
		// find the instructor by id
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("Updating instructor: " + tempInstructor);
		
		// update the instructor
		tempInstructor.setLastName("TESTER");
		appDAO.update(tempInstructor);

		System.out.println("Updated instructor: " + tempInstructor);
		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		// find the instructor by id
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
		
		System.out.println("Found instructor: " + tempInstructor);
		System.out.println("Associated instructor detail: " + tempInstructor.getInstructorDetail());
		System.out.println("Associated courses: " + tempInstructor.getCourses());
		
		System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		// find the instructor by id
		int theId = 1;
		System.out.println("Finding courses for instructor with id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("Found instructor: " + tempInstructor);
		
		// find the courses for the instructor
		var courses = appDAO.findCoursesByInstructorId(theId);
		System.out.println("Found courses: " + courses);

		//Associate the courses with the instructor
		tempInstructor.setCourses(courses);
		System.out.println("Associated courses with instructor: " + tempInstructor.getCourses());
		
		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		// find the instructor by id
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		
		System.out.println("Found instructor: " + tempInstructor);
		System.out.println("Associated instructor detail: " + tempInstructor.getInstructorDetail());
		System.out.println("Associated courses: " + tempInstructor.getCourses());
		
		System.out.println("Done!");
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
