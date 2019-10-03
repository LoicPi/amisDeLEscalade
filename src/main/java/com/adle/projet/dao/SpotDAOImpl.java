package com.adle.projet.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createQuery(
                "select distinct s from Spot as s "
                        + "left join fetch s.user "
                        + "left join fetch s.county "
                        + "left join fetch s.sectors as sse "
                        + "left join fetch s.comments "
                        + "left join fetch sse.paths as ssep "
                        + "left join fetch ssep.lengths as ssepl "
                        + "left join fetch ssepl.listing as ssepll "
                        + "left join fetch ssepll.level as sseplll ",
                Spot.class );
        List<Spot> spots = query.getResultList();
        return spots;
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

    @Override
    public List<Spot> searchSpot( String nameSpot, String citySpot, int countySpot, String sectorsSpot, int listingSpot,
            int levelSpot ) {

        List<Spot> searchSpot = new ArrayList<Spot>();

        StringBuilder request = new StringBuilder();

        String varJointure = "";
        String varTable = "";
        String varSelect = "Select distinct s from Spot as s "
                + "left join fetch s.user "
                + "left join fetch s.county "
                + "left join fetch s.sectors as sse "
                + "left join fetch s.comments "
                + "left join fetch sse.paths as ssep "
                + "left join fetch ssep.lengths as ssepl "
                + "left join fetch ssepl.listing as ssepll "
                + "left join fetch ssepll.level as sseplll";

        if ( listingSpot != 0 ) {
            varTable = varTable + ", Sector as se, Path as p, Length as le, Listing as li";
            varJointure = varJointure
                    + "s.id = se.spot.id and se.id = p.sector.id and p.id = le.path.id and le.listing.id = li.id and li.id = "
                    + listingSpot;
        }

        if ( listingSpot != 0 && levelSpot != 0 ) {
            varTable = varTable + ", Level as lev";
            varJointure = varJointure + " and li.level_id = lev.id and lev.id = " + levelSpot;
        }

        if ( listingSpot == 0 && levelSpot != 0 ) {
            varTable = varTable + ", Sector as se, Path as p, Length as le, Listing as li, Level as lev";
            varJointure = varJointure
                    + "s.id = se.spot.id and se.id = p.sector.id and p.id = le.path.id and le.listing.id = li.id and li.level_id = lev.id and lev.id = "
                    + levelSpot;
        }

        if ( nameSpot != null && !nameSpot.isEmpty() ) {
            varJointure = varJointure + " and s.spotName like '%" + nameSpot + "%'";
        }

        if ( citySpot != "" && !citySpot.isEmpty() ) {
            varJointure = varJointure + " and s.spotCity like '%" + citySpot + "%'";
        }

        if ( countySpot != 0 ) {
            varJointure = varJointure + " and county_id =" + countySpot;
        }

        request.append( varSelect );
        request.append( varTable );
        request.append( " where 1 = 1" );
        request.append( varJointure );

        logger.info( "Requete SQL : " + request );

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Spot> query = currentSession.createQuery( request.toString(), Spot.class );
        List<Spot> spots = query.getResultList();
        logger.info( "Spot List : " + spots );
        if ( sectorsSpot != null && sectorsSpot != "" ) {
            int sectorSearch = Integer.parseInt( sectorsSpot );
            for ( Spot spot : spots ) {
                if ( spot.getSectors().size() == sectorSearch ) {
                    searchSpot.add( spot );
                }
            }
            logger.info( "Spot List : " + searchSpot );
            return searchSpot;
        }
        return spots;

    }
}
