package com.demo.myapplication.popularmovie.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.demo.myapplication.network.response.Movies;
import com.demo.myapplication.popularmovie.repository.PopularMoviesRepository;

import io.reactivex.disposables.CompositeDisposable;

public class PopularMoviesViewModel extends AndroidViewModel {

    private CompositeDisposable compositeDisposable;
    private PopularMoviesRepository repository;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        compositeDisposable = new CompositeDisposable();
        repository = PopularMoviesRepository.getInstance(compositeDisposable, application);
    }


    public LiveData<PagedList<Movies>> getMovies() {
        return repository.getMediatorLiveData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
