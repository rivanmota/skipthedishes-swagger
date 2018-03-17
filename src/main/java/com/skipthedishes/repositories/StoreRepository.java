package com.skipthedishes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Product;
import com.skipthedishes.model.Store;

@RepositoryRestResource
public interface StoreRepository extends CrudRepository<Store, Long>{

	Store findByName(String searchText);

	Iterable<Product> findProductsByStoreId(Long id);
}
