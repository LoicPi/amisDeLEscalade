package com.adle.projet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table( name = "sectors" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Sector_findByUserId", query = "from Sector where user_id = :userId" ),
        @org.hibernate.annotations.NamedQuery( name = "Sector_findBySpotId", query = "from Sector where spot_id = :spotId" ),
} )

public class Sector {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer    id;

    @Column( name = "sector_name", unique = true )
    @Size( max = 100, min = 3, message = "{sector.name.invalid}" )
    private String     sectorName;

    @Column( name = "sector_descriptive" )
    @Size( max = 600, min = 10, message = "{sector.descriptive.invalid}" )
    private String     sectorDescriptive;

    @Column( name = "sector_type" )
    @Size( max = 8, min = 3, message = "{sector.type.invalid" )
    private String     sectorType;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "spot_id" )
    private Spot       spotId;

    @OneToMany( mappedBy = "sectorId" )
    private List<Path> paths = new ArrayList<>();

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User       userId;

    public Sector() {

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

    public String getSectorType() {
        return sectorType;
    }

    public void setSectorType( String sectorType ) {
        this.sectorType = sectorType;
    }

    public Spot getSpotId() {
        return spotId;
    }

    public void setSpotId( Spot spotId ) {
        this.spotId = spotId;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths( List<Path> paths ) {
        this.paths = paths;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId( User userId ) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Sector {id=" + id + ", sectorName = " + sectorName + ", sectorDescriptive = " + sectorDescriptive
                + ", sectorType = " + sectorType + "}";
    }

}
