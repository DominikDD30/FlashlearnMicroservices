package com.dominik.quizservice;

import org.springframework.boot.SpringApplication;

public class TestQuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(QuizServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
