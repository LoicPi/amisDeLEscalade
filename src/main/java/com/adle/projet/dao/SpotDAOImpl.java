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

import com.adle.projet.entity.Spot;

public class SpotDAOImpl implements SpotDAO {

    private static final Logger logger = LogManager.getLogger( SpotDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Spot> getSpots() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Spot> cq = cb.createQuery( Spot.class );
        Root<Spot> root = cq.from( Spot.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Spot List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public void saveSpot( Spot spot ) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( spot );
        logger.info( "Spot saved successfully, Spot details = " + spot );

    }

    @Override
    public Spot getSpot( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Spot spot = currentSession.get( Spot.class, theId );
        logger.info( "Spot loaded successfully, Spot details = " + spot );
        return spot;
    }

    @Override
    public void updateSpot( Spot spot ) {
        Session currentsession = sessionFactory.getCurrentSession();
        currentsession.update( spot );
        logger.info( "Spot updated successfully, Spot details = " + spot );

    }

    @Override
    public List<Spot> findSpotByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery( "Spot_findByUserId", Spot.class );
        query.setParameter( "userId", userId );
        List<Spot> spotResult = query.getResultList();
        logger.info( "Spot List : " + query.getResultList() );
        return spotResult;
    }

    @Override
    public void deleteSpot( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Spot theSpot = session.byId( Spot.class ).load( theId );
        session.delete( theSpot );

    }

}
