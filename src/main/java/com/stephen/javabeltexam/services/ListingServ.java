package com.stephen.javabeltexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.javabeltexam.models.Listing;
import com.stephen.javabeltexam.repositories.ListingRepo;

@Service
public class ListingServ {
	@Autowired ListingRepo listingRepo;
	
	public List<Listing> allListings(){
		return listingRepo.findAll();
	}
	
	public Listing createListing(Listing listing) {
		return listingRepo.save(listing);
	}
	
	public Listing updateListing(Listing listing) {
		return listingRepo.save(listing);
	}
	
	public void deleteListing(Long id) {
		listingRepo.deleteById(id);
	}
	
	public Listing findListingId(Long id) {
		Optional<Listing> optlist = listingRepo.findById(id);
		if(optlist.isPresent()) {
			return optlist.get();
		}else {
			return null;
		}
	}
	
}
