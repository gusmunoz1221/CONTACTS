package com.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiFolcademy2022Application {
	public static void main(String[] args) {
		SpringApplication.run(ApiFolcademy2022Application.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
