package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Comment;

public interface CommentService {

    public List<Comment> getComments();

    public void saveComment( Comment comment );

    public void updateComment( Comment comment );

    public void deleteComment( int theId );

    public Comment getComment( int theId );

    public List<Comment> findCommentByUserId( int userId );

    public List<Comment> findCommentBySpotId( int spotId );

}
