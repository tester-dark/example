package org.om.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class ExampleV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ExampleV1Application.class, args);
	}
}