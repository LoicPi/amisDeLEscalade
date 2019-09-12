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

import com.adle.projet.entity.Type;

@Repository
public class TypeDAOImpl implements TypeDAO {

    private static final Logger logger = LogManager.getLogger( TypeDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Type> getTypes() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Type> cq = cb.createQuery( Type.class );
        Root<Type> root = cq.from( Type.class );
        cq.select( root );
        cq.orderBy( cb.asc( root.get( "id" ) ) );
        Query query = session.createQuery( cq );
        logger.info( "Type List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public Type getType( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Type type = currentSession.get( Type.class, theId );
        logger.info( "Type loaded successfully, Type details = " + type );
        return type;
    }

    @Override
    public Map<String, String> getTypeNameOfTypes( List<Type> types ) {
        Map<String, String> typeName = new HashMap<String, String>();
        for ( int i = 0; i < types.size(); i++ ) {
            typeName.put( types.get( i ).getTypeName(), types.get( i ).getTypeName() );
        }
        logger.info( "MapOfType : " + typeName );
        return typeName;
    }

    @Override
    public Type findTypeByNameOfType( String nameType ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Type> query = currentSession.createNamedQuery( "Type_findByName", Type.class );
        query.setParameter( "typeName", nameType );
        Type typeResult = query.getSingleResult();
        logger.info( "Type : " + typeResult );
        return typeResult;
    }
}
