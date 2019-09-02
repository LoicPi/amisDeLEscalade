package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Length;

public interface LengthDAO {

    List<Length> getLengths();

    void saveLength( Length length );

    void updateLength( Length length );

    void deletePath( int theId );

    Length getLength( int theId );

    List<Length> findLengthByUserId( int userId );

    List<Length> findLengthByPathId( int pathId );

}
