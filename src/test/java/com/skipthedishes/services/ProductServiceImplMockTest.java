package com.skipthedishes.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.skipthedishes.model.Product;
import com.skipthedishes.repositories.ProductRepository;
import com.skipthedishes.services.impl.ProductServiceImpl;

public class ProductServiceImplMockTest {

	private ProductServiceImpl productServiceImpl;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private Product product;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		productServiceImpl = new ProductServiceImpl();
		productServiceImpl.setProductRepository(productRepository);
	}

	@Test
	public void returnProductById() throws Exception {
		when(productRepository.findOne(1L)).thenReturn(product);
		Product retrievedProduct = productServiceImpl.getById(1L);
		assertThat(retrievedProduct, is(equalTo(product)));

	}

	@Test
	public void returnProductBySave() throws Exception {
		when(productRepository.save(product)).thenReturn(product);
		Product savedProduct = productServiceImpl.save(product);
		assertThat(savedProduct, is(equalTo(product)));
	}
}