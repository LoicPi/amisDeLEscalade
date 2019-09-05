package com.adle.projet.dto;

import com.adle.projet.entity.Role;

public class UpdateUser {

    private Integer id;

    private String  firstName;

    private String  lastName;

    private String  nickName;

    private String  email;

    private Role    role;

    private Boolean userMember;

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

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    public Boolean getUserMember() {
        return userMember;
    }

    public void setUserMember( Boolean userMember ) {
        this.userMember = userMember;
    }

}
