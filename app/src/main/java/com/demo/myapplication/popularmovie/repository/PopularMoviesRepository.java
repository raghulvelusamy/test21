package com.demo.myapplication.popularmovie.repository;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;

import com.demo.myapplication.popularmovie.database.PopularMoviesDatabase;
import com.demo.myapplication.popularmovie.network.NetworkRepository;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PopularMoviesRepository {

    private static PopularMoviesRepository instance;

    public static PopularMoviesRepository getInstance(CompositeDisposable compositeDisposable,
                                                      Application application) {
        if (instance == null) {
            instance = new PopularMoviesRepository(compositeDisposable, application);
        }
        return instance;
    }

    private PopularMoviesDatabase database;
    private NetworkRepository networkRepository;
    private MediatorLiveData mediatorLiveData;

    @SuppressLint("CheckResult")
    private PopularMoviesRepository(CompositeDisposable compositeDisposable, Application application) {
        mediatorLiveData = new MediatorLiveData();
        database = PopularMoviesDatabase.getInstance(application);

        PagedList.BoundaryCallback boundaryCallback = new PagedList.BoundaryCallback() {
            @Override
            public void onZeroItemsLoaded() {
                super.onZeroItemsLoaded();
                mediatorLiveData.addSource(database.getMovies(),
                        movies -> {
                            mediatorLiveData.setValue(movies);
                            mediatorLiveData.removeSource(database.getMovies());
                        });
            }
        };
        networkRepository = new NetworkRepository(compositeDisposable, boundaryCallback);

        mediatorLiveData.addSource(networkRepository.getMovies(),
                movies -> {
                    mediatorLiveData.setValue(movies);
                });

        networkRepository.getMoviesReplaySubject()
                .observeOn(Schedulers.io())
                .subscribe(movie -> {
                    database.getMoviesDao().insertMovies(movie);
                }, error -> {
                    //log
                });
    }

    public MediatorLiveData getMediatorLiveData() {
        return mediatorLiveData;
    }
}
