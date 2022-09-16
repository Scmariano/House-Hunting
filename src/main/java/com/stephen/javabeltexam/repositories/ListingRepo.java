package com.stephen.javabeltexam.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.javabeltexam.models.Listing;

@Repository
public interface ListingRepo extends CrudRepository<Listing, Long>{
	public List<Listing>findAll();
	
}
