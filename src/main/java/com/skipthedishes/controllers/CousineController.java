package com.skipthedishes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.model.Cousine;
import com.skipthedishes.model.Store;
import com.skipthedishes.services.CousineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/Cousine")
@Api(value = "Cousine")
public class CousineController {

	private CousineService cousineService;

	@Autowired
	public void setCousineService(CousineService cousineService) {
		this.cousineService = cousineService;
	}

	@ApiOperation(value = "List of cousines", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
							@ApiResponse(code = 401, message = "not authorized"),
							@ApiResponse(code = 403, message = "is forbidden"),
							@ApiResponse(code = 404, message = "not found") })
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Cousine> list(Model model) {
		Iterable<Cousine> CousineList = cousineService.getAll();
		return CousineList;
	}

	@ApiOperation(value = "Find Cousine by searchText", response = Cousine.class)
	@RequestMapping(value = "/search/{searchText}", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Cousine> searchText(@PathVariable String searchText, Model model) {
		Iterable<Cousine> cousines = cousineService.getBySearchText(searchText);
		return cousines;
	}
	
	@ApiOperation(value = "List of stores by cousine id", response = Iterable.class)
	@RequestMapping(value = "/{cousineId}/stores", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Store> list(@PathVariable Long storeId, Model model) {
		Iterable<Store> storeList = cousineService.getStoresByCousineId(storeId);
		return storeList;
	}

}
