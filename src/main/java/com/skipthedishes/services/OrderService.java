package com.skipthedishes.services;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Order;

@RepositoryRestResource
public interface OrderService {

	Iterable<Order> getAll();

	Order order(Order order);

	Order getById(Long id);
	
}
