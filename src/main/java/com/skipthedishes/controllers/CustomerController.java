package com.skipthedishes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.Customer;
import com.skipthedishes.services.CustomerService;
import com.skipthedishes.services.impl.CustomerServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/Customer")
@Api("Customer")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerServiceImpl customerServiceImpl) {
		this.customerService = customerServiceImpl;
	}

	@ApiOperation(value = "List of customers", response = Customer[].class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
							@ApiResponse(code = 401, message = "not authorized"),
							@ApiResponse(code = 403, message = "is forbidden"),
							@ApiResponse(code = 404, message = "not found") })
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="Bearer token", required=true, dataType="string",  paramType="header")
	})
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Customer> list(Model model) {
		Iterable<Customer> customersList = customerService.getAll();
		return customersList;
	}

}
