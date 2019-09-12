package com.adle.projet.dao;

import java.util.List;
import java.util.Map;

import com.adle.projet.entity.Level;

public interface LevelDAO {

    public List<Level> getLevels();

    public Level getLevel( int theId );

    public Map<String, String> getLevelNameOfLevels( List<Level> levels );

    public Level findLevelByNameOfLevel( String nameLevel );

}
