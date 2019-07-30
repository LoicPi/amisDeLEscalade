package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.entity.Topo;

@Service
@Transactional
public class TopoServiceImpl implements TopoService {

    @Autowired
    private TopoDAO topoDAO;

    @Override
    public List<Topo> getTopos() {
        return topoDAO.getTopos();
    }

    @Override
    public void saveTopo( Topo topo ) {
        topoDAO.saveTopo( topo );

    }

    @Override
    public Topo getTopo( int theId ) {
        return topoDAO.getTopo( theId );
    }

    @Override
    public void updateTopo( Topo topo ) {
        topoDAO.updateTopo( topo );

    }

}
