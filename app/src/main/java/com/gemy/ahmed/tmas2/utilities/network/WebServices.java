package com.gemy.ahmed.tmas2.utilities.network;

import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.entities.Review;
import com.gemy.ahmed.tmas2.entities.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServices {

    @GET("popular")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<Movie> getRatedMovies(@Query("api_key") String apiKey);

    @GET("{id}/reviews")
    Call<Review> getReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("{id}/videos")
    Call<Trailer> getTrailers(@Path("id") int id, @Query("api_key") String apiKey);
}
