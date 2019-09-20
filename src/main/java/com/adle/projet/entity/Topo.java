package com.adle.projet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 * Created Topo Bean defined by id, name, city, county, country, descriptive,
 * releaseDate, avaibility
 * 
 * Join with User Bean by user
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "topos" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Topo_findByUserId", query = "from Topo where user_id = :user" ),
        @org.hibernate.annotations.NamedQuery( name = "Topo_findById", query = "from Topo as t left join fetch t.user left join fetch t.county where t.id =:topoId" ),
} )

public class Topo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer id;

    @Column( name = "topo_name" )
    @Size( max = 100, min = 3, message = "{topo.name.invalid}" )
    private String  topoName;

    @Column( name = "topo_city" )
    @Size( max = 100, min = 3, message = "{topo.city.invalid}" )
    private String  topoCity;

    @Column( name = "topo_country" )
    @Size( max = 100, min = 3, message = "{topo.country.invalid}" )
    private String  topoCountry;

    @Column( name = "topo_descriptive" )
    @Size( max = 600, min = 10, message = "La description doit contenir entre {2} et {1} charactères." )
    private String  topoDescriptive;

    @Column( name = "topo_releaseDate" )
    @Size( max = 10, min = 10, message = "{topo.releaseDate.invalid}" )
    private String  topoReleaseDate;

    @Column( name = "topo_availability" )
    private Boolean topoAvailability = false;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User    user;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "county_id" )
    private County  county;

    @Transient
    private Integer topoCounty;

    public Topo() {

    }

    @Transient
    public Integer countyOfTopo() {
        return county.getId();
    }

    public String getCountyNameOfTopo() {
        return county.getCountyName();
    }

    @Transient
    public Integer idUser() {
        return user.getId();
    }

    @Transient
    public boolean isAvailability() {
        return topoAvailability;
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

    public Boolean getTopoAvailability() {
        return topoAvailability;
    }

    public void setTopoAvailability( Boolean topoAvailability ) {
        this.topoAvailability = topoAvailability;
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

    @Override
    public String toString() {
        return "Topo {id=" + id + ", topoName =" + topoName + ",topoCity =" + topoCity +
                ",topoCountry =" + topoCountry + ", topoDescriptive =" + topoDescriptive +
                ", topoReleaseDate=" + topoReleaseDate + ", topoAvailability =" +
                topoAvailability + "}";
    }
}
