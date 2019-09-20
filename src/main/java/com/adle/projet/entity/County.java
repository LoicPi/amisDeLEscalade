package com.adle.projet.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table( name = "countys" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "County_findByName", query = "from County where county_name = :countyName" ),
} )
public class County {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer   id;

    @Column( name = "county_name" )
    @Size( max = 100, min = 3, message = "{county.name.invalid}" )
    private String    countyName;

    @OneToMany( mappedBy = "county" )
    private Set<Spot> spots;

    @OneToMany( mappedBy = "county" )
    private Set<Topo> topos;

    public County() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName( String countyName ) {
        this.countyName = countyName;
    }

    public Set<Spot> getSpots() {
        return spots;
    }

    public void setSpots( Set<Spot> spots ) {
        this.spots = spots;
    }

    public Set<Topo> getTopos() {
        return topos;
    }

    public void setTopos( Set<Topo> topos ) {
        this.topos = topos;
    }

    @Override
    public String toString() {
        return "County {id=" + id + ", countyName = " + countyName + "}";
    }
}
