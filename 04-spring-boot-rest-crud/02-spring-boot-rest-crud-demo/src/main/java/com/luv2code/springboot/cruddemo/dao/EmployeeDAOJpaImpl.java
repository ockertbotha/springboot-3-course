package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

  // define field for entity manager
  private EntityManager entityManager;

  // set up constructor injection
  @Autowired
  public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
    entityManager = theEntityManager;
  } 

  @Override
  public List<Employee> findAll() {
    
    // create a query
    TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class); 
    
    // execute query and get result list
    List<Employee> employees = theQuery.getResultList();

    // return the results
    return employees;
  }

}
