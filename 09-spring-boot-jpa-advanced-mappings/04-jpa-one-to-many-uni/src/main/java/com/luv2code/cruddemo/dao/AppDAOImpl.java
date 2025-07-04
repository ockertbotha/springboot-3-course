package com.luv2code.cruddemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AppDAOImpl implements AppDAO {

    // define the field for the entity manager
    private EntityManager entityManager;

    
    // inject entity manager using constructor injection
    public AppDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    @Transactional(readOnly = true)
    public Instructor findInstructorById(int theId) {
        // find the instructor by id
        return entityManager.find(Instructor.class, theId);
    }

    @Override  
    @Transactional
    public void deleteInstructorById(int theId) {
        
        // find the instructor by id
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        
        // get the associated courses
        List<Course> courses = tempInstructor.getCourses();

        // break the association for all courses for the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(tempInstructor);

    }

    @Override
    @Transactional(readOnly = true)
    public InstructorDetail findInstructorDetailById(int theId) {
        // find the instructor detail by id
        return entityManager.find(InstructorDetail.class, theId); 
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // find the instructor detail by id
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated instructor if it exists
        // break the bi-directional link
        if (tempInstructorDetail != null)  {
            tempInstructorDetail.getInstructor().setInstructorDetail(null); 
        }

        // delete the instructor detail
        if (tempInstructorDetail != null) {
            // also delete the associated instructor
            entityManager.remove(tempInstructorDetail);
        }
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                            "FROM Course c WHERE c.instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId", theId);    
        
        // execute query and get result list
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    // @Transactional(readOnly = true)
    public Instructor findInstructorByIdJoinFetch(int theId) {
        
        // create query to get instructor with courses using JOIN FETCH
        TypedQuery<Instructor> query = entityManager.createQuery(
                            "SELECT i FROM Instructor i "
                            + "JOIN FETCH i.courses "
                            + "JOIN FETCH i.instructorDetail "
                            + "WHERE i.id = :instructorId", Instructor.class);
        
        query.setParameter("instructorId", theId);  
        
        // execute query and get the result
        Instructor tempInstructor = query.getSingleResult();
        
        // return the instructor
        return tempInstructor;  
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        // update the instructor
        entityManager.merge(theInstructor); 
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        // update the course
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        // find the course by id
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        // find the course by id
        Course tempCourse = entityManager.find(Course.class, theId);
        if (tempCourse != null) {
            // delete the course
            entityManager.remove(tempCourse); 
        }
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        // save the course
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        
        // create query to get course with reviews using JOIN FETCH
        TypedQuery<Course> query = entityManager.createQuery(
                            "SELECT c FROM Course c "
                            + "JOIN FETCH c.reviews "
                            + "WHERE c.id = :courseId", Course.class);
        
        query.setParameter("courseId", theId);  
        
        // execute query and get the result
        Course tempCourse = query.getSingleResult();
        
        // return the course
        return tempCourse;
    }
}
