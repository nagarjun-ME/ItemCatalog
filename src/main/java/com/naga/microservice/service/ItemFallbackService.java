package com.naga.microservice.service;

import com.naga.microservice.model.Item;
import com.naga.microservice.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ItemFallbackService {

    @Value("${db.service.url}")
    private String dbUri;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackItemFromProducts")
    public Item getItemFromProducts(long pId) {

        log.info("Calling Microservice to retrieve products");
        return new Item("TEST001", "Test Items", 4, 12.22,
                Arrays.asList(restTemplate.getForEntity(dbUri + pId, Product.class, 1).getBody()));
    }


    private Item getFallbackItemFromProducts(long pId) {
        log.info("Returning default items . There is a problem while retrieving product with id : "+pId);
        return new Item( "Test000", "There are no Items", 0, 0, Arrays.asList(
                new Product(0, "SpringBoot ", "A beginner Guide", 0.0)
        ));
    }
}
