package com.adle.projet.service;

import java.util.List;
import java.util.Map;

import com.adle.projet.entity.County;

public interface CountyService {

    public List<County> getCountys();

    public County getCounty( int theId );

    public Map<Integer, String> getCountyNameOfCountys( List<County> countys );

}
