package com.nb.pelisapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nb.pelisapp.data.local.db.dao.MovieDAO;
import com.nb.pelisapp.data.local.db.entity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDAO getMovieDao();

}
