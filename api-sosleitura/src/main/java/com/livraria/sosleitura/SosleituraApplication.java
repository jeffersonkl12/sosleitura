package com.livraria.sosleitura;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
public class SosleituraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosleituraApplication.class, args);
	}

}
