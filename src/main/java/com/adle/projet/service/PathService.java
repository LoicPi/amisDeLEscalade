package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Path;

public interface PathService {

    public List<Path> getPaths();

    public void savePath( Path path );

    public Path getPath( int theId );

    public void updatePath( Path path );

    public List<Path> findPathByUserId( int userId );

    public List<Path> findPathBySectorId( int sectorId );

    public List<Path> findPathByTypeId( int typeId );

    public void deletePath( int theId );

}
