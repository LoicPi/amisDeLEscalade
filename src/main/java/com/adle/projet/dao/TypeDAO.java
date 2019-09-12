package com.adle.projet.dao;

import java.util.List;
import java.util.Map;

import com.adle.projet.entity.Type;

public interface TypeDAO {

    public List<Type> getTypes();

    public Type getType( int theId );

    public Map<String, String> getTypeNameOfTypes( List<Type> types );

    public Type findTypeByNameOfType( String nameType );

}
