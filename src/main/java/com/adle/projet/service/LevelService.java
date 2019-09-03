package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Level;

public interface LevelService {

    public List<Level> getLevels();

    public void saveLevel( Level level );

    public void deleteLevel( int theId );

    public Level getLevel( int theId );

}
