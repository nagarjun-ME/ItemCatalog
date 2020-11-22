package com.naga.microservice.api;

import com.naga.microservice.model.Item;
import com.naga.microservice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/naga/shop/item/")
public class ItemCatalogApi {

	private final Logger log=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	@Value("${db.service.url}")
	private String dbUri;


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
				Arrays.asList(restTemplate.getForEntity(dbUri+pId, Product.class,1).getBody())));
	}


	@GetMapping("/all")
	public ResponseEntity <List <Item>>  getAllItems()
	{

		log.info("Retrieving all Item details");

		ResponseEntity <Product[] > productResponse=restTemplate.getForEntity(dbUri+"/all", Product[].class);

		List <Item> list= new ArrayList<> ();

		Item item=new Item("001ABC", "Technology",2, 20.23, Arrays.asList(productResponse.getBody()));
		list.add(item);
		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping("/rmv/{prodId}")
	public ResponseEntity  <HttpStatus> deleteProductFromItemList(@PathVariable long prodId)
	{
		log.info("Deleting product "+prodId+" from Items");
		ResponseEntity<HttpStatus> responseEntity= webClient.baseUrl(dbUri)
				.build()
				.delete()
				.uri("/rmv/"+prodId)
				.retrieve()
				.toEntity(HttpStatus.class)
				.block();
		log.info("Deleted product "+prodId+" from Items" + responseEntity.getBody()) ;
		return responseEntity;
	}

}
