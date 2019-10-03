package com.adle.projet.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.adle.projet.entity.County;
import com.adle.projet.entity.User;

public class UpdateTopo {

    private Integer       id;

    private String        updateTopoName;

    private String        updateTopoCity;

    private String        updateTopoCountry;

    private String        updateTopoDescriptive;

    private Date          updateTopoReleaseDate;

    private MultipartFile updateTopoImage1;

    private MultipartFile updateTopoImage2;

    private MultipartFile updateTopoImage3;

    private User          user;

    private County        county;

    private Integer       topoCounty;

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

    public Date getUpdateTopoReleaseDate() {
        return updateTopoReleaseDate;
    }

    public void setUpdateTopoReleaseDate( Date updateTopoReleaseDate ) {
        this.updateTopoReleaseDate = updateTopoReleaseDate;
    }

    public MultipartFile getUpdateTopoImage1() {
        return updateTopoImage1;
    }

    public void setUpdateTopoImage1( MultipartFile updateTopoImage1 ) {
        this.updateTopoImage1 = updateTopoImage1;
    }

    public MultipartFile getUpdateTopoImage2() {
        return updateTopoImage2;
    }

    public void setUpdateTopoImage2( MultipartFile updateTopoImage2 ) {
        this.updateTopoImage2 = updateTopoImage2;
    }

    public MultipartFile getUpdateTopoImage3() {
        return updateTopoImage3;
    }

    public void setUpdateTopoImage3( MultipartFile updateTopoImage3 ) {
        this.updateTopoImage3 = updateTopoImage3;
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
