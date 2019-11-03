package com.jsonJPA.jsonWorking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jsonJPA.jsonWorking.constants.RestConstants;

@SpringBootApplication
public class MainApplication {

	@Autowired
	private RestConstants restConstants;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
