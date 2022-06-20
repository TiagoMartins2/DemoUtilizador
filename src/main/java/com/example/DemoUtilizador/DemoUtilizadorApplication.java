package com.example.DemoUtilizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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


	@Scheduled(fixedRate = 1000*60)
	public static void ss() {
		System.out.println("ugga");
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoUtilizadorApplication.class, args);
		//ss();
	}

}
