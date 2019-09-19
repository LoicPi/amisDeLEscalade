package com.adle.projet.service;

import java.util.List;
import java.util.Map;

import com.adle.projet.entity.Listing;

public interface ListingService {

    public List<Listing> getListings();

    public Listing getListing( int theId );

    public List<Listing> findListingByLevelId( int levelId );

    public Map<Integer, String> getListingNameOfListings( List<Listing> listings );

}
