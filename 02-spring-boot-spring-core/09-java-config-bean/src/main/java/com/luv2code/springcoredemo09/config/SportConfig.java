package com.luv2code.springcoredemo09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luv2code.springcoredemo09.common.Coach;
import com.luv2code.springcoredemo09.common.SwimCoach;

@Configuration
public class SportConfig {

  @Bean("aquatic")
  public Coach swimCoach() {
    return new SwimCoach();
  }
}
