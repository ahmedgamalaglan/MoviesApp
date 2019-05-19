package com.gemy.ahmed.tmas2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.repos.MoviesRepo;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Movie>> movies;
    private MoviesRepo moviesRepo;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movies = new MutableLiveData<>();
        moviesRepo = new MoviesRepo(application);
    }

    public LiveData<List<Movie>> getTopRatedMovies() {
        return moviesRepo.getTopRatedMovies();
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return moviesRepo.getPopularMovies();
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return moviesRepo.getFavoriteMovies();
    }
}
