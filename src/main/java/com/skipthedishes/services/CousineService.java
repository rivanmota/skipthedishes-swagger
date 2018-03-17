package com.skipthedishes.services;

import com.skipthedishes.model.Cousine;
import com.skipthedishes.model.Store;

public interface CousineService {
	
	Iterable<Cousine> getAll();

	Cousine getById(Long id);

	Cousine getBySearchText(String SearchText);

	Iterable<Store> getStoresByCousineId(Long storeId);

}
