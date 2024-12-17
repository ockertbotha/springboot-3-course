package com.luv2code.springcoredemo05.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach  implements Coach {

  @Override
  public String getDailyWorkout() {
    return "Run a hard 5k!";
  }
  
}