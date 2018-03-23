package com.skipthedishes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Cousine;
import com.skipthedishes.model.Store;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cousine Entity")
@RepositoryRestResource(path="cousine")
public interface CousineRepository extends CrudRepository<Cousine, Long> {

	@ApiOperation("find all Cousines by name")
	@ApiResponses({@ApiResponse(code=200, message="Success", response=Cousine.class)})
	Iterable<Cousine> findByName(@ApiParam(value="Name to be searched") String searchText);

	@ApiOperation("find all Stores by Cousines ID")
	Iterable<Store> findStoresByCousineId(@ApiParam(value="Cousine ID") Long storeId);

}
