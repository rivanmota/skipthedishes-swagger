package com.skipthedishes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Customer;

@RepositoryRestResource
public interface CustomerRepository extends CrudRepository<Customer, Long>{

	Customer findByEmail(String email);

}
