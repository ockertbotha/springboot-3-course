package com.luv2code.springcoredemo03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication(
// 		scanBasePackages = {"com.luv2code.springcoredemo02",
// 		                    "com.luv2code.util"}
// )
@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
