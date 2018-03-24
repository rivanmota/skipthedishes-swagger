package com.skipthedishes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.Product;
import com.skipthedishes.repositories.ProductRepository;
import com.skipthedishes.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByNameIgnoreCaseContaining(String searchText) {
        return productRepository.findByNameIgnoreCaseContaining(searchText);
    }
    
    @Override
    public Product getById(Long id) {
    	return productRepository.findOne(id);
    }

	public Product save(Product product) {
		return productRepository.save(product);
	}

}
