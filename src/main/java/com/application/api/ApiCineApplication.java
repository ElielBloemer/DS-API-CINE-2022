package com.application.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "PARCIAL DISENIO DE SISTEMAS 2022", version = "1.0", contact = @Contact(name = "Eliel Bloemer Correa",email = "elielbloemercorrea@gmail.com",url = "https://www.linkedin.com/in/elielbloemer/")))
public class ApiCineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCineApplication.class, args);
	}

}
