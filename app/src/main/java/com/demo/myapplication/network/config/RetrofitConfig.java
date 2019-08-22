package com.demo.myapplication.network.config;

import com.demo.myapplication.network.interceptor.RequestInterceptor;
import com.demo.myapplication.network.response.Movies;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static <T> T getService(Retrofit retrofit, Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static Retrofit getInstance() {
        OkHttpClient okHttpClient = getOkHttpClient();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    public static Gson getGson() {
        Type type = (new ArrayList<Movies>()).getClass();
        return new GsonBuilder()
                .registerTypeAdapter(type, new ResultTypeConverter())
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(interceptor);
        return okHttpBuilder.build();
    }
}