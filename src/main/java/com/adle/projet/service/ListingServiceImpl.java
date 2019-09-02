package com.adle.projet.service;

import java.util.List;

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
    public void saveListing( Listing listing ) {
        listingDAO.saveListing( listing );

    }

    @Override
    public void deleteListing( int theId ) {
        listingDAO.deleteListing( theId );

    }

    @Override
    public Listing getListing( int theId ) {
        return listingDAO.getListing( theId );
    }

    @Override
    public List<Listing> findListingByLevelId( int levelId ) {
        return listingDAO.findListingByLevelId( levelId );
    }

}
