package com.adle.projet.entity;

import java.util.Set;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 * Created Path Bean defined by id and name
 * 
 * Join with Sector Bean, User Bean, Spot Bean with sector, user, spot
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "paths" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Path_findByUserId", query = "from Path where user_id = :user" ),
        @org.hibernate.annotations.NamedQuery( name = "Path_findBySectorId", query = "from Path where sector_id = :sector" ),
        @org.hibernate.annotations.NamedQuery( name = "Path_findByTypeId", query = "from Path where type_id = :type" ),
        @org.hibernate.annotations.NamedQuery( name = "Path_findById", query = "from Path as p left join fetch p.user left join fetch p.sector left join fetch p.type left join fetch p.lengths as pl left join fetch pl.listing as pll left join fetch pll.level where p.id =:pathId" ),
} )

public class Path {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer     id;

    @Column( name = "path_name", unique = true )
    @Size( max = 100, min = 3, message = "{path.name.invalid}" )
    private String      pathName;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "sector_id" )
    private Sector      sector;

    @OneToMany( mappedBy = "path" )
    private Set<Length> lengths;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User        user;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "type_id" )
    private Type        type;

    @Transient
    private Integer     pathType;

    public Path() {

    }

    @Transient
    public String typeOfPath() {
        return type.getTypeName();
    }

    @Transient
    public Integer typeIdOfPath() {
        return type.getId();
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

    public Sector getSector() {
        return sector;
    }

    public void setSector( Sector sector ) {
        this.sector = sector;
    }

    public Set<Length> getLengths() {
        return lengths;
    }

    public void setLengths( Set<Length> lengths ) {
        this.lengths = lengths;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Path {id=" + id + ", pathName = " + pathName + "}";
    }
}
