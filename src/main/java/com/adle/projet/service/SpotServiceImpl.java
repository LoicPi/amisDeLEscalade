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
    private SpotDAO spotDAO;

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

}
