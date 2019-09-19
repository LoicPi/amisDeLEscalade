package com.adle.projet.service;

import java.util.List;
import java.util.Map;

import com.adle.projet.entity.Type;

public interface TypeService {

    public List<Type> getTypes();

    public Type getType( int theId );

    public Map<Integer, String> getTypeNameOfTypes( List<Type> types );

}
