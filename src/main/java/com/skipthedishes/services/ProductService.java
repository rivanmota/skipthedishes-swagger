package com.skipthedishes.services;

import com.skipthedishes.model.Product;

public interface ProductService {
	
	Iterable<Product> getAll();

	Product getById(Long id);

	Product getBySearchText(String SearchText);

}
