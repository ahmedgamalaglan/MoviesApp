package com.gemy.ahmed.tmas2.utilities.network;

import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.utilities.Consts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {

    @GET(Consts.POPULAR)
    Call<List<Movie>> getPopularMovies(@Query("api_key") String sortBy);


    @GET(Consts.TOP_RATED)
    Call<List<Movie>> getTopRatedMovies(@Query("api_key") String sortBy);


}
