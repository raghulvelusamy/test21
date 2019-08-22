package com.demo.myapplication.network.service;

import com.demo.myapplication.network.response.Movies;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {


    @GET("movie/popular")
    Single<ArrayList<Movies>> getMovies(@Query("page") int page);
}
