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
import javax.validation.constraints.Size;

@Entity
@Table( name = "spots" )

public class Spot {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer      id;

    @Column( name = "spot_name", unique = true )
    @Size( max = 100, min = 3, message = "{site.name.invalid}" )
    private String       spotName;

    @Column( name = "spot_county" )
    private Integer      spotCounty;

    @Column( name = "spot_city" )
    private String       spotCity;

    @Column( name = "spot_country" )
    private String       spotCountry;

    @Column( name = "spot_descriptive" )
    @Size( max = 600, min = 10, message = "{spot.descriptive.invalid}" )
    private String       spotDescriptive;

    @Column( name = "spot_access" )
    @Size( max = 300, min = 10, message = "{spot.access.invalid}" )
    private String       spotAccess;

    @Column( name = "spot_tag" )
    private Boolean      spotTag = false;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User         userId;

    @OneToMany( mappedBy = "spotId" )
    private List<Sector> sectors = new ArrayList<>();

    public Spot() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName( String spotName ) {
        this.spotName = spotName;
    }

    public Integer getSpotCounty() {
        return spotCounty;
    }

    public void setSpotCounty( Integer spotCounty ) {
        this.spotCounty = spotCounty;
    }

    public String getSpotCity() {
        return spotCity;
    }

    public void setSpotCity( String spotCity ) {
        this.spotCity = spotCity;
    }

    public String getSpotCountry() {
        return spotCountry;
    }

    public void setSpotCountry( String spotCountry ) {
        this.spotCountry = spotCountry;
    }

    public String getSpotDescriptive() {
        return spotDescriptive;
    }

    public void setSpotDescriptive( String spotDescriptive ) {
        this.spotDescriptive = spotDescriptive;
    }

    public String getSpotAccess() {
        return spotAccess;
    }

    public void setSpotAccess( String spotAccess ) {
        this.spotAccess = spotAccess;
    }

    public Boolean getSpotTag() {
        return spotTag;
    }

    public void setSpotTag( Boolean spotTag ) {
        this.spotTag = spotTag;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId( User userId ) {
        this.userId = userId;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors( List<Sector> sectors ) {
        this.sectors = sectors;
    }

    @Override
    public String toString() {
        return "Spot {id=" + id + ", spotName =" + spotName + ",spotCity =" + spotCity + ",spotCounty =" + spotCounty
                + ",spotCountry =" + spotCountry + ", spotDescriptive =" + spotDescriptive +
                ", spotAccess=" + spotAccess + ", userId =" + userId + "}";
    }
}
