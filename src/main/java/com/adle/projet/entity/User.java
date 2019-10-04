package com.adle.projet.entity;

import java.util.ArrayList;
import java.util.List;
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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created User Bean defined by id, firstName, lastName, nickName, email,
 * password
 * 
 * Join with Role by role
 * 
 * @author Loïc
 *
 */

@Entity
@Table( name = "users" )
@org.hibernate.annotations.NamedQueries( {
        @org.hibernate.annotations.NamedQuery( name = "User_findByEmail", query = "from User where email = :email" ),
        @org.hibernate.annotations.NamedQuery( name = "User_findById", query = "from User as u left join fetch u.topos as tps left join fetch u.spots as sts left join fetch tps.county left join fetch sts.county where u.id =:userId" ),
} )

public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Integer       id;

    @Column( name = "first_name" )
    @Size( max = 50, min = 3, message = "{user.firstName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un nom" )
    private String        firstName;

    @Column( name = "last_name" )
    @Size( max = 50, min = 3, message = "{user.lastName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un prénom" )
    private String        lastName;

    @Column( name = "nick_name", unique = true )
    @Size( max = 50, min = 3, message = "{user.nickName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un pseudo" )
    private String        nickName;

    @Column( name = "email", unique = true )
    @Email( message = "{user.email.invalid}" )
    @NotEmpty( message = "Merci de rentrer un email" )
    private String        email;

    @Column( name = "password" )
    @Size( min = 8, message = "{user.password.invalid}" )
    @NotEmpty( message = "Merci de rentrer un mot de passe" )
    private String        password;

    @Column( name = "image" )
    private Boolean       image    = false;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "role_id" )
    private Role          role;

    @Transient
    private String        passwordControl;

    @Transient
    private Boolean       userMember;

    @Transient
    private MultipartFile userImage;

    @OneToMany( mappedBy = "user" )
    private Set<Topo>     topos;

    @OneToMany( mappedBy = "user" )
    private Set<Spot>     spots;

    @OneToMany( mappedBy = "user" )
    private List<Sector>  sectors  = new ArrayList<>();

    @OneToMany( mappedBy = "user" )
    private List<Path>    paths    = new ArrayList<>();

    @OneToMany( mappedBy = "user" )
    private List<Length>  lengths  = new ArrayList<>();

    @OneToMany( mappedBy = "user" )
    private List<Comment> comments = new ArrayList<>();

    public User() {

    }

    @Transient
    public boolean isMember() {
        return role.getRoleCode().equals( "member" );
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

    public boolean isImage() {
        return image;
    }

    public void setImage( boolean image ) {
        this.image = image;
    }

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
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

    public MultipartFile getUserImage() {
        return userImage;
    }

    public void setUserImage( MultipartFile userImage ) {
        this.userImage = userImage;
    }

    public Set<Topo> getTopos() {
        return topos;
    }

    public void setTopos( Set<Topo> topos ) {
        this.topos = topos;
    }

    public Set<Spot> getSpots() {
        return spots;
    }

    public void setSpots( Set<Spot> spots ) {
        this.spots = spots;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors( List<Sector> sectors ) {
        this.sectors = sectors;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths( List<Path> paths ) {
        this.paths = paths;
    }

    public List<Length> getLengths() {
        return lengths;
    }

    public void setLengths( List<Length> lengths ) {
        this.lengths = lengths;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments( List<Comment> comments ) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User {id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
                ",nickName=" + nickName + ", email=" + email + ", password=" + password + ", image = " + image + "}";
    }

}
