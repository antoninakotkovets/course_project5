package com.ajuncodes.coursework;

import com.ajuncodes.coursework.util.ComparisonTextHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseworkApplication.class, args);
	}

	@Bean
	public ComparisonTextHandler comparisonTextHandler() {
		return new ComparisonTextHandler();
	}
}
