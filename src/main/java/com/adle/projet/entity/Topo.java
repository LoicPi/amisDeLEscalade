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

@Entity
@Table( name = "topos" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Topo_findByUserId", query = "from Topo where user_id = :userId" ),
} )
/**
 * Created Topo Bean Defined by id, name, city, county, country, descriptive,
 * releaseDate, avaibility
 * 
 * Join with User Bean by userId
 * 
 * @author Loïc
 *
 */

public class Topo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer id;

    @Column( name = "topo_name" )
    @Size( max = 100, min = 3 )
    private String  topoName;

    @Column( name = "topo_city" )
    private String  topoCity;

    @Column( name = "topo_county" )
    private Integer topoCounty;

    @Column( name = "topo_country" )
    private String  topoCountry;

    @Column( name = "topo_descriptive" )
    @Size( max = 600, min = 10 )
    private String  topoDescriptive;

    @Column( name = "topo_releaseDate" )
    @Size( max = 10, min = 10 )
    private String  topoReleaseDate;

    @Column( name = "topo_availability" )
    private Boolean topoAvailability = false;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User    userId;

    public Topo() {

    }

    @Transient
    public Integer idUser() {
        return userId.getId();
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

    public Boolean getTopoAvailability() {
        return topoAvailability;
    }

    public void setTopoAvailability( Boolean topoAvailability ) {
        this.topoAvailability = topoAvailability;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId( User userId ) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Topo {id=" + id + ", topoName =" + topoName + ",topoCity =" + topoCity + ",topoCounty =" + topoCounty
                + ",topoCountry =" + topoCountry + ", topoDescriptive =" + topoDescriptive +
                ", topoReleaseDate=" + topoReleaseDate + ", topoAvailability =" +
                topoAvailability + ", userId =" + userId + "}";
    }
}
