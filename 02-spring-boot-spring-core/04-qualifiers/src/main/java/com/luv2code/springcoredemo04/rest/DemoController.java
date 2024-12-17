package com.luv2code.springcoredemo04.rest;

import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springcoredemo04.common.Coach;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DemoController {

  // define a private field for the dependency
  private Coach myCoach;

  public DemoController(@Qualifier("cricketCoach") Coach theCoach) {
    myCoach = theCoach;  
  }

  @GetMapping("/dailyworkout")
  public String getDailyWorkout() {
      return myCoach.getDailyWorkout();
  }
}
