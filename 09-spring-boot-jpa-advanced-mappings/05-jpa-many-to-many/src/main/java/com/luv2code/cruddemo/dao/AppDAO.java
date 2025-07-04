package com.luv2code.cruddemo.dao;

import java.util.List;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

public interface AppDAO {

  void save(Instructor theInstructor);

  Instructor findInstructorById(int theId);

  void deleteInstructorById(int theId);

  InstructorDetail findInstructorDetailById(int theId);

  void deleteInstructorDetailById(int theId);

  List<Course> findCoursesByInstructorId(int theId);

  Instructor findInstructorByIdJoinFetch(int theId);

  void update(Instructor theInstructor);

  void update(Course theCourse);

  Course findCourseById(int theId);

  void deleteCourseById(int theId);

  void save(Course theCourse);

  Course findCourseAndReviewsByCourseId(int theId);

  Course findCourseAndStudentsByCourseId(int theId);

  Student findStudentAndCoursesByStudentId(int theId);

  void update(Student theStudent);

  void deleteStudentById(int theId);
}
