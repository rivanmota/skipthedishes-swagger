package com.skipthedishes.services;

import java.util.List;

import com.skipthedishes.model.Customer;

public interface CustomerService {

	Iterable<Customer> getAll();

	Customer getById(Long id);

	List<Customer> getByNameIgnoreCaseContaining(String searchText);

}
