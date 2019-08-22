package com.demo.myapplication.di.component;


import android.app.Application;

import com.demo.myapplication.MoviesApplication;
import com.demo.myapplication.di.module.ImageModule;
import com.demo.myapplication.di.module.NetworkModule;
import com.demo.myapplication.popularmovie.network.PopularMoviesNetworkDataSource;
import com.demo.myapplication.popularmovie.ui.PopularMoviesAdapter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        ImageModule.class,
        NetworkModule.class})
public interface AppComponent extends AndroidInjector<MoviesApplication> {

    void inject (PopularMoviesAdapter adapter);

    void inject(PopularMoviesNetworkDataSource dataSource);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }
}
