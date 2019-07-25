package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Role;

public interface RoleDAO {

    public void saveRole( Role theRole );

    public List<Role> getRoles();

    public Role findUserRoleByCode( Boolean userMember );

}
