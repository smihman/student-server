package fr.efrei.studentserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class StudentServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServerApplication.class, args);
	}

}
