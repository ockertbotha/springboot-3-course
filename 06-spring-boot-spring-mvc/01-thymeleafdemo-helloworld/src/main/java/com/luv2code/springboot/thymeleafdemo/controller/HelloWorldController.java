package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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

}
