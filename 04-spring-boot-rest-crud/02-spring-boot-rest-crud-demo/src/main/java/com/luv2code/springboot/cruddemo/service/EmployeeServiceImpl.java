package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeDAO employeeDAO;

  public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
    employeeDAO = theEmployeeDAO;
  }

  @Override
  public List<Employee> findAll() {
    return employeeDAO.findAll();
  }


  @Transactional
  @Override
  public void deleteById(int theId) {
    employeeDAO.deleteById(theId);
    
  }

  @Override
  public Employee findById(int theId) {
    return employeeDAO.findById(theId);
  }

  @Transactional
  @Override
  public Employee save(Employee theEmployee) {
    return employeeDAO.save(theEmployee);
  }

}
