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

import com.adle.projet.entity.Listing;

@Repository
public class ListingDAOImpl implements ListingDAO {

    private static final Logger logger = LogManager.getLogger( LengthDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Listing> getListings() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Listing> cq = cb.createQuery( Listing.class );
        Root<Listing> root = cq.from( Listing.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Listing List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public void saveListing( Listing listing ) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( listing );
        logger.info( "Listing saved successfully, Listing details = " + listing );

    }

    @Override
    public void deleteListing( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Listing theListing = session.byId( Listing.class ).load( theId );
        session.delete( theListing );

    }

    @Override
    public Listing getListing( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Listing listing = currentSession.get( Listing.class, theId );
        logger.info( "Listing loaded successfully, Listing details = " + listing );
        return listing;
    }

    @Override
    public List<Listing> findListingByLevelId( int levelId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Listing> query = currentSession.createNamedQuery( "Listing_findByLevelId", Listing.class );
        query.setParameter( "levelId", levelId );
        List<Listing> listingResult = query.getResultList();
        logger.info( "Listing List : " + query.getResultList() );
        return listingResult;
    }
}
