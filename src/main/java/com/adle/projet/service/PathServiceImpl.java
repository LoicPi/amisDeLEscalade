package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.PathDAO;
import com.adle.projet.entity.Path;

@Service
@Transactional
public class PathServiceImpl implements PathService {

    @Autowired
    private PathDAO pathDAO;

    @Override
    public List<Path> getPaths() {
        return pathDAO.getPaths();
    }

    @Override
    public void savePath( Path path ) {
        pathDAO.savePath( path );

    }

    @Override
    public Path getPath( int theId ) {
        return pathDAO.getPath( theId );
    }

    @Override
    public void updatePath( Path path ) {
        pathDAO.updatePath( path );

    }

    @Override
    public List<Path> findPathByUserId( int userId ) {
        return pathDAO.findPathByUserId( userId );
    }

    @Override
    public List<Path> findPathBySpotId( int spotId ) {
        return pathDAO.findPathBySpotId( spotId );
    }

    @Override
    public List<Path> findPathBySectorId( int sectorId ) {
        return pathDAO.findPathBySectorId( sectorId );
    }

    @Override
    public List<Path> findPathByTypeId( int typeId ) {
        return pathDAO.findPathByTypeId( typeId );
    }

    @Override
    public void deletePath( int theId ) {
        pathDAO.deletePath( theId );

    }

}
