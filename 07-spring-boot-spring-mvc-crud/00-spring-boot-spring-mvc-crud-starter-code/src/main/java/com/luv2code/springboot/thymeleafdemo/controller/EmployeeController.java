package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  private EmployeeService employeeService;
  
  public EmployeeController(EmployeeService theEmployeeService) {
    this.employeeService = theEmployeeService;
  }
  
  // add mapping for "/list"
  @RequestMapping("/list")
  public String listEmployees(Model theModel) {
    
    // get employees from db
    List<Employee> theEmployees = employeeService.findAll();  

    // add to the spring model
    theModel.addAttribute("employees", theEmployees);
    
    return "list-employees";
  }

}
