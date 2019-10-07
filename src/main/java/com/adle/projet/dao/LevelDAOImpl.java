package com.adle.projet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.Level;

@Repository
public class LevelDAOImpl implements LevelDAO {

    private static final Logger logger = LogManager.getLogger( LevelDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    /**
     * Function return the list of levels in database
     */
    @Override
    public List<Level> getLevels() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Level> cq = cb.createQuery( Level.class );
        Root<Level> root = cq.from( Level.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Level List : " + query.getResultList() );
        return query.getResultList();
    }

    /**
     * Function return a level by the given id
     */
    @Override
    public Level getLevel( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Level level = currentSession.get( Level.class, theId );
        logger.info( "Level loaded successfully, Level details = " + level );
        return level;
    }

    /**
     * Fucntion return a map of level by the given list of levels
     */
    @Override
    public Map<Integer, String> getLevelNameOfLevels( List<Level> levels ) {
        Map<Integer, String> levelName = new HashMap<Integer, String>();
        for ( int i = 1; i <= levels.size(); i++ ) {
            levelName.put( i, levels.get( i - 1 ).getLevelName() );
        }
        logger.info( "MapOfLevel : " + levelName );
        return levelName;
    }

}
