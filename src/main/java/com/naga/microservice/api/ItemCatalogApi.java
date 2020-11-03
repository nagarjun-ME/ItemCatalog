package com.naga.microservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemCatalogApi {

	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/")
	public String sayHello()
	{
		log.info("Inside " + this.toString());
		return "String";
	}
	

	@GetMapping("/{item}")
	public String getItem(@PathVariable("item") String itemname)
	{
		log.info("Itemname " + this.toString());
		return "Item name" + itemname;
	}
	
}
