package com.adle.projet.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.tools.FormattingString;

@Repository
public class SpotDAOImpl implements SpotDAO {

    private static final Logger logger           = LogManager.getLogger( SpotDAOImpl.class );

    FormattingString            formattingString = new FormattingString();

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
        spot.setSpotName( formattingString.Formatting( spot.getSpotName() ) );
        spot.setSpotCity( formattingString.Formatting( spot.getSpotCity() ) );
        spot.setSpotCountry( formattingString.Formatting( spot.getSpotCountry() ) );
        currentSession.saveOrUpdate( spot );
        logger.info( "Spot saved successfully, Spot details = " + spot );

    }

    @Override
    public Spot getSpot( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery( "Spot_findById", Spot.class );
        query.setParameter( "spotId", theId );
        Spot spotResult = (Spot) query.getSingleResult();
        logger.info( "Spot loaded successfully, Spot details = " + spotResult );
        return spotResult;
    }

    @Override
    public void updateSpot( Spot spot ) {
        Session currentsession = sessionFactory.getCurrentSession();
        spot.setSpotName( formattingString.Formatting( spot.getSpotName() ) );
        spot.setSpotCity( formattingString.Formatting( spot.getSpotCity() ) );
        spot.setSpotCountry( formattingString.Formatting( spot.getSpotCountry() ) );
        currentsession.update( spot );
        logger.info( "Spot updated successfully, Spot details = " + spot );

    }

    @Override
    public List<Spot> findSpotByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery( "Spot_findByUserId", Spot.class );
        query.setParameter( "user", userId );
        List<Spot> spotResult = query.getResultList();
        logger.info( "Spot List : " + query.getResultList() );
        return spotResult;
    }

    @Override
    public void deleteSpot( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Spot theSpot = session.byId( Spot.class ).load( theId );
        for ( Sector s : theSpot.getSectors() ) {
            session.remove( s );
        }
        session.delete( theSpot );
    }

}
