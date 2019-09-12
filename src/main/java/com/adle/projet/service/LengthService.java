package com.adle.projet.service;

import java.util.List;

import com.adle.projet.entity.Length;
import com.adle.projet.entity.Path;

public interface LengthService {

    public List<Length> getLengths();

    public void saveLength( Length length );

    public void updateLength( Length length );

    public void deleteLength( int theId );

    public Length getLength( int theId );

    public List<Length> findLengthByUserId( int userId );

    public List<Length> findLengthByPathId( int pathId );

    public List<Length> findLengthByListingId( int listingId );

    public List<Length> findLengthByLevelId( int levelId );

    public void deleteLengths( List<Length> lengths );

    public List<Length> findLengthsByPaths( List<Path> paths );

}
