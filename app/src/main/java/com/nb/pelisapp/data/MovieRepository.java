package com.nb.pelisapp.data;

import androidx.room.Room;

import com.nb.pelisapp.app.MyApp;
import com.nb.pelisapp.data.local.MovieRoomDatabase;
import com.nb.pelisapp.data.local.db.dao.MovieDAO;
import com.nb.pelisapp.data.remote.ApiConstants;
import com.nb.pelisapp.data.remote.MovieApiService;
import com.nb.pelisapp.data.remote.RequestInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDAO movieDAO;

    public MovieRepository() {
        // Local > ROOM
        //Acceso a las consultas de la bd
        MovieRoomDatabase movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDatabase.class,
                "db_movies")
                .build();

        movieDAO = movieRoomDatabase.getMovieDao();

        //Reques Interceptor: incluye en la cabecera (URL) de la peticion el TOKEN o api_key que autoriza consultas
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient client = okHttpClientBuilder.build();

        //Remote > Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)//vincula con el interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Inicializa servicio
        movieApiService = retrofit.create(MovieApiService.class);

    }

}
