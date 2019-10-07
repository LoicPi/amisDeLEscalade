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

import com.adle.projet.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private static final Logger logger = LogManager.getLogger( RoleDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    /**
     * Function save a Role in database
     */
    @Override
    public void saveRole( Role theRole ) {
        Session currentSession = sessionFactory.getCurrentSession();
        logger.info( "Role saved successfully, Role details = " + theRole );
        currentSession.saveOrUpdate( theRole );
    }

    /**
     * Function return the list of roles in database
     */
    @Override
    public List<Role> getRoles() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery( Role.class );
        Root<Role> root = cq.from( Role.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "The list of role : " + query.getResultList() );
        return query.getResultList();
    }

    /**
     * Function return a role by a boolean if user is member or not
     */
    @Override
    public Role findUserRoleByCode( Boolean userMember ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Role> query = currentSession.createNamedQuery( "Role_findByCode", Role.class );
        Role theRole;
        String roleCode;

        if ( userMember ) {
            roleCode = "member";
            query.setParameter( "roleCode", roleCode );
            logger.info( "The user is a member." );
            theRole = query.getSingleResult();
        } else {
            roleCode = "normal";
            query.setParameter( "roleCode", roleCode );
            logger.info( "The user is not a member." );
            theRole = query.getSingleResult();
        }

        return theRole;
    }

}
