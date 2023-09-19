package com.padr.gys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class GysApplication {

	public static void main(String[] args) {
		SpringApplication.run(GysApplication.class, args);
	}

}
