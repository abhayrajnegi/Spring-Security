package com.eazybytes.springsection1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
public class EazyBankBackendApps {

	public static void main(String[] args) {
		SpringApplication.run(EazyBankBackendApps.class, args);
	}

}
