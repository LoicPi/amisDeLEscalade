package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.SectorDAO;
import com.adle.projet.entity.Sector;

@Service
@Transactional
public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorDAO      sectorDAO;

    @Autowired
    private LevelService   levelService;

    @Autowired
    private ListingService listingService;

    @Override
    public List<Sector> getSectors() {
        return sectorDAO.getSectors();
    }

    @Override
    public void saveSector( Sector sector ) {
        sectorDAO.saveSector( sector );

    }

    @Override
    public Sector getSector( int theId ) {
        return sectorDAO.getSectors( theId );
    }

    @Override
    public void updateSector( Sector sector ) {
        sectorDAO.updateSector( sector );

    }

    @Override
    public List<Sector> findSectorByUserId( int userId ) {
        return sectorDAO.findSectorByUser( userId );
    }

    @Override
    public void deleteSector( int theId ) {
        sectorDAO.deleteSector( theId );

    }

    @Override
    public List<Sector> findSectorBySpotId( int spotId ) {
        return sectorDAO.findSectorBySpotId( spotId );
    }

    @Override
    public void levelOfSectors( List<Sector> sectors ) {
        for ( Sector sector : sectors ) {
            sector.setHighLevelOfSector( levelService.getLevel( sector.highLevelId() ).getLevelName() );
            sector.setLowLevelOfSector( levelService.getLevel( sector.lowLevelId() ).getLevelName() );
        }
    }

    @Override
    public void listingOfSectors( List<Sector> sectors ) {
        for ( Sector sector : sectors ) {
            sector.setHighListingOfSector( listingService.getListing( sector.highListingId() ).getListingName() );
            sector.setLowListingOfSector( listingService.getListing( sector.lowListingId() ).getListingName() );
        }
    }
}
