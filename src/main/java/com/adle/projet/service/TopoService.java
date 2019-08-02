package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Topo;

public interface TopoService {

    public List<Topo> getTopos();

    public void saveTopo( Topo topo );

    public Topo getTopo( int theId );

    public void updateTopo( Topo topo );

    public List<Topo> findTopoByUserId( int userId );
}
