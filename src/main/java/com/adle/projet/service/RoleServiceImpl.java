package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.RoleDAO;
import com.adle.projet.entity.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public void saveRole( Role theRole ) {
        roleDAO.saveRole( theRole );
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }

    @Override
    public Role findRoleById( int id ) {
        return roleDAO.findRoleById( id );
    }

}
