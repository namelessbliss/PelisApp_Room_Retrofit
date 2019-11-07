package com.nb.pelisapp.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.nb.pelisapp.app.MyApp;
import com.nb.pelisapp.data.Network.NetworkBoundResource;
import com.nb.pelisapp.data.Network.Resource;
import com.nb.pelisapp.data.local.MovieRoomDatabase;
import com.nb.pelisapp.data.local.db.dao.MovieDAO;
import com.nb.pelisapp.data.local.db.entity.MovieEntity;
import com.nb.pelisapp.data.remote.ApiConstants;
import com.nb.pelisapp.data.remote.MovieApiService;
import com.nb.pelisapp.data.remote.RequestInterceptor;
import com.nb.pelisapp.data.remote.model.MoviesResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
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

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        //1° Tipo que devulve room (BD Local),2° Tipo que devuelve la API
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                movieDAO.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                //Devuelve datos que dispone en room
                return movieDAO.loadPopularMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                //Obtiene los datos de la API Remota
                return movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }
}
