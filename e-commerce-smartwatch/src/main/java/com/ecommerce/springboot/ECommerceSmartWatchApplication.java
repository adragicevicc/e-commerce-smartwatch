package com.ecommerce.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ECommerceSmartWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceSmartWatchApplication.class, args);
		
	}

}
