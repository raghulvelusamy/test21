package com.demo.myapplication.di.module;


import com.demo.myapplication.network.config.RetrofitConfig;
import com.demo.myapplication.network.service.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    static MovieService providesMovieService(Retrofit retrofit) {
        return (MovieService) RetrofitConfig.getService(retrofit, MovieService.class);
    }

    @Singleton
    @Provides
    static Retrofit providesRetrofit() {
        return RetrofitConfig.getInstance();
    }
}
