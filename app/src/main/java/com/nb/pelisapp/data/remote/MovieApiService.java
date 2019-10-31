package com.nb.pelisapp.data.remote;

import com.nb.pelisapp.data.remote.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    /**
     * Obtiene listado de peliculas populares del servicio web
     *
     * @return
     */
    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
}
