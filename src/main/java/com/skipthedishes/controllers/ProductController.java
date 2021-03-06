package com.skipthedishes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.Product;
import com.skipthedishes.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/Product")
@Api(value = "Skip", description = "Order API")
public class ProductController {

	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "List of products", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
							@ApiResponse(code = 401, message = "not authorized"),
							@ApiResponse(code = 403, message = "is forbidden"),
							@ApiResponse(code = 404, message = "not found") })
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Product> list(Model model) {
		Iterable<Product> productList = productService.getAll();
		return productList;
	}

	@ApiOperation(value = "Find product by searchText", response = Product.class)
	@RequestMapping(value = "/search/{searchText}", method = RequestMethod.GET, produces = "application/json")
	public Product searchText(@PathVariable String searchText, Model model) {
		Product product = productService.getBySearchText(searchText);
		return product;
	}
	
	@ApiOperation(value = "Find product by id", response = Product.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Product searchText(@PathVariable Long id, Model model) {
		Product product = productService.getById(id);
		return product;
	}

	
	

}
