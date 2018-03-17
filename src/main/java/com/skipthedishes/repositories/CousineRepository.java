package com.skipthedishes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skipthedishes.model.Cousine;
import com.skipthedishes.model.Store;

@RepositoryRestResource
public interface CousineRepository extends CrudRepository<Cousine, Long> {

	Cousine findByName(String searchText);

	Iterable<Store> findStoresByCousineId(Long storeId);

}
