package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Comment;

public interface CommentDAO {

    public List<Comment> getComments();

    public void saveComment( Comment comment );

    public void updateDAO( Comment comment );

    public void deleteComment( int theId );

    public Comment getComment( int theId );

    public List<Comment> findCommentByUserId( int userId );

    public List<Comment> findCommentBySpotId( int spotId );

}
