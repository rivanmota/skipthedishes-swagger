package com.skipthedishes.services;

import com.skipthedishes.model.Order;

public interface OrderService {

	Iterable<Order> getAll();

	Order order(Order order);

	Order getById(Long id);

}
