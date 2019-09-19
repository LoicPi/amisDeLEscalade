package com.adle.projet.dto;

import com.adle.projet.entity.Spot;
import com.adle.projet.entity.User;

public class UpdateSector {

    private Integer id;

    private String  updateSectorName;

    private String  updateSectorDescriptive;

    private String  updateSectorAccess;

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

    public String getUpdateSectorName() {
        return updateSectorName;
    }

    public void setUpdateSectorName( String updateSectorName ) {
        this.updateSectorName = updateSectorName;
    }

    public String getUpdateSectorDescriptive() {
        return updateSectorDescriptive;
    }

    public void setUpdateSectorDescriptive( String updateSectorDescriptive ) {
        this.updateSectorDescriptive = updateSectorDescriptive;
    }

    public String getUpdateSectorAccess() {
        return updateSectorAccess;
    }

    public void setUpdateSectorAccess( String updateSectorAccess ) {
        this.updateSectorAccess = updateSectorAccess;
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
