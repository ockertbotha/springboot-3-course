package com.luv2code.cruddemo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;

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

}
