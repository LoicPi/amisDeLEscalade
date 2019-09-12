package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.LengthDAO;
import com.adle.projet.entity.Length;
import com.adle.projet.entity.Path;

@Service
@Transactional
public class LengthServiceImpl implements LengthService {

    @Autowired
    private LengthDAO lengthDAO;

    @Override
    public List<Length> getLengths() {
        return lengthDAO.getLengths();
    }

    @Override
    public void saveLength( Length length ) {
        lengthDAO.saveLength( length );

    }

    @Override
    public void updateLength( Length length ) {
        lengthDAO.updateLength( length );

    }

    @Override
    public void deleteLength( int theId ) {
        lengthDAO.deleteLength( theId );

    }

    @Override
    public Length getLength( int theId ) {
        return lengthDAO.getLength( theId );
    }

    @Override
    public List<Length> findLengthByUserId( int userId ) {
        return lengthDAO.findLengthByUserId( userId );
    }

    @Override
    public List<Length> findLengthByPathId( int pathId ) {
        return lengthDAO.findLengthByPathId( pathId );
    }

    @Override
    public List<Length> findLengthByListingId( int listingId ) {
        return lengthDAO.findLengthByListingId( listingId );
    }

    @Override
    public List<Length> findLengthByLevelId( int levelId ) {
        return lengthDAO.findLengthByLevelId( levelId );
    }

    @Override
    public void deleteLengths( List<Length> lengths ) {
        lengthDAO.deleteLengths( lengths );

    }

    @Override
    public List<Length> findLengthsByPaths( List<Path> paths ) {
        return lengthDAO.findLengthsByPaths( paths );
    }

}
