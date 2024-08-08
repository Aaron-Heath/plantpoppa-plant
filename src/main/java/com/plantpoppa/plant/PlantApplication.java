package com.plantpoppa.plant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.plantpoppa.plant")
public class PlantApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantApplication.class, args);
	}

}
