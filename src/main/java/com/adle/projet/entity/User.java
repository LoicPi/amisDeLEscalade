package com.adle.projet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table( name = "users" )
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
    private Integer id;

    @Column( name = "first_name" )
    @Size( max = 20, min = 3, message = "{user.firstName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un nom" )
    private String  firstName;

    @Column( name = "last_name" )
    @Size( max = 20, min = 3, message = "{user.lastName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un prénom" )
    private String  lastName;

    @Column( name = "nick_name", unique = true )
    @Size( max = 20, min = 3, message = "{user.nickName.invalid}" )
    @NotEmpty( message = "Merci de rentrer un pseudo" )
    private String  nickName;

    @Column( name = "email", unique = true )
    @Email( message = "{user.email.invalid}" )
    @NotEmpty( message = "Merci de rentrer un email" )
    private String  email;

    @Column( name = "password" )
    @Size( min = 8, message = "{user.password.invalid}" )
    @NotEmpty( message = "Merci de rentrer un mot de passe" )
    private String  password;

    public User() {

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

    @Override
    public String toString() {
        return "User {id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
                ",nickName=" + nickName + ", email=" + email + ", password=" +
                password + "}";
    }

}
