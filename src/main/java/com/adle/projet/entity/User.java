package com.adle.projet.entity;

import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table( name = "users" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "User_findByEmail", query = "from User where email = :email" ),
} )
/**
 * Created User Bean Defined by id, firstName, lastName, nickName, email,
 * password
 * 
 * @author Loïc
 *
 */
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer    id;

    @Column( name = "first_name" )
    @Size( max = 20, min = 3, message = "{user.firstName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un nom" )
    private String     firstName;

    @Column( name = "last_name" )
    @Size( max = 20, min = 3, message = "{user.lastName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un prénom" )
    private String     lastName;

    @Column( name = "nick_name", unique = true )
    @Size( max = 20, min = 3, message = "{user.nickName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un pseudo" )
    private String     nickName;

    @Column( name = "email", unique = true )
    @Email( message = "{user.email.invalid}" )
    @NotEmpty( message = "Merci de rentrer un email" )
    private String     email;

    @Column( name = "password" )
    @Size( min = 8, message = "{user.password.invalid}" )
    @NotEmpty( message = "Merci de rentrer un mot de passe" )
    private String     password;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "role_id" )
    private Role       userRole;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "spot_id" )
    private Spot       userSpot;

    @Transient
    private String     passwordControl;

    @Transient
    private Boolean    userMember;

    @OneToMany( mappedBy = "userId" )
    private List<Topo> topos = new ArrayList<>();

    @OneToMany( mappedBy = "userId" )
    private List<Spot> spots = new ArrayList<>();

    public User() {

    }

    @Transient
    public boolean isMember() {
        return userRole.getRoleCode().equals( "member" );
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName( String nickName ) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole( Role userRole ) {
        this.userRole = userRole;
    }

    public String getPasswordControl() {
        return passwordControl;
    }

    public void setPasswordControl( String passwordControl ) {
        this.passwordControl = passwordControl;
    }

    public Boolean getUserMember() {
        return userMember;
    }

    public void setUserMember( Boolean userMember ) {
        this.userMember = userMember;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos( List<Topo> topos ) {
        this.topos = topos;
    }

    public Spot getUserSpot() {
        return userSpot;
    }

    public void setUserSpot( Spot userSpot ) {
        this.userSpot = userSpot;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots( List<Spot> spots ) {
        this.spots = spots;
    }

    @Override
    public String toString() {
        return "User {id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
                ",nickName=" + nickName + ", email=" + email + ", password=" +
                password + "}";
    }

}
