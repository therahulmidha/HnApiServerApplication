package com.hn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * Main class for the application
 * 
 * @author Rahul Midha
 */
@SpringBootApplication
@EnableCaching
public class HnApiServerApplication {

	/**
	 * main method for starting the application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(HnApiServerApplication.class, args);
	}

}
