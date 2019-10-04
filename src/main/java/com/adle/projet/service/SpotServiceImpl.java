package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.SpotDAO;
import com.adle.projet.entity.Spot;

@Service
@Transactional
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotDAO        spotDAO;

    @Autowired
    private LevelService   levelService;

    @Autowired
    private ListingService listingService;

    @Override
    public List<Spot> getSpots() {
        return spotDAO.getSpots();
    }

    @Override
    public void saveSpot( Spot spot ) {
        spotDAO.saveSpot( spot );

    }

    @Override
    public Spot getSpot( int theId ) {
        return spotDAO.getSpot( theId );
    }

    @Override
    public void updateSpot( Spot spot ) {
        spotDAO.updateSpot( spot );

    }

    @Override
    public List<Spot> findSpotByUserId( int userId ) {
        return spotDAO.findSpotByUserId( userId );
    }

    @Override
    public void deleteSpot( int theId ) {
        spotDAO.deleteSpot( theId );
    }

    @Override
    public void levelOfSpots( List<Spot> spots ) {
        for ( Spot spot : spots ) {
            if ( spot.highLevelId() != 0 && spot.lowLevelId() != 0 ) {
                spot.setHighLevelOfSpot( levelService.getLevel( spot.highLevelId() ).getLevelName() );
                spot.setLowLevelOfSpot( levelService.getLevel( spot.lowLevelId() ).getLevelName() );
            } else {
                spot.setHighLevelOfSpot( "Pas encore de niveau connu." );
                spot.setLowLevelOfSpot( "Pas encore de niveau connu." );
            }
        }
    }

    @Override
    public List<Spot> searchSpots( String nameSpot, String citySpot, int countySpot, String sectorsSpot,
            int listingSpot, int levelSpot ) {
        return spotDAO.searchSpot( nameSpot, citySpot, countySpot, sectorsSpot, listingSpot, levelSpot );
    }

    @Override
    public void listingOfSpots( List<Spot> spots ) {
        for ( Spot spot : spots ) {
            if ( spot.highListingId() != 0 && spot.lowListingId() != 0 ) {
                spot.setHighListingOfSpot( listingService.getListing( spot.highListingId() ).getListingName() );
                spot.setLowListingOfSpot( listingService.getListing( spot.lowListingId() ).getListingName() );
            } else {
                spot.setHighListingOfSpot( "Pas encore de cotation connu." );
                spot.setLowListingOfSpot( "Pas encore de cotation connu." );
            }

        }
    }
}
