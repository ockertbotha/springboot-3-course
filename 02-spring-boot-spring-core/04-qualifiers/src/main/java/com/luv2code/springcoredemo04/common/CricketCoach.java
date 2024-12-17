package com.luv2code.springcoredemo03.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

  @Override
  public String getDailyWorkout() {
    return "Practice fast bowling for 15 minutes!!";
  }
}
