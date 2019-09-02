package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Listing;

public interface ListingDAO {

    public List<Listing> getListings();

    public void saveListing( Listing listing );

    public void deleteListing( int theId );

    public Listing getListing( int theId );

    public List<Listing> findListingByLevelId( int levelId );

}
