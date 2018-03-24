package com.skipthedishes.services;

import java.util.List;

import com.skipthedishes.model.Product;

public interface ProductService {

	Iterable<Product> getAll();

	Product getById(Long id);

	List<Product> getByNameIgnoreCaseContaining(String searchText);

}
