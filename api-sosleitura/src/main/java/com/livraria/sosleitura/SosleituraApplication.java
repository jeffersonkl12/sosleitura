package com.livraria.sosleitura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.livraria.sosleitura.repository"})
@SpringBootApplication
public class SosleituraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosleituraApplication.class, args);
	}

}
