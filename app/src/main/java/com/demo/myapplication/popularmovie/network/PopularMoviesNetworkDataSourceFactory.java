package com.demo.myapplication.popularmovie.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.demo.myapplication.network.response.Movies;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.ReplaySubject;

public class PopularMoviesNetworkDataSourceFactory extends DataSource.Factory {

    private PopularMoviesNetworkDataSource dataSource;
    private MutableLiveData<PopularMoviesNetworkDataSource> dataSourceMutableLiveData;

    public PopularMoviesNetworkDataSourceFactory(CompositeDisposable compositeDisposable) {
        dataSource = new PopularMoviesNetworkDataSource(compositeDisposable);
        dataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public ReplaySubject<Movies> getReplaySubject() {
        return dataSource.getReplaySubject();
    }

}
