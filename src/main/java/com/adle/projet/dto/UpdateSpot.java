package com.adle.projet.dto;

import com.adle.projet.entity.User;

public class UpdateSpot {

    private Integer id;

    private String  spotName;

    private String  spotCity;

    private Integer spotCounty;

    private String  spotCountry;

    private String  spotDescriptive;

    private String  spotAccess;

    private Boolean spotTag;

    private User    user;

    public Integer spotIdUser() {
        return user.getId();
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

    public String getSpotCity() {
        return spotCity;
    }

    public void setSpotCity( String spotCity ) {
        this.spotCity = spotCity;
    }

    public Integer getSpotCounty() {
        return spotCounty;
    }

    public void setSpotCounty( Integer spotCounty ) {
        this.spotCounty = spotCounty;
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

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

}
