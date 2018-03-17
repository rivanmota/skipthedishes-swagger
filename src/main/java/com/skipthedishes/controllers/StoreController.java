package com.skipthedishes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.Product;
import com.skipthedishes.model.Store;
import com.skipthedishes.services.StoreService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/Store")
public class StoreController {

	private StoreService storeService;

	@Autowired
	public void setStoreservice(StoreService storeservice) {
		storeService = storeservice;
	}

	@ApiOperation(value = "List of Stores", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
							@ApiResponse(code = 401, message = "not authorized"),
							@ApiResponse(code = 403, message = "is forbidden"),
							@ApiResponse(code = 404, message = "not found") })
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Store> list(Model model) {
		Iterable<Store> storeList = storeService.getAll();
		return storeList;
	}

	@ApiOperation(value = "Find store by searchText", response = Store.class)
	@RequestMapping(value = "/search/{searchText}", method = RequestMethod.GET, produces = "application/json")
	public Store searchText(@PathVariable String searchText, Model model) {
		Store store = storeService.getBySearchText(searchText);
		return store;
	}
	
	@ApiOperation(value = "Find store by id", response = Store.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Store searchText(@PathVariable Long id, Model model) {
		Store store = storeService.getById(id);
		return store;
	}

	@ApiOperation(value = "List of products by store id", response = Iterable.class)
	@RequestMapping(value = "/{storeId}/products", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Product> list(@PathVariable Long storeId, Model model) {
		Iterable<Product> productList = storeService.getProductsByStoreId(storeId);
		return productList;
	}
	

}
