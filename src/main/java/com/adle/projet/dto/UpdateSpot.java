package com.adle.projet.dto;

import com.adle.projet.entity.County;
import com.adle.projet.entity.User;

public class UpdateSpot {

    private Integer id;

    private String  updateSpotName;

    private String  updateSpotCity;

    private String  updateSpotCountry;

    private String  updateSpotDescriptive;

    private String  updateSpotAccess;

    private Boolean updateSpotTag;

    private User    user;

    private County  county;

    private Integer spotCounty;

    public Integer spotIdUser() {
        return user.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getUpdateSpotName() {
        return updateSpotName;
    }

    public void setUpdateSpotName( String updateSpotName ) {
        this.updateSpotName = updateSpotName;
    }

    public String getUpdateSpotCity() {
        return updateSpotCity;
    }

    public void setUpdateSpotCity( String updateSpotCity ) {
        this.updateSpotCity = updateSpotCity;
    }

    public String getUpdateSpotCountry() {
        return updateSpotCountry;
    }

    public void setUpdateSpotCountry( String updateSpotCountry ) {
        this.updateSpotCountry = updateSpotCountry;
    }

    public String getUpdateSpotDescriptive() {
        return updateSpotDescriptive;
    }

    public void setUpdateSpotDescriptive( String updateSpotDescriptive ) {
        this.updateSpotDescriptive = updateSpotDescriptive;
    }

    public String getUpdateSpotAccess() {
        return updateSpotAccess;
    }

    public void setUpdateSpotAccess( String updateSpotAccess ) {
        this.updateSpotAccess = updateSpotAccess;
    }

    public Boolean getUpdateSpotTag() {
        return updateSpotTag;
    }

    public void setUpdateSpotTag( Boolean updateSpotTag ) {
        this.updateSpotTag = updateSpotTag;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty( County county ) {
        this.county = county;
    }

    public Integer getSpotCounty() {
        return spotCounty;
    }

    public void setSpotCounty( Integer spotCounty ) {
        this.spotCounty = spotCounty;
    }

}
