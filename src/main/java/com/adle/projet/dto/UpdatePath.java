package com.adle.projet.dto;

import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.Type;
import com.adle.projet.entity.User;

public class UpdatePath {

    private Integer id;

    private String  pathName;

    private User    user;

    private Spot    spot;

    private Sector  sector;

    private Type    type;

    private String  typeName;

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

    public String getPathName() {
        return pathName;
    }

    public void setPathName( String pathName ) {
        this.pathName = pathName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName( String typeName ) {
        this.typeName = typeName;
    }
}