package com.adle.projet.entity;

import java.util.Set;

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

/**
 * Created Listing Bean defined by id and name
 * 
 * Join with Level Bean with level
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "listings" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Listing_findByLevelId", query = "from Listing where level_id = :level" ),
        @org.hibernate.annotations.NamedQuery( name = "Listing_findByName", query = "from Listing where listing_name = :listingName" ),
} )

public class Listing {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer     id;

    @Column( name = "listing_name" )
    private String      listingName;

    @OneToMany( mappedBy = "listing" )
    private Set<Length> lengths;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "level_id" )
    private Level       level;

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

    public Set<Length> getLengths() {
        return lengths;
    }

    public void setLengths( Set<Length> lengths ) {
        this.lengths = lengths;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel( Level level ) {
        this.level = level;
    }

    public String toString() {
        return "Listing {id = " + id + ", listingName = " + listingName + "}";
    }
}
