package com.example.cloudbeesproject.cloudbeesproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.cloudbeesproject.cloudbeesproject")
public class CloudbeesprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudbeesprojectApplication.class, args);
	}
}
