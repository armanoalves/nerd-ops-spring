package br.com.nerdops.api_nerdops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ApiNerdopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNerdopsApplication.class, args);
	}
}
