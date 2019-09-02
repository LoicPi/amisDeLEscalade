package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.LengthDAO;
import com.adle.projet.entity.Length;

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

}
