package com.cred.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootJpaOneToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaOneToManyApplication.class, args);
	}

}
