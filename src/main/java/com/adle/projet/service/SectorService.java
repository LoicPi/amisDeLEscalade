package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Sector;

public interface SectorService {

    public List<Sector> getSectors();

    public void saveSector( Sector sector );

    public Sector getSector( int theId );

    public void updateSector( Sector sector );

    public List<Sector> findSectorByUserId( int userId );

    public List<Sector> findSectorBySpotId( int spotId );

    public void deleteSector( int theId );

    public void levelOfSectors( List<Sector> theSectors );

    public void listingOfSectors( List<Sector> sectors );
}
