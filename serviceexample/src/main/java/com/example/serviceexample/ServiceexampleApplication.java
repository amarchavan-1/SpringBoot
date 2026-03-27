package com.example.serviceexample;

import com.example.serviceexample.service.FactorialService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ServiceexampleApplication {

	public static void main(String[] args) {
		// Normally we would start Spring Boot with this line (web/server apps):
		// SpringApplication.run(ServiceexampleApplication.class, args);
		// But here we are manually controlling the Spring container, so we comment it out.

		// Create an empty Spring container (ApplicationContext)
		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext();

		// Tell Spring which package to scan for beans (@Service, @Component, @Repository, etc.)
		annotationConfigApplicationContext.scan("com.example.serviceexample");

		// Refresh the context to initialize all beans and prepare the container
		annotationConfigApplicationContext.refresh();

		// Retrieve the FactorialService bean from the Spring container
		FactorialService factorialService =
				annotationConfigApplicationContext.getBean(FactorialService.class);

		// Call the factorial method and print the result
		System.out.println("Factorial of 5 is: " + factorialService.factorial(5));

		// Close the Spring container to release resources
		annotationConfigApplicationContext.close();
	}
}
