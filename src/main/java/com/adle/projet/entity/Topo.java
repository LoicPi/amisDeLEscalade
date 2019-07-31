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
import javax.validation.constraints.Size;

@Entity
@Table( name = "topos" )
public class Topo {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer id;

    @Column( name = "topo_name" )
    @Size( max = 60, min = 3 )
    private String  topoName;

    @Column( name = "topo_descriptive" )
    @Size( max = 300, min = 10 )
    private String  topoDescriptive;

    @Column( name = "topo_location" )
    @Size( max = 100, min = 3 )
    private String  topoLocation;

    @Column( name = "topo_department" )
    private Integer topoDepartment;

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

    public String getTopoDescriptive() {
        return topoDescriptive;
    }

    public void setTopoDescriptive( String topoDescriptive ) {
        this.topoDescriptive = topoDescriptive;
    }

    public String getTopoLocation() {
        return topoLocation;
    }

    public void setTopoLocation( String topoLocation ) {
        this.topoLocation = topoLocation;
    }

    public Integer getTopoDepartment() {
        return topoDepartment;
    }

    public void setTopoDepartment( Integer topoDepartment ) {
        this.topoDepartment = topoDepartment;
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
        return "Topo {id=" + id + ", topoName =" + topoName + ", topoDescriptive =" + topoDescriptive +
                ",topoLocation =" + topoLocation + ", topoReleaseDate=" + topoReleaseDate + ", topoAvailability =" +
                topoAvailability + ", userId =" + userId + "}";
    }
}
