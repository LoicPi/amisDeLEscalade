package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.Length;

public interface LengthDAO {

    public List<Length> getLengths();

    public void saveLength( Length length );

    public void updateLength( Length length );

    public void deleteLength( int theId );

    public Length getLength( int theId );

    public List<Length> findLengthByUserId( int userId );

    public List<Length> findLengthByPathId( int pathId );

    public List<Length> findLengthByListingId( int listingId );

    public List<Length> findLengthByLevelId( int levelId );

}
