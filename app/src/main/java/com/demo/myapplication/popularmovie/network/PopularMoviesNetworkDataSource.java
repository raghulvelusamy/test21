package com.demo.myapplication.popularmovie.network;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.demo.myapplication.MoviesApplication;
import com.demo.myapplication.network.response.Movies;
import com.demo.myapplication.network.service.MovieService;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;

public class PopularMoviesNetworkDataSource extends PageKeyedDataSource<Integer, Movies> {

    private static final int INITIAL_PAGE = 1;

    @Inject
    MovieService movieService;

    private ReplaySubject<Movies> replaySubject;
    private CompositeDisposable compositeDisposable;

    public PopularMoviesNetworkDataSource(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        replaySubject = ReplaySubject.create();
        MoviesApplication.getAppComponent().inject(this);
    }

    public ReplaySubject<Movies> getReplaySubject() {
        return replaySubject;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movies> callback) {
        Disposable disposable = movieService.getMovies(INITIAL_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    callback.onResult(movies, null, INITIAL_PAGE + 1);
                    //emit each movie one by one to be inserted into the database
                    for (Movies m : movies) {
                        replaySubject.onNext(m);
                    }
                }, throwable -> {
                    //this is needed for the onZeroItemsLoaded Callback so that we can get data from
                    //db and display for offline mode
                    callback.onResult(new ArrayList<>(), null, INITIAL_PAGE);
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {
        Disposable disposable = movieService.getMovies(params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    Integer key = null;
                    if (params.key < movies.get(0).getTotalPages()) key = params.key + 1;
                    callback.onResult(movies, key);
                    //emit each movie one by one to be inserted into the database
                    for (Movies m : movies) {
                        replaySubject.onNext(m);
                    }
                }, throwable -> {
                    //this is needed for the onZeroItemsLoaded Callback so that we can get data from
                    //db and display for offline mode
                    callback.onResult(new ArrayList<>(), 0);
                });

        compositeDisposable.add(disposable);
    }
}
