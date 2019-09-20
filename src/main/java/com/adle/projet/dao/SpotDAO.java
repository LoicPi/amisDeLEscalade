package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Spot;

public interface SpotDAO {

    public List<Spot> getSpots();

    public void saveSpot( Spot spot );

    public Spot getSpot( int theId );

    public void updateSpot( Spot spot );

    public List<Spot> findSpotByUserId( int userId );

    public void deleteSpot( int theId );

    public List<Spot> findSpotWithAllInfo( List<Spot> spots );

}
