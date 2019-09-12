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

import com.adle.projet.entity.Listing;

@Repository
public class ListingDAOImpl implements ListingDAO {

    private static final Logger logger = LogManager.getLogger( ListingDAOImpl.class );

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
        query.setParameter( "level", levelId );
        List<Listing> listingResult = query.getResultList();
        logger.info( "Listing List : " + query.getResultList() );
        return listingResult;
    }

    @Override
    public Map<String, String> getListingNameOfListings( List<Listing> listings ) {
        Map<String, String> listingName = new HashMap<String, String>();
        for ( int i = 0; i < listings.size(); i++ ) {
            listingName.put( listings.get( i ).getListingName(), listings.get( i ).getListingName() );
        }
        logger.info( "MapOfListing : " + listingName );
        return listingName;
    }

    @Override
    public Listing findListingByNameOfListing( String nameListing ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Listing> query = currentSession.createNamedQuery( "Listing_findByName", Listing.class );
        query.setParameter( "listingName", nameListing );
        Listing listingResult = query.getSingleResult();
        logger.info( "Listing : " + listingResult );
        return listingResult;
    }
}
