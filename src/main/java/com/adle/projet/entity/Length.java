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
import javax.validation.constraints.Size;

@Entity
@Table( name = "lengths" )

public class Length {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer id;

    @Column( name = "length_height" )
    private Integer lengthHeight;

    @Column( name = "length_relay" )
    @Size( max = 100, min = 3, message = "{length.relay.invalid}" )
    private String  lengthRelay;

    @Column( name = "length_spit" )
    private Boolean lengthSpit;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "path_id" )
    private Path    pathId;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "listing_id" )
    private Listing listingId;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User    userId;

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

    public String getLengthRelay() {
        return lengthRelay;
    }

    public void setLengthRelay( String lengthRelay ) {
        this.lengthRelay = lengthRelay;
    }

    public Boolean getLengthSpit() {
        return lengthSpit;
    }

    public void setLengthSpit( Boolean lengthSpit ) {
        this.lengthSpit = lengthSpit;
    }

    public Path getPathId() {
        return pathId;
    }

    public void setPathId( Path pathId ) {
        this.pathId = pathId;
    }

    public Listing getListingId() {
        return listingId;
    }

    public void setListingId( Listing listingId ) {
        this.listingId = listingId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId( User userId ) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Length {id=" + id + ", lengthHeight = " + lengthHeight + ", lengthRelay = " + lengthRelay
                + ", lengthSpit = " + lengthSpit + "}";
    }
}
