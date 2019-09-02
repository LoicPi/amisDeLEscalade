package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Sector;

public interface SectorDAO {

    public List<Sector> getSectors();

    public void saveSector( Sector sector );

    public Sector getSectors( int theId );

    public void updateSector( Sector sector );

    public List<Sector> findSectorByUser( int userId );

    public void deleteSector( int theId );

    public List<Sector> findSectorBySpotId( int spotId );

}
