package com.demo.myapplication.popularmovie.database;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.demo.myapplication.network.response.Movies;

import java.util.List;

public class PMDBPagedListDataSource extends PageKeyedDataSource<Integer, Movies> {

    PopularMoviesDAO dao;

    public PMDBPagedListDataSource(PopularMoviesDAO dao) {
        this.dao = dao;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movies> callback) {
        List movies = dao.getAllMovies();
        if (movies != null) {
            callback.onResult(movies, null, 1);
        } else {
            //Log error
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {
    }
}
