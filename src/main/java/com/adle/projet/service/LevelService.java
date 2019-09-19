package com.adle.projet.service;

import java.util.List;
import java.util.Map;

import com.adle.projet.entity.Level;

public interface LevelService {

    public List<Level> getLevels();

    public Level getLevel( int theId );

    public Map<Integer, String> getLevelNameOfLevels( List<Level> levels );

}
