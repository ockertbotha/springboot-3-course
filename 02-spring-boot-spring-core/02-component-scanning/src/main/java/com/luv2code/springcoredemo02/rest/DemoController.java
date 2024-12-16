package com.luv2code.springcoredemo02.rest;

import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springcoredemo02.common.Coach;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {

  // define a private field for the dependency
  private Coach myCoach;

  // define a constructor for dependency inhection
  public DemoController(Coach theCoach){
    myCoach = theCoach;
  }

  @GetMapping("/dailyworkout")
  public String getDailyWorkout() {
      return myCoach.getDailyWorkout();
  }
}
