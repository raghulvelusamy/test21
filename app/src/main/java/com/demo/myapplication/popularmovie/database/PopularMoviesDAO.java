package com.demo.myapplication.popularmovie.database;


import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.demo.myapplication.network.response.Movies;

import java.util.List;

@Dao
public interface PopularMoviesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovies(Movies movies);

    @Query("DELETE FROM movies")
    void deleteAllMovies();

    @Query("SELECT * FROM movies")
    List<Movies> getAllMovies();

}
