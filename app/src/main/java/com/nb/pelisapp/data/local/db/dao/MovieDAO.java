package com.nb.pelisapp.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nb.pelisapp.data.local.db.entity.MovieEntity;

import java.util.List;

/**
 * Define los metodos de consultas a la base de datos
 */
public interface MovieDAO {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadPopularMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntityList);

}
