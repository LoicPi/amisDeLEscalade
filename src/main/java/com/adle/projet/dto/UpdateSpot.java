package com.adle.projet.dto;

import org.springframework.web.multipart.MultipartFile;

import com.adle.projet.entity.County;
import com.adle.projet.entity.User;

public class UpdateSpot {

    private Integer       id;

    private String        updateSpotName;

    private String        updateSpotCity;

    private String        updateSpotCountry;

    private String        updateSpotDescriptive;

    private String        updateSpotAccess;

    private Boolean       updateSpotTag;

    private MultipartFile updateSpotImage1;

    private MultipartFile updateSpotImage2;

    private MultipartFile updateSpotImage3;

    private User          user;

    private County        county;

    private Integer       spotCounty;

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

    public MultipartFile getUpdateSpotImage1() {
        return updateSpotImage1;
    }

    public void setUpdateSpotImage1( MultipartFile updateSpotImage1 ) {
        this.updateSpotImage1 = updateSpotImage1;
    }

    public MultipartFile getUpdateSpotImage2() {
        return updateSpotImage2;
    }

    public void setUpdateSpotImage2( MultipartFile updateSpotImage2 ) {
        this.updateSpotImage2 = updateSpotImage2;
    }

    public MultipartFile getUpdateSpotImage3() {
        return updateSpotImage3;
    }

    public void setUpdateSpotImage3( MultipartFile updateSpotImage3 ) {
        this.updateSpotImage3 = updateSpotImage3;
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
