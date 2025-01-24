package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class HelloWorldController {

  // new controller method to show the initial HTML form
  @RequestMapping("/showForm")
  public String requestMethodName() {
    return "helloworld-form";
  }

  // new controller method to process the HTML form
  @RequestMapping("/processForm")
  public String processForm() {
    return "helloworld";
  }

  // need a controller method to read form data
  // add data to the model

  @RequestMapping("/processFormV2")
  public String letsShoutDude(HttpServletRequest request, Model model) {
    
    // read the request parameter from the HTML form
    String theName = request.getParameter("studentName");

    // convert the data to all caps
    theName = theName.toUpperCase();

    // create the message
    String result = "Yo! " + theName;

    // add message to the model
    model.addAttribute("message", result);

    return "helloworld";
  }

}
