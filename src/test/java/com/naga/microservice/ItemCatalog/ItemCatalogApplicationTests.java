package com.naga.microservice.ItemCatalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.naga.microservice.itemcatalog.ItemCatalogApplication;

@SpringBootConfiguration
@SpringBootTest
class ItemCatalogApplicationTests {


	@Test
	public void applicationContextLoaded() {
	}

	@Test
	public void applicationContextTest() {
		ItemCatalogApplication.main(new String[] {});
	}

}
