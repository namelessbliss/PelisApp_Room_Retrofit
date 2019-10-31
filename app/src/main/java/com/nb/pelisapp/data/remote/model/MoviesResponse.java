package com.nb.pelisapp.data.remote.model;

import com.nb.pelisapp.data.local.db.entity.MovieEntity;

import java.util.List;

public class MoviesResponse {

    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
