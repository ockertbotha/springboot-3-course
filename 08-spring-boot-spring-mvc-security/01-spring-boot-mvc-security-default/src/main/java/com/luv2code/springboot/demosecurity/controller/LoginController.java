package com.luv2code.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

  @GetMapping("/showMyLoginPage")
  public String showMyLoginPage() {
      return "fancy-login";
  }
  public String getMethodName(@RequestParam String param) {
      return new String();
  }
  
}
