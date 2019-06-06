package com.gemy.ahmed.tmas2.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.tmas2.R;
import com.gemy.ahmed.tmas2.adapters.MoviesAdapter;
import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.utilities.network.NetWorkUtils;
import com.gemy.ahmed.tmas2.viewmodels.MoviesListViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MoviesAdapter.OnListItemClickListener {

    private static final String TAG = "Main";
    private MoviesListViewModel moviesListViewModel;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;
    private TextView connectionError;
    private ActionBar actionBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        progressBar = findViewById(R.id.pb_progress_bar);
        connectionError = findViewById(R.id.tv_connection_error);

        recyclerView = findViewById(R.id.rv_movies_recycler_view);
        moviesAdapter = new MoviesAdapter(this);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, getSpanCount()));
        recyclerView.setHasFixedSize(true);


        moviesListViewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
        if (NetWorkUtils.isOnline(this)) {
            showPopularMovies();
        } else showConnectionError();

    }


    int getSpanCount() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return 2;
        else
            return 4;

    }

    private void showConnectionError() {
        progressBar.setVisibility(View.INVISIBLE);
        connectionError.setText("Connection Error");
        connectionError.setVisibility(View.VISIBLE);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        connectionError.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE
        );
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rated:
                showRatedMovies();
                break;
            case R.id.popular:
                showPopularMovies();
                break;
            case R.id.favorite:
                showFavoriteMovies();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRatedMovies() {
        showProgressBar();
        actionBar.setTitle(getResources().getString(R.string.top_rated));
        List<Movie> ratedMovies = moviesListViewModel.getRatedMovies().getValue();
        if (ratedMovies != null) {
            Log.d(TAG, "showRatedMovies: is not null");
            moviesAdapter.setMovies(ratedMovies);
            hideProgressBar();
        } else {
            Log.d(TAG, "showRatedMovies: is null");
            moviesListViewModel.getRatedMovies()
                    .observe(this, movies -> {
                        moviesAdapter.setMovies(movies);
                        hideProgressBar();
                    });
        }

    }

    private void showPopularMovies() {
        showProgressBar();
        actionBar.setTitle(getResources().getString(R.string.popular));
        List<Movie> popMovies = moviesListViewModel.getPopularMovies().getValue();
        if (popMovies != null) {
            Log.d(TAG, "showpopMovies: is not null");
            moviesAdapter.setMovies(popMovies);
            hideProgressBar();
        } else {
            Log.d(TAG, "showPopularMovies: is null");
            moviesListViewModel.getPopularMovies()
                    .observe(this, movies -> {
                        moviesAdapter.setMovies(movies);
                        hideProgressBar();
                    });
        }
    }

    private void showFavoriteMovies() {
        showProgressBar();
        actionBar.setTitle(getResources().getString(R.string.favorite));
        List<Movie> favMovies = moviesListViewModel.getFavoriteMovies().getValue();
        if (favMovies != null) {
            Log.d(TAG, "showFavoriteMovies: is not null" + favMovies.size());
            moviesAdapter.setMovies(favMovies);
            hideProgressBar();
        } else {
            Log.d(TAG, "showFavoriteMovies: is null");
            moviesListViewModel.getFavoriteMovies()
                    .observe(this, movies -> {
                        if (movies != null) {
                            Log.d(TAG, "showFavoriteMovies: observed is not null "+movies.toString());
                            moviesAdapter.setMovies(movies);
                        } else {
                            Log.d(TAG, "showFavoriteMovies:  obbserved is null");
                            hideProgressBar();
                            showNoFavoriteMoviesError();
                        }
                    });
        }
    }

    private void showNoFavoriteMoviesError() {
        progressBar.setVisibility(View.INVISIBLE);
        connectionError.setText("No Favorite Movies Found");
        recyclerView.setVisibility(View.INVISIBLE);
        connectionError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(Movie movie) {
        if (movie != null) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("Movie", movie);
            startActivity(intent);
        }
    }
}
