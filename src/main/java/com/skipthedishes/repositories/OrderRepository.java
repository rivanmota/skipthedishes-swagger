package com.skipthedishes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Order;

@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Long>{
}
