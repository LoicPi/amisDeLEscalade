package com.adle.projet.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.Path;
import com.adle.projet.entity.Sector;
import com.adle.projet.tools.FormattingString;

@Repository
public class SectorDAOImpl implements SectorDAO {

    private static final Logger logger           = LogManager.getLogger( SectorDAOImpl.class );

    FormattingString            formattingString = new FormattingString();

    @Autowired
    private SessionFactory      sessionFactory;

    /**
     * Function return a list of sector with all elements
     */
    @Override
    public List<Sector> getSectors() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createQuery(
                "select distinct r from Sector as r "
                        + "left join fetch r.user "
                        + "left join fetch r.paths as rp "
                        + "left join fetch rp.type "
                        + "left join fetch rp.lengths as rpl "
                        + "left join fetch rpl.listing as rpll "
                        + "left join fetch rpll.level as rplll ",
                Sector.class );
        List<Sector> sectors = query.getResultList();
        logger.info( "Sector List : " + sectors );
        return sectors;
    }

    /**
     * Function save a Sector in database
     */
    @Override
    public void saveSector( Sector sector ) {
        Session currentSession = sessionFactory.getCurrentSession();
        sector.setSectorName( formattingString.Formatting( sector.getSectorName() ) );
        currentSession.saveOrUpdate( sector );
        logger.info( "Sector saved successfully, Sector details = " + sector );

    }

    /**
     * Function return a sector by the given id
     */
    @Override
    public Sector getSectors( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery( "Sector_findById", Sector.class );
        query.setParameter( "sectorId", theId );
        Sector sectorResult = (Sector) query.getSingleResult();
        Hibernate.initialize( sectorResult.getPaths() );
        logger.info( "Sector loaded successfully, Sector details = " + sectorResult );
        return sectorResult;
    }

    /**
     * Function update a Sector in database
     */
    @Override
    public void updateSector( Sector sector ) {
        Session currentsession = sessionFactory.getCurrentSession();
        sector.setSectorName( formattingString.Formatting( sector.getSectorName() ) );
        currentsession.update( sector );
        logger.info( "Sector updated successfully, Sector details = " + sector );

    }

    /**
     * Function find a List of Sector with userId
     */
    @Override
    public List<Sector> findSectorByUser( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery( "Sector_findByUserId", Sector.class );
        query.setParameter( "user", userId );
        List<Sector> sectorResult = query.getResultList();
        logger.info( "Sector List : " + query.getResultList() );
        return sectorResult;
    }

    /**
     * Function delete a Sector in database
     */
    @Override
    public void deleteSector( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Sector theSector = session.byId( Sector.class ).load( theId );
        for ( Path p : theSector.getPaths() ) {
            session.remove( p );
        }
        session.delete( theSector );

    }

    /**
     * Function find a List of Sector with spotId
     */
    @Override
    public List<Sector> findSectorBySpotId( int spotId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        StringBuilder request = new StringBuilder();

        String varSelect = "Select distinct r from Sector as r "
                + "left join fetch r.user "
                + "left join fetch r.spot "
                + "left join fetch r.paths as rp "
                + "left join fetch rp.type "
                + "left join fetch rp.lengths as rpl "
                + "left join fetch rpl.listing as rpll "
                + "left join fetch rpll.level as rplll ";

        String varWhere = "where r.spot.id = " + spotId;

        request.append( varSelect );
        request.append( varWhere );

        Query<Sector> query = currentSession.createQuery( request.toString(), Sector.class );
        List<Sector> sectorResult = query.getResultList();
        logger.info( "Sector List : " + sectorResult );
        return sectorResult;
    }

}
