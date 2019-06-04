package com.gemy.ahmed.tmas2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gemy.ahmed.tmas2.entities.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT  * FROM movies_table")
    LiveData<List<Movie>> getMovies();

    @Query("SELECT * FROM movies_table WHERE id=:id")
    LiveData<Movie> getMovie(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

    @Query("DELETE FROM movies_table")
    void deleteAllMovies();

}
