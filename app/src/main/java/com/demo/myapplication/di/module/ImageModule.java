package com.demo.myapplication.di.module;


import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.demo.myapplication.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return new RequestOptions()
                .placeholder(R.drawable.placeholder_front)
                .error(R.drawable.error_image_front);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(RequestOptions requestOptions, Application application) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }
}
