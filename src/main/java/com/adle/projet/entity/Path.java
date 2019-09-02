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
@Table( name = "paths" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Path_findByUserId", query = "from Path where user_id = :userId" ),
        @org.hibernate.annotations.NamedQuery( name = "Path_findBySpotId", query = "from Path where spot_id = :spotId" ),
        @org.hibernate.annotations.NamedQuery( name = "Path_findBySectorId", query = "from Path where sector_id = :sectorId" ),
} )

public class Path {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer      id;

    @Column( name = "path_name", unique = true )
    @Size( max = 100, min = 3, message = "{path.name.invalid}" )
    private String       pathName;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "sector_id" )
    private Sector       sectorId;

    @OneToMany( mappedBy = "pathId" )
    private List<Length> lengths = new ArrayList<>();

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User         userId;

    public Path() {

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

    public Sector getSectorId() {
        return sectorId;
    }

    public void setSectorId( Sector sectorId ) {
        this.sectorId = sectorId;
    }

    public List<Length> getLengths() {
        return lengths;
    }

    public void setLengths( List<Length> lengths ) {
        this.lengths = lengths;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId( User userId ) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Path {id=" + id + ", pathName = " + pathName + "}";
    }
}
