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

@Entity
@Table( name = "roles" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "Role_findByCode", query = "from Role where roleCode = :roleCode" ),
} )

public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer    id;

    @Column( name = "role_name" )
    @Size( max = 20, min = 3 )
    private String     roleName;

    /** Colonne qui va avoir un string afin d'effectuer un where dessus **/
    @Column( name = "role_code" )
    @Size( max = 20, min = 3 )
    private String     roleCode;

    @OneToMany( mappedBy = "userRole" )
    private List<User> users = new ArrayList<>();

    public Role() {

    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName( String roleName ) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode( String roleCode ) {
        this.roleCode = roleCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers( List<User> users ) {
        this.users = users;
    }
}
