package com.skipthedishes.security;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.Customer;
import com.skipthedishes.model.UserAuth;
import com.skipthedishes.repositories.CustomerRepository;

@Service
public class UserAuthService implements UserDetailsService {
	
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
	
}
