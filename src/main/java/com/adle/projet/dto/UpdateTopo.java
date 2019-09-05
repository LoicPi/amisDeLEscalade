package com.adle.projet.dto;

import com.adle.projet.entity.User;

public class UpdateTopo {

    private Integer id;

    private String  topoName;

    private String  topoCity;

    private Integer topoCounty;

    private String  topoCountry;

    private String  topoDescriptive;

    private String  topoReleaseDate;

    private User    user;

    public Integer idUser() {
        return user.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getTopoName() {
        return topoName;
    }

    public void setTopoName( String topoName ) {
        this.topoName = topoName;
    }

    public String getTopoCity() {
        return topoCity;
    }

    public void setTopoCity( String topoCity ) {
        this.topoCity = topoCity;
    }

    public Integer getTopoCounty() {
        return topoCounty;
    }

    public void setTopoCounty( Integer topoCounty ) {
        this.topoCounty = topoCounty;
    }

    public String getTopoCountry() {
        return topoCountry;
    }

    public void setTopoCountry( String topoCountry ) {
        this.topoCountry = topoCountry;
    }

    public String getTopoDescriptive() {
        return topoDescriptive;
    }

    public void setTopoDescriptive( String topoDescriptive ) {
        this.topoDescriptive = topoDescriptive;
    }

    public String getTopoReleaseDate() {
        return topoReleaseDate;
    }

    public void setTopoReleaseDate( String topoReleaseDate ) {
        this.topoReleaseDate = topoReleaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

}
