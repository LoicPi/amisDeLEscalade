package com.adle.projet.dto;

import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.Type;
import com.adle.projet.entity.User;

public class UpdatePath {

    private Integer id;

    private String  updatePathName;

    private User    user;

    private Sector  sector;

    private Type    type;

    private Integer pathType;

    public Integer pathIdUser() {
        return user.getId();
    }

    public Integer pathIdSpot() {
        return spot.getId();
    }

    public Integer pathIdSector() {
        return sector.getId();
    }

    public String pathTypeName() {
        return type.getTypeName();
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getUpdatePathName() {
        return updatePathName;
    }

    public void setUpdatePathName( String updatePathName ) {
        this.updatePathName = updatePathName;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot( Spot spot ) {
        this.spot = spot;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector( Sector sector ) {
        this.sector = sector;
    }

    public Type getType() {
        return type;
    }

    public void setType( Type type ) {
        this.type = type;
    }

    public Integer getPathType() {
        return pathType;
    }

    public void setPathType( Integer pathType ) {
        this.pathType = pathType;
    }

}
