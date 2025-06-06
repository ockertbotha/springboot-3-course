package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController02 {

  // expose "/" that returns "Hello World"
  @GetMapping("/")
  public String sayHello() {
      return "Hello World!";
  }

  // expose a new endoint for "workout"
  @GetMapping("/workout")
  public String getDailyWorkout() {
      return "Run a hard 5k!";
  }

  // expose a new endoint for "fortune"
  @GetMapping("/fortune")
  public String getDailyFortune() {
      return "Today is your lucky day.";
  }

  // expose a new endoint for "rugby"
  @GetMapping("/rugby")
  public String getRugby() {
      return "Rugby rocks!";
  } 
  
}
