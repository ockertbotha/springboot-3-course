package com.luv2code.cruddemo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.cruddemo.entity.Instructor;

import jakarta.persistence.EntityManager;

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
        // delete the instructor
        if (tempInstructor != null) {
            entityManager.remove(tempInstructor);
        }
    }

}
