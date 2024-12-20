package com.luv2code.springcoredemo09.common;

public class SwimCoach implements Coach {

  public SwimCoach() {
    System.out.println("In contructor: SwimCoach");
  }

  @Override
  public String getDailyWorkout() {
    return "Swim 1000 meters as a warm up.";
  }

}
