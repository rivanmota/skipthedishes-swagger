package com.skipthedishes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.Order;
import com.skipthedishes.repositories.OrderRepository;
import com.skipthedishes.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
    private OrderRepository orderRepository;

    @Autowired
    public void setStoreRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

	@Override
	public Iterable<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order order(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order getById(Long id) {
		return orderRepository.findOne(id);
	}


}
