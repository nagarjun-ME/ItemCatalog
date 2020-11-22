package com.naga.microservice.api;

import com.naga.microservice.model.Item;
import com.naga.microservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/naga/shop/item/")
public class ItemCatalogApi {

	private final Logger log=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public String sayHello()
	{
		log.info("Inside say hello");
		return "Welcome to item catalog";
	}
	

	@GetMapping("/{pId}")
	public ResponseEntity<Item> getItemList(@PathVariable("pId") long pId)
	{

		log.info("Item with product id  " + pId+ " retrieving");

		return ResponseEntity.ok().body(new Item("TEST001", "Test Items", 4, 12.22,
				Arrays.asList(restTemplate.getForEntity("http://localhost:8400/db/products/"+pId, Product.class,1).getBody())));
	}


	@RequestMapping("/all")
	public ResponseEntity <List <Item>>  getAllItems()
	{

		log.info("Retrieving all Item details");

		ResponseEntity <Product[] > productResponse=restTemplate.getForEntity("http://localhost:8400/db/products/all", Product[].class);

		List <Item> list= new ArrayList<> ();

		Item item=new Item("001ABC", "Technology",2, 20.23, Arrays.asList(productResponse.getBody()));
		list.add(item);
		return ResponseEntity.ok().body(list);
	}


}
