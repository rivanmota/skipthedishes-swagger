package com.skipthedishes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.model.Cousine;
import com.skipthedishes.model.Store;
import com.skipthedishes.repositories.CousineRepository;
import com.skipthedishes.services.CousineService;

@Service
public class CousineServiceImpl implements CousineService {
	
    private CousineRepository cousineRepository;

    @Autowired
    public void setCousineRepository(CousineRepository cousineRepository) {
		this.cousineRepository = cousineRepository;
    }

    @Override
    public Iterable<Cousine> getAll() {
        return cousineRepository.findAll();
    }

    @Override
    public Cousine getBySearchText(String searchText) {
        return cousineRepository.findByName(searchText);
    }
    
    @Override
    public Cousine getById(Long id) {
    	return cousineRepository.findOne(id);
    }

	public Cousine save(Cousine Cousine) {
		return cousineRepository.save(Cousine);
	}

	@Override
	public Iterable<Store> getStoresByCousineId(Long storeId) {
		return cousineRepository.findStoresByCousineId(storeId);
	}

}
