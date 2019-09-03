package com.adle.projet.dao;

import java.util.List;

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
    public void saveLevel( Level level ) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( level );
        logger.info( "Level saved successfully, Level details = " + level );
    }

    @Override
    public void deleteLevel( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Level theLevel = session.byId( Level.class ).load( theId );
        session.delete( theLevel );
    }

    @Override
    public Level getLevel( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Level level = currentSession.get( Level.class, theId );
        logger.info( "Level loaded successfully, Level details = " + level );
        return level;
    }

}
