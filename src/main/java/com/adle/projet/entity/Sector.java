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
 * Created Sector Bean defined by id, name, descriptive and type
 * 
 * Join with Spot Bean and User Bean by spot and user
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "sectors" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Sector_findByUserId", query = "from Sector as r "
                + "left join fetch r.user "
                + "left join fetch r.paths as rp "
                + "left join fetch rp.type "
                + "left join fetch rp.lengths as rpl "
                + "left join fetch rpl.listing as rpll "
                + "left join fetch rpll.level as rplll "
                + "where user_id =:user" ),
        @org.hibernate.annotations.NamedQuery( name = "Sector_findById", query = "from Sector as r "
                + "left join fetch r.user "
                + "left join fetch r.paths as rp "
                + "left join fetch rp.lengths as rpl "
                + "left join fetch rp.type "
                + "left join fetch rpl.listing as rpll "
                + "left join fetch rpll.level as rplll "
                + "where r.id =:sectorId" ),
} )

public class Sector {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer   id;

    @Column( name = "sector_name", unique = true )
    @Size( max = 100, min = 3, message = "{sector.name.invalid}" )
    private String    sectorName;

    @Column( name = "sector_descriptive" )
    @Size( max = 600, min = 10, message = "{sector.descriptive.invalid}" )
    private String    sectorDescriptive;

    @Column( name = "sector_access" )
    @Size( max = 300, min = 10, message = "{sector.acces.invalid}" )
    private String    sectorAccess;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "spot_id" )
    private Spot      spot;

    @OneToMany( mappedBy = "sector" )
    private Set<Path> paths;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id" )
    private User      user;

    public Sector() {

    }

    @Transient
    public Integer highLevelId() {
        int intOfHighLevel = 0;
        for ( Path path : this.getPaths() ) {
            for ( Length length : path.getLengths() ) {
                int lengthLevel = length.getListing().getLevel().getId();
                if ( lengthLevel >= intOfHighLevel ) {
                    intOfHighLevel = lengthLevel;
                }
            }
        }
        return intOfHighLevel;
    }

    @Transient
    public Integer lowLevelId() {
        int intOfLowLevel = 0;
        for ( Path path : this.getPaths() ) {
            for ( Length length : path.getLengths() ) {
                int lengthLevel = length.getListing().getLevel().getId();
                if ( lengthLevel <= intOfLowLevel || intOfLowLevel == 0 ) {
                    intOfLowLevel = lengthLevel;
                }
            }
        }
        return intOfLowLevel;
    }

    @Transient
    private String highLevelOfSector;

    @Transient
    private String lowLevelOfSector;

    @Transient
    public Integer highListingId() {
        int intOfHighListing = 0;
        for ( Path path : this.getPaths() ) {
            for ( Length length : path.getLengths() ) {
                int lengthListing = length.getListing().getId();
                if ( lengthListing >= intOfHighListing ) {
                    intOfHighListing = lengthListing;
                }
            }
        }
        return intOfHighListing;
    }

    @Transient
    public Integer lowListingId() {
        int intOfLowListing = 0;
        for ( Path path : this.getPaths() ) {
            for ( Length length : path.getLengths() ) {
                int lengthListing = length.getListing().getId();
                if ( lengthListing <= intOfLowListing || intOfLowListing == 0 ) {
                    intOfLowListing = lengthListing;
                }
            }
        }
        return intOfLowListing;
    }

    @Transient
    private String highListingOfSector;

    @Transient
    private String lowListingOfSector;

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

    public Set<Path> getPaths() {
        return paths;
    }

    public void setPaths( Set<Path> paths ) {
        this.paths = paths;
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

    public String getHighLevelOfSector() {
        return highLevelOfSector;
    }

    public void setHighLevelOfSector( String highLevelOfSector ) {
        this.highLevelOfSector = highLevelOfSector;
    }

    public String getLowLevelOfSector() {
        return lowLevelOfSector;
    }

    public void setLowLevelOfSector( String lowLevelOfSector ) {
        this.lowLevelOfSector = lowLevelOfSector;
    }

    public String getHighListingOfSector() {
        return highListingOfSector;
    }

    public void setHighListingOfSector( String highListingOfSector ) {
        this.highListingOfSector = highListingOfSector;
    }

    public String getLowListingOfSector() {
        return lowListingOfSector;
    }

    public void setLowListingOfSector( String lowListingOfSector ) {
        this.lowListingOfSector = lowListingOfSector;
    }

    @Override
    public String toString() {
        return "Sector {id=" + id + ", sectorName = " + sectorName + ", sectorDescriptive = " + sectorDescriptive
                + ", sectorAccess = " + sectorAccess + "}";
    }

}
