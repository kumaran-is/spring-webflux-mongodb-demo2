package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
	title = "Product Management API Swagger Documentation",
	version = "1.0.0",
	contact = @Contact(
			name = "Product API",
			url = "https://github.com/kumaran-is",
			email = "kumaran.isk@gmail.com"
	),
	license = @License(
			name = "Apache 2.0",
			url = "https://www.apache.org/licenses/LICENSE-2.0"
	),
 	description = "This API can be used to add, update, delete and fetch products using spring webflux"
))
public class SpringWebfluxMongodbDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxMongodbDemo2Application.class, args);
	}

}
