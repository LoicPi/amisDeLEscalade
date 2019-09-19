package com.adle.projet.dao;

import java.util.List;
import java.util.Map;

import com.adle.projet.entity.Listing;

public interface ListingDAO {

    public List<Listing> getListings();

    public Listing getListing( int theId );

    public List<Listing> findListingByLevelId( int levelId );

    public Map<Integer, String> getListingNameOfListings( List<Listing> listings );

}
