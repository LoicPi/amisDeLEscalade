package com.adle.projet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "listings" )

public class Listing {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer      id;

    @Column( name = "listing_name" )
    private String       listingName;

    @OneToMany( mappedBy = "listingId" )
    private List<Length> lengths = new ArrayList<>();

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "level_id" )
    private Level        levelId;

    public Listing() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getListingName() {
        return listingName;
    }

    public void setListingName( String listingName ) {
        this.listingName = listingName;
    }

    public List<Length> getLengths() {
        return lengths;
    }

    public void setLengths( List<Length> lengths ) {
        this.lengths = lengths;
    }

    public Level getLevelId() {
        return levelId;
    }

    public void setLevelId( Level levelId ) {
        this.levelId = levelId;
    }

    public String toString() {
        return "Listing {id = " + id + ", listingName = " + listingName + "}";
    }
}
