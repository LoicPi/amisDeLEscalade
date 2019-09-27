package com.adle.projet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table( name = "comments" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Comment_findByUserId", query = "from Comment where user_id = :user" ),
        @org.hibernate.annotations.NamedQuery( name = "Comment_findBySpotId", query = "from Comment where spot_id =:spot" ),
        @org.hibernate.annotations.NamedQuery( name = "Comment_findById", query = "from Comment as c inner join fetch c.user where c.id =:commentId" ),
} )
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer id;

    @Column( name = "comment_contents" )
    @Size( max = 300, message = "{comment.name.invalid}" )
    private String  contents;

    @Column( name = "comment_date" )
    @Temporal( TemporalType.TIMESTAMP )
    @DateTimeFormat( iso = ISO.DATE )
    private Date    date;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User    user;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "spot_id" )
    private Spot    spot;

    public Comment() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents( String contents ) {
        this.contents = contents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot( Spot spot ) {
        this.spot = spot;
    }

    @Override
    public String toString() {
        return "Comment {id= " + id + ", contents = " + contents + ", date = " + date + "}";
    }

}
