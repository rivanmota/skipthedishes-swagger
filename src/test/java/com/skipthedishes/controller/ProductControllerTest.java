package com.skipthedishes.controller;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.skipthedishes.model.Product;
import com.skipthedishes.repositories.ProductRepository;
import com.skipthedishes.security.SecurityConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@MockBean
	private ProductRepository productRepository;
	
//	@Autowired
//	private MockMvc mockMvc;
	
	private HttpEntity<Void> loggedHeader;
	private HttpEntity<Void> adminHeader;
	private HttpEntity<Void> incorrectHeader;

//	@TestConfiguration
//	static class config {
//		public RestTemplateBuilder restTemplateBuilde(){
//			return new RestTemplateBuilder().basicAuthorization("rivanluiz@gmail.com", "123456");
//		}
//	}
	
	@Before
	public void configLoggedHeader() {
		String s = "{\"email\":\"rivanluiz@gmail.com\",\"password\":\"123456\"}";
		HttpHeaders httpHeaders = restTemplate.postForEntity(SecurityConstants.LOGIN_URL, s, String.class).getHeaders();
		loggedHeader = new HttpEntity<>(httpHeaders);
	}
	
	@Before
	public void configAdminHeader() {
		String s = "{\"email\":\"admin\",\"password\":\"skip\"}";
		HttpHeaders httpHeaders = restTemplate.postForEntity(SecurityConstants.LOGIN_URL, s, String.class).getHeaders();
		adminHeader = new HttpEntity<>(httpHeaders);
	}
	
	@Before
	public void configIncorrectHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "wrongValue");
		incorrectHeader = new HttpEntity<>(httpHeaders);
	}
	
	@Before
	public void init() {
		Product product = new Product(1l,1l,"Apple","Red apple", 1.59);
		BDDMockito.when(productRepository.findOne(1l)).thenReturn(product);
	}
	
	@Test
	public void listProductsWhenTokenIsIncorrectShouldReturn403() {
		ResponseEntity<String> response = restTemplate.exchange("/api/v1/Product", HttpMethod.GET, incorrectHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}
	
	@Test
	public void getProductByIdWhenTokenIsIncorrectShouldReturn403() {
		ResponseEntity<String> response = restTemplate.exchange("/api/v1/Product/1", HttpMethod.GET, incorrectHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}
	
	@Test
	public void listProductsWhenTokenIsCorrectShouldReturn200() {
		ResponseEntity<String> response = restTemplate.exchange("/api/v1/Product/", HttpMethod.GET, loggedHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
}

