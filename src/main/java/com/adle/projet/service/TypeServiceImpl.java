package com.adle.projet.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.TypeDAO;
import com.adle.projet.entity.Type;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDAO typeDAO;

    @Override
    public List<Type> getTypes() {
        return typeDAO.getTypes();
    }

    @Override
    public Type getType( int theId ) {
        return typeDAO.getType( theId );
    }

    @Override
    public Map<Integer, String> getTypeNameOfTypes( List<Type> types ) {
        return typeDAO.getTypeNameOfTypes( types );
    }

}
