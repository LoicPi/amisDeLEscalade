package com.adle.projet.dto;

import com.adle.projet.entity.Role;

public class UpdateUser {

    private Integer id;

    private String  firstName;

    private String  lastName;

    private String  nickName;

    private String  email;

    private Role    userRole;

    private Boolean userMember;

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

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole( Role userRole ) {
        this.userRole = userRole;
    }

    public Boolean getUserMember() {
        return userMember;
    }

    public void setUserMember( Boolean userMember ) {
        this.userMember = userMember;
    }

}
