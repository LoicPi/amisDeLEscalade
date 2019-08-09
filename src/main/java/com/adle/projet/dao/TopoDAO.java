package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Topo;

public interface TopoDAO {

    public List<Topo> getTopos();

    public void saveTopo( Topo topo );

    public Topo getTopo( int theId );

    public void updateTopo( Topo topo );

    public List<Topo> findTopoByUserId( int userId );

    public void deleteTopo( int theId );
}
