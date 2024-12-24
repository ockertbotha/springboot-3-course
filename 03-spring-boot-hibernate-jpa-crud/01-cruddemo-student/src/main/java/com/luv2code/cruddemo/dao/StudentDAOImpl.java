package com.luv2code.cruddemo.dao;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOImpl implements StudentDAO {
  
  // define the entity manager
  private EntityManager entityManager;

  // inject the entity manager using constructor injection
  public StudentDAOImpl(EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }

  // implement the save method
  @Override
  @Transactional
  public void save(Student theStudent) {
    entityManager.persist(theStudent);
  }

  // implement the findById method
  @Override
  public Student findById(int id) {
    return entityManager.find(Student.class, id);
  }

  // implement the findAll method
  @Override
  public List<Student> findAll() {

    //create a query
    TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER BY lastName", Student.class);

    //return the results
    return theQuery.getResultList();
  }

  // implement the findByLastName method
  @Override
  public List<Student> findByLastName(String theLastName) {

    //create a query
    TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
    theQuery.setParameter("theData", theLastName);

    //return the results
    return theQuery.getResultList();
  }

  // implement the update method
  @Override
  @Transactional
  public void update(Student theStudent) {
    entityManager.merge(theStudent);
  }

  // implement the delete method
  @Override
  @Transactional
  public void delete(Integer id) {
    // retrieve the student based on the id
    Student theStudent = entityManager.find(Student.class, id);
    // delete the student
    entityManager.remove(theStudent);
  }

  // implement the deleteAll method
  @Override
  @Transactional
  public int deleteAll() {
    // create a query
    int numRowsDeleted =  entityManager.createQuery("DELETE FROM Student").executeUpdate();
    return numRowsDeleted;
  }
}
