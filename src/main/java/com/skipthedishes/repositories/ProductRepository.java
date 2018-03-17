package com.skipthedishes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long>{

	Product findByName(String searchText);
	
}
