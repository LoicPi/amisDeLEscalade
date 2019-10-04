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

import org.springframework.web.multipart.MultipartFile;

/**
 * Created Spot Bean defined by id, name, city, county, country, descriptive,
 * access and tag
 * 
 * Join with User Bean by user
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "spots" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Spot_findByUserId", query = "from Spot where user_id = :user" ),
        @org.hibernate.annotations.NamedQuery( name = "Spot_findById", query = "from Spot as s left join fetch s.user left join fetch s.county left join fetch s.sectors as sse left join fetch s.comments left join fetch sse.paths where s.id =:spotId" ),
} )

public class Spot {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer      id;

    @Column( name = "spot_name", unique = true )
    @Size( max = 100, min = 3, message = "{spot.name.invalid}" )
    private String       spotName;

    @Column( name = "spot_city" )
    @Size( max = 100, min = 3, message = "{spot.city.invalid}" )
    private String       spotCity;

    @Column( name = "spot_country" )
    @Size( max = 100, min = 3, message = "{spot.country.invalid}" )
    private String       spotCountry;

    @Column( name = "spot_descriptive" )
    @Size( max = 600, min = 10, message = "{spot.descriptive.invalid}" )
    private String       spotDescriptive;

    @Column( name = "spot_access" )
    @Size( max = 300, min = 10, message = "{spot.access.invalid}" )
    private String       spotAccess;

    @Column( name = "spot_tag" )
    private Boolean      spotTag = false;

    @Column( name = "image1" )
    private Boolean      image1  = false;

    @Column( name = "image2" )
    private Boolean      image2  = false;

    @Column( name = "image3" )
    private Boolean      image3  = false;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User         user;

    @OneToMany( mappedBy = "spot" )
    private Set<Sector>  sectors;

    @OneToMany( mappedBy = "spot" )
    private Set<Comment> comments;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "county_id" )
    private County       county;

    @Transient
    private Integer      spotCounty;

    @Transient
    public boolean isTag() {
        return spotTag;
    }

    @Transient
    private MultipartFile spotImage1;

    @Transient
    private MultipartFile spotImage2;

    @Transient
    private MultipartFile spotImage3;

    public Spot() {

    }

    @Transient
    public Integer countyOfSpot() {
        return county.getId();
    }

    @Transient
    public Integer highLevelId() {

        int intOfHighLevel = 0;
        for ( Sector sector : this.getSectors() ) {
            for ( Path path : sector.getPaths() ) {
                for ( Length length : path.getLengths() ) {
                    int lengthLevel = length.getListing().getLevel().getId();
                    if ( lengthLevel >= intOfHighLevel ) {
                        intOfHighLevel = lengthLevel;
                    }
                }
            }
        }
        return intOfHighLevel;
    }

    @Transient
    public Integer lowLevelId() {

        int intOfLowLevel = 0;
        for ( Sector sector : this.getSectors() ) {
            for ( Path path : sector.getPaths() ) {
                for ( Length length : path.getLengths() ) {
                    int lengthLevel = length.getListing().getLevel().getId();
                    if ( lengthLevel <= intOfLowLevel || intOfLowLevel == 0 ) {
                        intOfLowLevel = lengthLevel;
                    }
                }
            }
        }
        return intOfLowLevel;
    }

    @Transient
    private String highLevelOfSpot;

    @Transient
    private String lowLevelOfSpot;

    @Transient
    public Integer highListingId() {

        int intOfHighListing = 0;
        for ( Sector sector : this.getSectors() ) {
            for ( Path path : sector.getPaths() ) {
                for ( Length length : path.getLengths() ) {
                    int lengthListing = length.getListing().getId();
                    if ( lengthListing >= intOfHighListing ) {
                        intOfHighListing = lengthListing;
                    }
                }
            }
        }
        return intOfHighListing;
    }

    @Transient
    public Integer lowListingId() {

        int intOfLowListing = 0;
        for ( Sector sector : this.getSectors() ) {
            for ( Path path : sector.getPaths() ) {
                for ( Length length : path.getLengths() ) {
                    int lengthListing = length.getListing().getId();
                    if ( lengthListing <= intOfLowListing || intOfLowListing == 0 ) {
                        intOfLowListing = lengthListing;
                    }
                }
            }
        }
        return intOfLowListing;
    }

    @Transient
    private String highListingOfSpot;

    @Transient
    private String lowListingOfSpot;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName( String spotName ) {
        this.spotName = spotName;
    }

    public String getSpotCity() {
        return spotCity;
    }

    public void setSpotCity( String spotCity ) {
        this.spotCity = spotCity;
    }

    public String getSpotCountry() {
        return spotCountry;
    }

    public void setSpotCountry( String spotCountry ) {
        this.spotCountry = spotCountry;
    }

    public String getSpotDescriptive() {
        return spotDescriptive;
    }

    public void setSpotDescriptive( String spotDescriptive ) {
        this.spotDescriptive = spotDescriptive;
    }

    public String getSpotAccess() {
        return spotAccess;
    }

    public void setSpotAccess( String spotAccess ) {
        this.spotAccess = spotAccess;
    }

    public Boolean getSpotTag() {
        return spotTag;
    }

    public void setSpotTag( Boolean spotTag ) {
        this.spotTag = spotTag;
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

    public Set<Sector> getSectors() {
        return sectors;
    }

    public void setSectors( Set<Sector> sectors ) {
        this.sectors = sectors;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments( Set<Comment> comments ) {
        this.comments = comments;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty( County county ) {
        this.county = county;
    }

    public Integer getSpotCounty() {
        return spotCounty;
    }

    public void setSpotCounty( Integer spotCounty ) {
        this.spotCounty = spotCounty;
    }

    public String getHighLevelOfSpot() {
        return highLevelOfSpot;
    }

    public void setHighLevelOfSpot( String highLevelOfSpot ) {
        this.highLevelOfSpot = highLevelOfSpot;
    }

    public String getLowLevelOfSpot() {
        return lowLevelOfSpot;
    }

    public void setLowLevelOfSpot( String lowLevelOfSpot ) {
        this.lowLevelOfSpot = lowLevelOfSpot;
    }

    public String getHighListingOfSpot() {
        return highListingOfSpot;
    }

    public void setHighListingOfSpot( String highListingOfSpot ) {
        this.highListingOfSpot = highListingOfSpot;
    }

    public String getLowListingOfSpot() {
        return lowListingOfSpot;
    }

    public void setLowListingOfSpot( String lowListingOfSpot ) {
        this.lowListingOfSpot = lowListingOfSpot;
    }

    public MultipartFile getSpotImage1() {
        return spotImage1;
    }

    public void setSpotImage1( MultipartFile spotImage1 ) {
        this.spotImage1 = spotImage1;
    }

    public MultipartFile getSpotImage2() {
        return spotImage2;
    }

    public void setSpotImage2( MultipartFile spotImage2 ) {
        this.spotImage2 = spotImage2;
    }

    public MultipartFile getSpotImage3() {
        return spotImage3;
    }

    public void setSpotImage3( MultipartFile spotImage3 ) {
        this.spotImage3 = spotImage3;
    }

    @Override
    public String toString() {
        return "Spot {id=" + id + ", spotName =" + spotName + ",spotCity =" + spotCity +
                ",spotCountry =" + spotCountry + ", spotDescriptive =" + spotDescriptive +
                ", spotAccess=" + spotAccess + "}";
    }
}
