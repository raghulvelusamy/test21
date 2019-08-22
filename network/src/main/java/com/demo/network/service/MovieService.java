package com.demo.network.service;

import com.demo.network.response.Movies;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {


    @GET("movie/popular")
    Single<ArrayList<Movies>> getMovies(@Query("page") int page);
}
