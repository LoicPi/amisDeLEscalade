package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Role;

public interface RoleService {

    public void saveRole( Role theRole );

    public List<Role> getRoles();

    public Role findUserRoleByCode( Boolean userMember );

}
