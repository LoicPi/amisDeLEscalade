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

    @Override
    public Level getLevel( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Level level = currentSession.get( Level.class, theId );
        logger.info( "Level loaded successfully, Level details = " + level );
        return level;
    }

    @Override
    public Map<String, String> getLevelNameOfLevels( List<Level> levels ) {
        Map<String, String> levelName = new HashMap<String, String>();
        for ( int i = 0; i < levels.size(); i++ ) {
            levelName.put( levels.get( i ).getLevelName(), levels.get( i ).getLevelName() );
        }
        logger.info( "MapOfLevel : " + levelName );
        return levelName;
    }

    @Override
    public Level findLevelByNameOfLevel( String nameLevel ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Level> query = currentSession.createNamedQuery( "Level_findByName", Level.class );
        query.setParameter( "levelName", nameLevel );
        Level levelResult = query.getSingleResult();
        logger.info( "Level : " + levelResult );
        return levelResult;
    }

}
