package com.adle.projet.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created Level Bean defined by id and name
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "levels" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Level_findByName", query = "from Level where level_name = :levelName" ),
} )
public class Level {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer      id;

    @Column( name = "level_name" )
    private String       levelName;

    @OneToMany( mappedBy = "level" )
    private Set<Listing> listings;

    public Level() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName( String levelName ) {
        this.levelName = levelName;
    }

    public Set<Listing> getListings() {
        return listings;
    }

    public void setListings( Set<Listing> listings ) {
        this.listings = listings;
    }

    public String toString() {
        return "Level {id = " + id + ", levelName = " + levelName + "}";
    }
}
