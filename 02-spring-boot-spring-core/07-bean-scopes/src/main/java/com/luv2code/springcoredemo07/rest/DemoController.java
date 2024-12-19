package com.luv2code.springcoredemo07.rest;

import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springcoredemo07.common.Coach;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class DemoController {

  // define a private field for the dependency
  private Coach myCoach;
  private Coach anotherCoach;

  public DemoController(
              @Qualifier("cricketCoach") Coach theCoach,
              @Qualifier("cricketCoach") Coach theAnotherCoach) {

    System.out.println("In constructor: " + getClass().getSimpleName());

    myCoach = theCoach;
    anotherCoach = theAnotherCoach;  
  }

  @GetMapping("/dailyworkout")
  public String getDailyWorkout() {
      return myCoach.getDailyWorkout();
  }

  @GetMapping("/check")
  public String check() {
      return "Comparing beans: myCoash == anotherCoach, " + (myCoach == anotherCoach);
  }
}
