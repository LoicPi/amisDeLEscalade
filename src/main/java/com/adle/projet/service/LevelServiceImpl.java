package com.adle.projet.service;

import java.util.List;
import java.util.Map;

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
    public Level getLevel( int theId ) {
        return levelDAO.getLevel( theId );
    }

    @Override
    public Map<Integer, String> getLevelNameOfLevels( List<Level> levels ) {
        return levelDAO.getLevelNameOfLevels( levels );
    }

}
