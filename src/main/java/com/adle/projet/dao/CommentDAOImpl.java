package com.adle.projet.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private static final Logger logger = LogManager.getLogger( CountyDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    /**
     * Function return the list of comments in database
     */
    @Override
    public List<Comment> getComments() {
        Session session = sessionFactory.getCurrentSession();
        Query<Comment> query = session.createQuery(
                "select distinct c from Comment as c "
                        + "left join fetch c.user "
                        + "left join fetch c.spot ",
                Comment.class );
        List<Comment> comments = query.getResultList();
        logger.info( "Comment List : " + comments );
        return comments;
    }

    /**
     * Function save a comment in database
     */
    @Override
    public void saveComment( Comment comment ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Timestamp now = new Timestamp( System.currentTimeMillis() );
        comment.setDate( now );
        currentSession.saveOrUpdate( comment );
        logger.info( "Comment saved successfully, Comment details = " + comment );
    }

    /**
     * Function update a Comment in database
     */
    @Override
    public void updateDAO( Comment comment ) {
        Session currentsession = sessionFactory.getCurrentSession();
        currentsession.update( comment );
        logger.info( "Comment updated successfully, Comment details = " + comment );
    }

    /**
     * Function delete a Comment in database
     */
    @Override
    public void deleteComment( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Comment theComment = session.byId( Comment.class ).load( theId );
        session.delete( theComment );
    }

    /**
     * Function return a comment find with commentId
     */
    @Override
    public Comment getComment( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Comment> query = currentSession.createNamedQuery( "Comment_findById", Comment.class );
        query.setParameter( "commentId", theId );
        Comment commentResult = (Comment) query.getSingleResult();
        Hibernate.initialize( commentResult.getUser() );
        logger.info( "Comment loaded successfully, Comment details = " + commentResult );
        return commentResult;
    }

    /**
     * Function return a list of comments find with userId
     */
    @Override
    public List<Comment> findCommentByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Comment> query = currentSession.createNamedQuery( "Comment_findByUserId", Comment.class );
        query.setParameter( "user", userId );
        List<Comment> commentResult = query.getResultList();
        logger.info( "Comment List : " + commentResult );
        return commentResult;
    }

    /**
     * Function return a list of comments find with spotId
     */
    @Override
    public List<Comment> findCommentBySpotId( int spotId ) {
        Session currentSession = sessionFactory.getCurrentSession();

        StringBuilder request = new StringBuilder();

        String varSelect = "Select distinct c from Comment as c "
                + "left join fetch c.user "
                + "left join fetch c.spot ";

        String varWhere = "where spot_id = " + spotId;

        request.append( varSelect );
        request.append( varWhere );

        Query<Comment> query = currentSession.createQuery( request.toString(), Comment.class );
        List<Comment> commentResult = query.getResultList();
        logger.info( "Comment List : " + commentResult );
        return commentResult;
    }

}
