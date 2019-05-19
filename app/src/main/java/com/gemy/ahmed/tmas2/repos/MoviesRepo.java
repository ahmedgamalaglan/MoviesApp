package com.gemy.ahmed.tmas2.repos;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.tmas2.db.MovieDao;
import com.gemy.ahmed.tmas2.db.MoviesDataBase;
import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.utilities.Consts;
import com.gemy.ahmed.tmas2.utilities.network.NetWorkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepo {

    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private MovieDao movieDao;

    public MoviesRepo(Application application) {
        movieDao = MoviesDataBase.getInstance(application.getApplicationContext()).movieDao();
    }


    public LiveData<List<Movie>> getTopRatedMovies() {

        NetWorkUtils.getClient().getTopRatedMovies(Consts.API_KEY).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                System.out.println(response.body().toString()+"==========================================");
                movies.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });


        return movies;
    }


    public LiveData<List<Movie>> getPopularMovies() {
        NetWorkUtils.getClient().getPopularMovies(Consts.API_KEY).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movies.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
        return movies;
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return movieDao.getMovies();
    }
}