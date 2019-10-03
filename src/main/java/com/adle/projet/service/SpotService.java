package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Spot;

public interface SpotService {

    public List<Spot> getSpots();

    public void saveSpot( Spot spot );

    public Spot getSpot( int theId );

    public void updateSpot( Spot spot );

    public List<Spot> findSpotByUserId( int userId );

    public void deleteSpot( int theId );

    public void levelOfSpots( List<Spot> spots );

    public List<Spot> searchSpots( String nameSpot, String citySpot, int countySpot, String sectorsSpot,
            int listingSpot, int levelSpot );

    public void listingOfSpots( List<Spot> spots );
}
