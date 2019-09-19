package com.adle.projet.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.CountyDAO;
import com.adle.projet.entity.County;

@Service
@Transactional
public class CountyServiceImpl implements CountyService {

    @Autowired
    public CountyDAO countyDAO;

    @Override
    public List<County> getCountys() {
        return countyDAO.getCountys();
    }

    @Override
    public County getCounty( int theId ) {
        return countyDAO.getCounty( theId );
    }

    @Override
    public Map<Integer, String> getCountyNameOfCountys( List<County> countys ) {
        return countyDAO.getCountyNameOfCountys( countys );
    }

}
