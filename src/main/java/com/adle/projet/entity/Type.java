package com.adle.projet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Created Type Bean defined by id, name
 * 
 * Join with Path Bean
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "types" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Type_findByName", query = "from Type where type_name = :typeName" ),
} )
public class Type {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer    id;

    @Column( name = "type_name" )
    @Size( max = 100, min = 3, message = "{type.name.invalid}" )
    private String     typeName;

    @OneToMany( mappedBy = "type" )
    private List<Path> paths = new ArrayList<>();

    public Type() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName( String typeName ) {
        this.typeName = typeName;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths( List<Path> paths ) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "Type {id=" + id + ", typeName = " + typeName + "}";
    }

}
