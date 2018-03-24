package com.skipthedishes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Product;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long>{

	List<Product> findByNameIgnoreCaseContaining(String searchText);
	
}
