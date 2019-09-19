package com.adle.projet.dto;

import com.adle.projet.entity.Role;

public class UpdateUser {

    private Integer id;

    private String  updateFirstName;

    private String  updateLastName;

    private String  updateNickName;

    private String  updateEmail;

    private Role    role;

    private Boolean userMember;

    public boolean getMember() {
        return role.getRoleCode().equals( "member" );
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getUpdateFirstName() {
        return updateFirstName;
    }

    public void setUpdateFirstName( String updateFirstName ) {
        this.updateFirstName = updateFirstName;
    }

    public String getUpdateLastName() {
        return updateLastName;
    }

    public void setUpdateLastName( String updateLastName ) {
        this.updateLastName = updateLastName;
    }

    public String getUpdateNickName() {
        return updateNickName;
    }

    public void setUpdateNickName( String updateNickName ) {
        this.updateNickName = updateNickName;
    }

    public String getUpdateEmail() {
        return updateEmail;
    }

    public void setUpdateEmail( String updateEmail ) {
        this.updateEmail = updateEmail;
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
