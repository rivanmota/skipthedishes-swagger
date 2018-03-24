package com.skipthedishes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.repositories.CustomerRepository;

@RestController
@RequestMapping("/api/v1/Customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	

}
