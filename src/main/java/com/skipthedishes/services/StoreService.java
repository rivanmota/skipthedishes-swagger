package com.skipthedishes.services;

import com.skipthedishes.model.Product;
import com.skipthedishes.model.Store;

public interface StoreService {

	Iterable<Store> getAll();

	Store getBySearchText(String SearchText);

	Store getById(Long id);

	Iterable<Product> getProductsByStoreId(Long id);

}
