package com.nb.pelisapp.data;

import com.nb.pelisapp.data.remote.ApiConstants;
import com.nb.pelisapp.data.remote.MovieApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;

    public MovieRepository() {

        //Remote > Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Inicializa servicio
        movieApiService = retrofit.create(MovieApiService.class);

    }

}
