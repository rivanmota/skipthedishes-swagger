package com.skipthedishes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.Product;
import com.skipthedishes.model.Store;
import com.skipthedishes.repositories.StoreRepository;
import com.skipthedishes.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	
    private StoreRepository storeRepository;

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Iterable<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store getBySearchText(String searchText) {
        return storeRepository.findByName(searchText);
    }
    
    @Override
    public Store getById(Long id) {
    	return storeRepository.findOne(id);
    }

	@Override
	public Iterable<Product> getProductsByStoreId(Long id) {
		return storeRepository.findProductsByStoreId(id);
	}

}
