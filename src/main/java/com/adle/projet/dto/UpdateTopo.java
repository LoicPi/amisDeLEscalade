package com.adle.projet.dto;

import com.adle.projet.entity.County;
import com.adle.projet.entity.User;

public class UpdateTopo {

    private Integer id;

    private String  updateTopoName;

    private String  updateTopoCity;

    private String  updateTopoCountry;

    private String  updateTopoDescriptive;

    private String  updateTopoReleaseDate;

    private User    user;

    private County  county;

    private Integer topoCounty;

    public Integer idUser() {
        return user.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getUpdateTopoName() {
        return updateTopoName;
    }

    public void setUpdateTopoName( String updateTopoName ) {
        this.updateTopoName = updateTopoName;
    }

    public String getUpdateTopoCity() {
        return updateTopoCity;
    }

    public void setUpdateTopoCity( String updateTopoCity ) {
        this.updateTopoCity = updateTopoCity;
    }

    public String getUpdateTopoCountry() {
        return updateTopoCountry;
    }

    public void setUpdateTopoCountry( String updateTopoCountry ) {
        this.updateTopoCountry = updateTopoCountry;
    }

    public String getUpdateTopoDescriptive() {
        return updateTopoDescriptive;
    }

    public void setUpdateTopoDescriptive( String updateTopoDescriptive ) {
        this.updateTopoDescriptive = updateTopoDescriptive;
    }

    public String getUpdateTopoReleaseDate() {
        return updateTopoReleaseDate;
    }

    public void setUpdateTopoReleaseDate( String updateTopoReleaseDate ) {
        this.updateTopoReleaseDate = updateTopoReleaseDate;
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

    public Integer getTopoCounty() {
        return topoCounty;
    }

    public void setTopoCounty( Integer topoCounty ) {
        this.topoCounty = topoCounty;
    }

}
