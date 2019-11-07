package com.nb.pelisapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nb.pelisapp.data.MovieRepository;
import com.nb.pelisapp.data.Network.Resource;
import com.nb.pelisapp.data.local.db.entity.MovieEntity;

import java.util.List;

public class MovieViewModel extends ViewModel {

    // Objeto que almacena la peliculas que se obtengan del repositorio
    private final LiveData<Resource<List<MovieEntity>>> popularMovies;
    //instancia del repositorio
    private MovieRepository movieRepository;

    public MovieViewModel() {
        movieRepository = new MovieRepository();
        popularMovies = movieRepository.getPopularMovies();
    }

    /**
     * Metodo que devuelve el listado de pelis polulares
     */
    public LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        return popularMovies;
    }
}