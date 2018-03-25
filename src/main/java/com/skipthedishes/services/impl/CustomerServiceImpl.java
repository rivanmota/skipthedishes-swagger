package com.skipthedishes.services.impl;

import static java.util.Collections.emptyList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.Customer;
import com.skipthedishes.model.UserAuth;
import com.skipthedishes.repositories.CustomerRepository;
import com.skipthedishes.services.CustomerService;

@Service
public class CustomerServiceImpl implements UserDetailsService, CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DisabledException {
		Customer customer = customerRepository.findByEmail(email);
		
		if(customer == null)
			throw new UsernameNotFoundException(email);
		
		UserAuth user = new UserAuth(customer.getEmail(), customer.getPassword(), emptyList());
		user.setId(customer.getId());
		user.setName(customer.getName());
		user.setEmail(customer.getEmail());
		
		return user;
	}

	@Override
	public Iterable<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public List<Customer> getByNameIgnoreCaseContaining(String searchText) {
		return customerRepository.findByNameIgnoreCaseContaining(searchText);
	}
	
}
