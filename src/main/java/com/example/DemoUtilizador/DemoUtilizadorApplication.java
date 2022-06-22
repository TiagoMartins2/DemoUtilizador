package com.example.DemoUtilizador;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@RestController
@EnableSwagger2
@EnableScheduling
public class DemoUtilizadorApplication {

	@RequestMapping("/helloo")
	public String home(){
		return "Hello Docker World";
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoUtilizadorApplication.class, args);
		//ss();
	}

	//Creating bean keycloakConfigResolver
	@Bean
	public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}

}
