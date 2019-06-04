package com.gemy.ahmed.tmas2.repos;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.tmas2.database.MovieDao;
import com.gemy.ahmed.tmas2.database.MoviesDataBase;
import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.entities.Review;
import com.gemy.ahmed.tmas2.entities.Trailer;
import com.gemy.ahmed.tmas2.utilities.Consts;
import com.gemy.ahmed.tmas2.utilities.network.NetWorkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepo {

    private static final String TAG = "MoviesRepo";
    private MutableLiveData<List<Movie>> movies;
    private MovieDao movieDao;

    public MoviesRepo(Application application) {
        movieDao = MoviesDataBase.getInstance(application.getApplicationContext()).movieDao();
        movies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getRatedMovies() {
        NetWorkUtils.getClient().getRatedMovies(Consts.API_KEY).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movies.postValue(response.body().getResults());
                Log.d(TAG, "onResponse: " + response.body().toString() + "==========================================");
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

        return movies;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        NetWorkUtils.getClient().getPopularMovies(Consts.API_KEY).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movies.postValue(response.body().getResults());
                Log.d(TAG, "onResponse: " + response.body().toString() + "==========================================");
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

        return movies;
    }

    public LiveData<List<Movie>> getFavoriteMovies() {

        movies.postValue(movieDao.getMovies().getValue());

        return movies;
    }

    public void InsertMovie(Movie movie) {
        new InsertToFavorite(movieDao).execute(movie);
    }

    public LiveData<Movie> getMovieFromDataBase(int id) {
        return movieDao.getMovie(id);
    }

    public LiveData<List<Review.Reviews>> getReviews(int movieId) {
        final MutableLiveData<List<Review.Reviews>> reviews = new MutableLiveData<>();
        NetWorkUtils.getClient().getReviews(movieId, Consts.API_KEY).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if (response.isSuccessful()) {
                    reviews.postValue(response.body().getReviews());
                }
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });

        return reviews;
    }

    public LiveData<List<Trailer.Trailers>> getTrailers(int movieId) {
        final MutableLiveData<List<Trailer.Trailers>> trailers = new MutableLiveData<>();
        NetWorkUtils.getClient().getTrailers(movieId, Consts.API_KEY).enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                if (response.isSuccessful()) {
                    trailers.postValue(response.body().getTrailers());
                }
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {

            }
        });

        return trailers;
    }

    public static class InsertToFavorite extends AsyncTask<Movie, Void, Void> {

        private MovieDao movieDao;

        public InsertToFavorite(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insertMovie(movies[0]);
            return null;
        }
    }

}