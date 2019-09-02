package com.adle.projet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "levels" )

public class Level {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer       id;

    @Column( name = "level_name" )
    private String        levelName;

    @OneToMany( mappedBy = "levelId" )
    private List<Listing> listings = new ArrayList<>();

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

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings( List<Listing> listings ) {
        this.listings = listings;
    }

    public String toString() {
        return "Level {id = " + id + ", levelName = " + levelName + "}";
    }
}
