package com.skipthedishes.services;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Product;
import com.skipthedishes.model.Store;

@RepositoryRestResource
public interface StoreService {
	
	Iterable<Store> getAll();

	Store getBySearchText(String SearchText);

	Store getById(Long id);
	
	Iterable<Product> getProductsByStoreId(Long id);

}
