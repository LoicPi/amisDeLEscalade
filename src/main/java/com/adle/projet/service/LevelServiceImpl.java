package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.LevelDAO;
import com.adle.projet.entity.Level;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelDAO levelDAO;

    @Override
    public List<Level> getLevels() {
        return levelDAO.getLevels();
    }

    @Override
    public void saveLevel( Level level ) {
        levelDAO.saveLevel( level );
    }

    @Override
    public void deleteLevel( int theId ) {
        levelDAO.deleteLevel( theId );

    }

    @Override
    public Level getLevel( int theId ) {
        return levelDAO.getLevel( theId );
    }

}
