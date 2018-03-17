package com.skipthedishes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.Order;
import com.skipthedishes.services.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/Order")
public class OrderController {

	private OrderService orderService;

	@Autowired
	public void setStoreservice(OrderService storeservice) {
		orderService = storeservice;
	}

	@ApiOperation(value = "Order", response = Order.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
							@ApiResponse(code = 401, message = "not authorized"),
							@ApiResponse(code = 403, message = "is forbidden"),
							@ApiResponse(code = 404, message = "not found") })
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public Order order(@RequestBody Order order, Model model) {
		return orderService.order(order);
	}

	@ApiOperation(value = "Order customer", response = Order.class)
	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
	public Order curstomer(Model model) {
//		return orderService. TODO
		return null;
	}
	
	@ApiOperation(value = "Find order by id", response = Order.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Order searchText(@PathVariable Long id, Model model) {
		return orderService.getById(id);
	}


}
