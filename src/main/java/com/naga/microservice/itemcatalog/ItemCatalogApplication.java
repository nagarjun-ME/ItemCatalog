package com.naga.microservice.itemcatalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.naga.microservice.api")
@SpringBootApplication
public class ItemCatalogApplication {

	private static Logger log=LoggerFactory.getLogger(ItemCatalogApplication.class);
	
	public static void main(String[] args) {
		
		log.info("Main method beginning");
		SpringApplication.run(ItemCatalogApplication.class, args);
		
		log.info("Main method Ending");
	}

}
