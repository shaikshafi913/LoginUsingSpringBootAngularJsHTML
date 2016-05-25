package com.dhboa.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class UserDemoApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SpringApplication.run(UserDemoApplication.class,args);

	}

}
