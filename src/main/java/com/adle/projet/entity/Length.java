package com.adle.projet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created Length Bean defined by id, heigth, relay and spit
 * 
 * Join with Path Bean, Listing Bean, User Bean, Level Bean with path, listing,
 * user, level
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "lengths" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Length_findByUserId", query = "from Length where user_id = :user" ),
        @org.hibernate.annotations.NamedQuery( name = "Length_findByPathId", query = "from Length where path_id = :path" ),
        @org.hibernate.annotations.NamedQuery( name = "Length_findByListingId", query = "from Length where listing_id = :listing" ),
        @org.hibernate.annotations.NamedQuery( name = "Length_findByLevelId", query = "from Length where level_id = :level" ),
        @org.hibernate.annotations.NamedQuery( name = "Length_findById", query = "from Length as l inner join fetch l.user where l.id =:lengthId" ),
} )

public class Length {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer id;

    @Column( name = "length_height", nullable = false )
    private Integer lengthHeight;

    @Column( name = "length_relay" )
    private Integer lengthRelay;

    @Column( name = "length_spit" )
    private Boolean lengthSpit;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "path_id" )
    private Path    path;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "listing_id" )
    private Listing listing;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User    user;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "level_id" )
    private Level   level;

    @Transient
    private String  lengthLevel;

    @Transient
    private String  lengthListing;

    public Length() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getLengthHeight() {
        return lengthHeight;
    }

    public void setLengthHeight( Integer lengthHeight ) {
        this.lengthHeight = lengthHeight;
    }

    public Integer getLengthRelay() {
        return lengthRelay;
    }

    public void setLengthRelay( Integer lengthRelay ) {
        this.lengthRelay = lengthRelay;
    }

    public Boolean getLengthSpit() {
        return lengthSpit;
    }

    public void setLengthSpit( Boolean lengthSpit ) {
        this.lengthSpit = lengthSpit;
    }

    public Path getPath() {
        return path;
    }

    public void setPath( Path path ) {
        this.path = path;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing( Listing listing ) {
        this.listing = listing;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel( Level level ) {
        this.level = level;
    }

    public String getLengthLevel() {
        return lengthLevel;
    }

    public void setLengthLevel( String lengthLevel ) {
        this.lengthLevel = lengthLevel;
    }

    public String getLengthListing() {
        return lengthListing;
    }

    public void setLengthListing( String lengthListing ) {
        this.lengthListing = lengthListing;
    }

    @Override
    public String toString() {
        return "Length {id=" + id + ", lengthHeight = " + lengthHeight + ", lengthRelay = " + lengthRelay
                + ", lengthSpit = " + lengthSpit + "}";
    }
}
