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

import com.adle.projet.entity.Topo;
import com.adle.projet.tools.FormattingString;

@Repository
public class TopoDAOImpl implements TopoDAO {

    private static final Logger logger           = LogManager.getLogger( TopoDAOImpl.class );

    FormattingString            formattingString = new FormattingString();

    @Autowired
    private SessionFactory      sessionFactory;

    /**
     * Function return the list of topos in database
     */
    @Override
    public List<Topo> getTopos() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Topo> query = currentSession.createQuery(
                "select distinct t from Topo as t "
                        + "left join fetch t.user "
                        + "left join fetch t.county",
                Topo.class );
        List<Topo> topos = query.getResultList();
        logger.info( "List of Topo : " + topos );
        return topos;
    }

    /**
     * Function save a topo in database
     */
    @Override
    public void saveTopo( Topo topo ) {
        Session currentSession = sessionFactory.getCurrentSession();
        topo.setTopoName( formattingString.Formatting( topo.getTopoName() ) );
        topo.setTopoCity( formattingString.Formatting( topo.getTopoCity() ) );
        topo.setTopoCountry( formattingString.Formatting( topo.getTopoCountry() ) );
        currentSession.saveOrUpdate( topo );
        logger.info( "Topo saved successfully, Topo details = " + topo );
    }

    /**
     * Function return a topo by the given id
     */
    @Override
    public Topo getTopo( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Topo> query = currentSession.createNamedQuery( "Topo_findById", Topo.class );
        query.setParameter( "topoId", theId );
        Topo topoResult = (Topo) query.getSingleResult();
        Hibernate.initialize( topoResult.getCounty() );
        logger.info( "Topo loaded successfully, Topo details = " + topoResult );
        return topoResult;
    }

    /**
     * Function update a Topo in database
     */
    @Override
    public void updateTopo( Topo topo ) {
        Session currentsession = sessionFactory.getCurrentSession();
        topo.setTopoName( formattingString.Formatting( topo.getTopoName() ) );
        topo.setTopoCity( formattingString.Formatting( topo.getTopoCity() ) );
        topo.setTopoCountry( formattingString.Formatting( topo.getTopoCountry() ) );
        currentsession.update( topo );
        logger.info( "Topo updated successfully, Topo details = " + topo );
    }

    /**
     * Function find a Topo with userId return a list of topo
     */
    @Override
    public List<Topo> findTopoByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Topo> query = currentSession.createNamedQuery( "Topo_findByUserId", Topo.class );
        query.setParameter( "user", userId );
        List<Topo> topoResult = query.getResultList();
        logger.info( "Topo List : " + query.getResultList() );
        return topoResult;
    }

    /**
     * Function delete a Topo in database
     */
    @Override
    public void deleteTopo( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Topo theTopo = session.byId( Topo.class ).load( theId );
        session.delete( theTopo );
    }

}
