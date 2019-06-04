package com.gemy.ahmed.tmas2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.entities.Review;
import com.gemy.ahmed.tmas2.entities.Trailer;
import com.gemy.ahmed.tmas2.repos.MoviesRepo;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {


    private MoviesRepo moviesRepo;
    private LiveData<List<Review.Reviews>> reviews;
    private LiveData<List<Trailer.Trailers>> trailers;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        this.moviesRepo = new MoviesRepo(application);
        reviews = new MutableLiveData<>();
        trailers = new MutableLiveData<>();
    }


    public void addToFavorite(Movie movie) {
        moviesRepo.InsertMovie(movie);
    }


    public LiveData<List<Review.Reviews>> getReviews(int movieId) {
        reviews = moviesRepo.getReviews(movieId);
        return reviews;

    }

    public LiveData<List<Trailer.Trailers>> getTrailers(int movieId) {
        trailers = moviesRepo.getTrailers(movieId);
        return trailers;

    }
}
