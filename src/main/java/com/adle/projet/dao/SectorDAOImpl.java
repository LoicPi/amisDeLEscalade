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

import com.adle.projet.entity.Path;
import com.adle.projet.entity.Sector;

@Repository
public class SectorDAOImpl implements SectorDAO {

    private static final Logger logger = LogManager.getLogger( SectorDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Sector> getSectors() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Sector> cq = cb.createQuery( Sector.class );
        Root<Sector> root = cq.from( Sector.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Sector List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public void saveSector( Sector sector ) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( sector );
        logger.info( "Sector saved successfully, Sector details = " + sector );

    }

    @Override
    public Sector getSectors( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery( "Sector_findById", Sector.class );
        query.setParameter( "sectorId", theId );
        Sector sectorResult = (Sector) query.getSingleResult();
        logger.info( "Sector loaded successfully, Sector details = " + sectorResult );
        return sectorResult;
    }

    @Override
    public void updateSector( Sector sector ) {
        Session currentsession = sessionFactory.getCurrentSession();
        currentsession.update( sector );
        logger.info( "Sector updated successfully, Sector details = " + sector );

    }

    @Override
    public List<Sector> findSectorByUser( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery( "Sector_findByUserId", Sector.class );
        query.setParameter( "user", userId );
        List<Sector> sectorResult = query.getResultList();
        logger.info( "Sector List : " + query.getResultList() );
        return sectorResult;
    }

    @Override
    public void deleteSector( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Sector theSector = session.byId( Sector.class ).load( theId );
        for ( Path p : theSector.getPaths() ) {
            session.remove( p );
        }
        session.delete( theSector );

    }

    @Override
    public List<Sector> findSectorBySpotId( int spotId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery( "Sector_findBySpotId", Sector.class );
        query.setParameter( "spot", spotId );
        List<Sector> sectorResult = query.getResultList();
        logger.info( "Sector List : " + query.getResultList() );
        return sectorResult;
    }

}
