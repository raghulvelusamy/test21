package com.demo.myapplication.popularmovie.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demo.myapplication.network.response.Movies;

@Database(version = 1, entities = {Movies.class})
public abstract class PopularMoviesDatabase extends RoomDatabase {

    private static PopularMoviesDatabase instance;
    private static LiveData<PagedList<Movies>> movies;

    public static synchronized PopularMoviesDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, PopularMoviesDatabase.class,
                    PopularMoviesDatabase.class.getName())
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract PopularMoviesDAO getMoviesDao();

    public LiveData<PagedList<Movies>> getMovies() {
        return movies;
    }

    @Override
    public void init(@NonNull DatabaseConfiguration configuration) {
        super.init(configuration);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(100)
                .build();


        PMDBPagedListDataSourceFactory factory = new PMDBPagedListDataSourceFactory(getMoviesDao());
        movies = new LivePagedListBuilder<>(factory, config).build();
    }


}
