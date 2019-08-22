package com.demo.myapplication.popularmovie.network;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.demo.myapplication.network.response.Movies;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.ReplaySubject;

public class NetworkRepository {

    private static final int MAX_PAGE_SIZE = 20;
    private static final int PRE_FETCH_DISTANCE = 10;
    private PopularMoviesNetworkDataSourceFactory factory;
    private LiveData<PagedList<Movies>> movies;
    public NetworkRepository(CompositeDisposable compositeDisposable, PagedList.BoundaryCallback callback) {
        factory = new PopularMoviesNetworkDataSourceFactory(compositeDisposable);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(MAX_PAGE_SIZE)
                .setPrefetchDistance(PRE_FETCH_DISTANCE)
                .build();

        movies = new LivePagedListBuilder<>(factory, config)
                .setBoundaryCallback(callback)
                .build();

    }

    public LiveData<PagedList<Movies>> getMovies() {
        return movies;
    }

    public ReplaySubject<Movies> getMoviesReplaySubject() {
        return factory.getReplaySubject();
    }
}
