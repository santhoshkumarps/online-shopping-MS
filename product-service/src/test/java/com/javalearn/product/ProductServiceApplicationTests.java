package com.javalearn.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalearn.product.dto.ProductRequestDto;
import com.javalearn.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	/*@Container
	static	MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");*/

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry propertyRegistry){
		String mongoUri = "mongodb://localhost:27017/product-service";
		propertyRegistry.add("spring.data.mongodb.uri",()->mongoUri);
	}

	@Test
	void shouldCreateProducts() throws Exception {

		ProductRequestDto productRequestDto = getProductRequest();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productRequestDto)))
				.andExpect(status().isCreated());

		Assertions.assertTrue(productRepository.existsByproductSkuCode(productRequestDto.getProductSkuCode()));
	}

	@Test
	void shouldGetProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());
	}

	private ProductRequestDto getProductRequest() {
		return ProductRequestDto.builder()
				.name("Iphone 14")
				.productSkuCode("MOB436")
				.description("Good Apple Mobile to Buy")
				.price(BigDecimal.valueOf(2500.00))
				.build();
	}

}
