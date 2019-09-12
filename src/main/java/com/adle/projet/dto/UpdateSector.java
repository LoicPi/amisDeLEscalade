package com.adle.projet.dto;

import com.adle.projet.entity.Spot;
import com.adle.projet.entity.User;

public class UpdateSector {

    private Integer id;

    private String  sectorName;

    private String  sectorDescriptive;

    private String  sectorAccess;

    private User    user;

    private Spot    spot;

    public Integer sectorIdUser() {
        return user.getId();
    }

    public Integer sectorIdSpot() {
        return spot.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName( String sectorName ) {
        this.sectorName = sectorName;
    }

    public String getSectorDescriptive() {
        return sectorDescriptive;
    }

    public void setSectorDescriptive( String sectorDescriptive ) {
        this.sectorDescriptive = sectorDescriptive;
    }

    public String getSectorAccess() {
        return sectorAccess;
    }

    public void setSectorAccess( String sectorAccess ) {
        this.sectorAccess = sectorAccess;
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

}
