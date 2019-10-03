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

import com.adle.projet.entity.Length;
import com.adle.projet.entity.Path;
import com.adle.projet.tools.FormattingString;

@Repository
public class PathDAOImpl implements PathDAO {

    private static final Logger logger           = LogManager.getLogger( PathDAOImpl.class );

    FormattingString            formattingString = new FormattingString();

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Path> getPaths() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Path> query = currentSession.createQuery(
                "Select distinct p from Path as p "
                        + "left join fetch p.user "
                        + "left join fetch p.sector "
                        + "left join fetch p.type "
                        + "left join fetch p.lengths as pl "
                        + "left join fetch pl.listing as pll "
                        + "left join fetch pll.level",
                Path.class );
        List<Path> paths = query.getResultList();
        logger.info( "Path List : " + paths );
        return paths;
    }

    @Override
    public void savePath( Path path ) {
        Session currentSession = sessionFactory.getCurrentSession();
        path.setPathName( formattingString.Formatting( path.getPathName() ) );
        currentSession.saveOrUpdate( path );
        logger.info( "Path saved successfully, Path details = " + path );

    }

    @Override
    public Path getPath( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Path> query = currentSession.createNamedQuery( "Path_findById", Path.class );
        query.setParameter( "pathId", theId );
        Path pathResult = (Path) query.getSingleResult();
        Hibernate.initialize( pathResult.getLengths() );
        logger.info( "Path loaded successfully, Path details = " + pathResult );
        return pathResult;
    }

    @Override
    public void updatePath( Path path ) {
        Session currentsession = sessionFactory.getCurrentSession();
        path.setPathName( formattingString.Formatting( path.getPathName() ) );
        currentsession.update( path );
        logger.info( "Path updated successfully, Path details = " + path );

    }

    @Override
    public List<Path> findPathByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Path> query = currentSession.createNamedQuery( "Path_findByUserId", Path.class );
        query.setParameter( "user", userId );
        List<Path> pathResult = query.getResultList();
        logger.info( "Path List : " + query.getResultList() );
        return pathResult;
    }

    @Override
    public List<Path> findPathBySpotId( int spotId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Path> query = currentSession.createNamedQuery( "Path_findBySpotId", Path.class );
        query.setParameter( "spot", spotId );
        List<Path> pathResult = query.getResultList();
        logger.info( "Path List : " + query.getResultList() );
        return pathResult;
    }

    @Override
    public List<Path> findPathBySectorId( int sectorId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Path> query = currentSession.createNamedQuery( "Path_findBySectorId", Path.class );
        query.setParameter( "sector", sectorId );
        List<Path> pathResult = query.getResultList();
        logger.info( "Path List : " + query.getResultList() );
        return pathResult;
    }

    @Override
    public List<Path> findPathByTypeId( int typeId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Path> query = currentSession.createNamedQuery( "Path_findByTypeId", Path.class );
        query.setParameter( "type", typeId );
        List<Path> pathResult = query.getResultList();
        logger.info( "Path List : " + query.getResultList() );
        return pathResult;
    }

    @Override
    public void deletePath( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Path thePath = session.byId( Path.class ).load( theId );
        for ( Length l : thePath.getLengths() ) {
            session.remove( l );
        }
        session.delete( thePath );

    }
}
