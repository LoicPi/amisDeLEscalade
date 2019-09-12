package com.adle.projet.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.ListingDAO;
import com.adle.projet.entity.Listing;

@Service
@Transactional
public class ListingServiceImpl implements ListingService {

    @Autowired
    private ListingDAO listingDAO;

    @Override
    public List<Listing> getListings() {
        return listingDAO.getListings();
    }

    @Override
    public Listing getListing( int theId ) {
        return listingDAO.getListing( theId );
    }

    @Override
    public List<Listing> findListingByLevelId( int levelId ) {
        return listingDAO.findListingByLevelId( levelId );
    }

    @Override
    public Map<String, String> getListingNameOfListings( List<Listing> listings ) {
        return listingDAO.getListingNameOfListings( listings );
    }

    @Override
    public Listing findListingByNameOfListing( String nameListing ) {
        return listingDAO.findListingByNameOfListing( nameListing );
    }

}
