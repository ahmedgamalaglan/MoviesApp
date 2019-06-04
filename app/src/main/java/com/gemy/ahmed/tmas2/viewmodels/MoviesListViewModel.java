package com.gemy.ahmed.tmas2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.repos.MoviesRepo;

import java.util.List;

public class MoviesListViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> popularMovies;
    private LiveData<List<Movie>> ratedMovies;
    private LiveData<List<Movie>> favoriteMovies;

    private MoviesRepo moviesRepo;

    public MoviesListViewModel(@NonNull Application application) {
        super(application);
        moviesRepo = new MoviesRepo(application);
    }


    public LiveData<List<Movie>> getPopularMovies() {
        if (popularMovies == null) {
            popularMovies = new MutableLiveData<>();
            popularMovies = moviesRepo.getPopularMovies();
        }
        return popularMovies;
    }


    public LiveData<List<Movie>> getRatedMovies() {
        if (ratedMovies == null) {
            ratedMovies = new MutableLiveData<>();
            ratedMovies = moviesRepo.getRatedMovies();
        }
        return ratedMovies;
    }

    public LiveData<List<Movie>> getFavoriteMovies() {

        if (favoriteMovies == null) {
            favoriteMovies = new MutableLiveData<>();
            favoriteMovies = moviesRepo.getFavoriteMovies();
        }
        return favoriteMovies;
    }

}
