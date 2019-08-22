package com.demo.myapplication;

import android.os.Build;

import com.demo.myapplication.di.component.AppComponent;
import com.demo.myapplication.di.component.DaggerAppComponent;
import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MoviesApplication extends DaggerApplication {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ANRWatchDog anrWatchDog = new ANRWatchDog(10000);
            anrWatchDog.setANRListener(new ANRWatchDog.ANRListener() {
                @Override
                public void onAppNotResponding(ANRError error) {
                }
            }).setReportMainThreadOnly().start();
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        return appComponent;
    }
}