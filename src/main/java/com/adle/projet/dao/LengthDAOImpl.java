package com.adle.projet.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.Length;

@Repository
public class LengthDAOImpl implements LengthDAO {

    private static final Logger logger = LogManager.getLogger( LengthDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Length> getLengths() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createQuery(
                "select distinct l from Length as l "
                        + "left join fetch l.user "
                        + "left join fetch l.path "
                        + "left join fetch l.listing  as ll "
                        + "left join fetch ll.level",
                Length.class );
        List<Length> lengths = query.getResultList();
        return lengths;
    }

    @Override
    public void saveLength( Length length ) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( length );
        logger.info( "Length saved successfully, Length details = " + length );
    }

    @Override
    public void updateLength( Length length ) {
        Session currentsession = sessionFactory.getCurrentSession();
        currentsession.update( length );
        logger.info( "Length updated successfully, Length details = " + length );
    }

    @Override
    public void deleteLength( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Length theLength = session.byId( Length.class ).load( theId );
        session.delete( theLength );
    }

    @Override
    public Length getLength( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery( "Length_findById", Length.class );
        query.setParameter( "lengthId", theId );
        Length lengthResult = (Length) query.getSingleResult();
        logger.info( "Length loaded successfully, Length details = " + lengthResult );
        return lengthResult;
    }

    @Override
    public List<Length> findLengthByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery( "Length_findByUserId", Length.class );
        query.setParameter( "user", userId );
        List<Length> lengthResult = query.getResultList();
        logger.info( "Length List : " + query.getResultList() );
        return lengthResult;
    }

    @Override
    public List<Length> findLengthByPathId( int pathId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery( "Length_findByPathId", Length.class );
        query.setParameter( "path", pathId );
        List<Length> lengthResult = query.getResultList();
        logger.info( "Length List : " + query.getResultList() );
        return lengthResult;
    }

    @Override
    public List<Length> findLengthByListingId( int listingId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery( "Length_findByListingId", Length.class );
        query.setParameter( "listing", listingId );
        List<Length> lengthResult = query.getResultList();
        logger.info( "Length List : " + query.getResultList() );
        return lengthResult;
    }

    @Override
    public List<Length> findLengthByLevelId( int levelId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery( "Length_findByLevelId", Length.class );
        query.setParameter( "level", levelId );
        List<Length> lengthResult = query.getResultList();
        logger.info( "Length List : " + query.getResultList() );
        return lengthResult;
    }

}
