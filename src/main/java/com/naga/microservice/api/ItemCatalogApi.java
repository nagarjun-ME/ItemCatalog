package com.naga.microservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ItemCatalogApi {

	@GetMapping("/")
	public String Hello()
	{
		return "String";
	}
}
