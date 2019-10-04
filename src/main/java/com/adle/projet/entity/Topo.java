package com.adle.projet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

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
    private Integer       id;

    @Column( name = "topo_name" )
    @Size( max = 100, min = 3, message = "{topo.name.invalid}" )
    private String        topoName;

    @Column( name = "topo_city" )
    @Size( max = 100, min = 3, message = "{topo.city.invalid}" )
    private String        topoCity;

    @Column( name = "topo_country" )
    @Size( max = 100, min = 3, message = "{topo.country.invalid}" )
    private String        topoCountry;

    @Column( name = "topo_descriptive" )
    @Size( max = 600, min = 10, message = "La description doit contenir entre {2} et {1} charactères." )
    private String        topoDescriptive;

    @Column( name = "topo_releaseDate" )
    @Temporal( TemporalType.DATE )
    @DateTimeFormat( iso = ISO.DATE )
    private Date          topoReleaseDate;

    @Column( name = "topo_availability" )
    private Boolean       topoAvailability = false;

    @Column( name = "image1" )
    private Boolean       image1           = false;

    @Column( name = "image2" )
    private Boolean       image2           = false;

    @Column( name = "image3" )
    private Boolean       image3           = false;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User          user;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "county_id" )
    private County        county;

    @Transient
    private Integer       topoCounty;

    @Transient
    private MultipartFile topoImage1;

    @Transient
    private MultipartFile topoImage2;

    @Transient
    private MultipartFile topoImage3;

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

    public Date getTopoReleaseDate() {
        return topoReleaseDate;
    }

    public void setTopoReleaseDate( Date topoReleaseDate ) {
        this.topoReleaseDate = topoReleaseDate;
    }

    public Boolean getTopoAvailability() {
        return topoAvailability;
    }

    public void setTopoAvailability( Boolean topoAvailability ) {
        this.topoAvailability = topoAvailability;
    }

    public Boolean getImage1() {
        return image1;
    }

    public void setImage1( Boolean image1 ) {
        this.image1 = image1;
    }

    public Boolean getImage2() {
        return image2;
    }

    public void setImage2( Boolean image2 ) {
        this.image2 = image2;
    }

    public Boolean getImage3() {
        return image3;
    }

    public void setImage3( Boolean image3 ) {
        this.image3 = image3;
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

    public MultipartFile getTopoImage1() {
        return topoImage1;
    }

    public void setTopoImage1( MultipartFile topoImage1 ) {
        this.topoImage1 = topoImage1;
    }

    public MultipartFile getTopoImage2() {
        return topoImage2;
    }

    public void setTopoImage2( MultipartFile topoImage2 ) {
        this.topoImage2 = topoImage2;
    }

    public MultipartFile getTopoImage3() {
        return topoImage3;
    }

    public void setTopoImage3( MultipartFile topoImage3 ) {
        this.topoImage3 = topoImage3;
    }

    @Override
    public String toString() {
        return "Topo {id=" + id + ", topoName =" + topoName + ",topoCity =" + topoCity +
                ",topoCountry =" + topoCountry + ", topoDescriptive =" + topoDescriptive +
                ", topoReleaseDate=" + topoReleaseDate + ", topoAvailability =" +
                topoAvailability + ", image1 = " + image1 + ", image2 = " + image2 + ",image3 = " + image3 + "}";
    }
}
