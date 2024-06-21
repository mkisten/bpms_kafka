package ru.ertelecom.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//(scanBasePackages = "ru.ertelecom.demo")
//@EnableJpaRepositories(basePackages = "ru.ertelecom.demo.repository")
//@EntityScan(basePackages = "ru.ertelecom.demo.model")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
