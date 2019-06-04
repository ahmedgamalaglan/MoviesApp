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
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                showFavoriteMovies();
                break;
            case R.id.popular:
                showPopularMovies();
                break;
            case R.id.top_rated:
                showRatedMovies();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRatedMovies() {
        showProgressBar();
        moviesListViewModel.getRatedMovies()
                .observe(this, movies -> {
                    moviesAdapter.setMovies(movies);
                    hideProgressBar();
                    actionBar.setTitle(getResources().getString(R.string.top_rated));
                });
    }

    private void showPopularMovies() {
        showProgressBar();
        moviesListViewModel.getPopularMovies()
                .observe(this, movies -> {
                    moviesAdapter.setMovies(movies);
                    hideProgressBar();
                    actionBar.setTitle(getResources().getString(R.string.popular));
                });
    }

    private void showFavoriteMovies() {
        showProgressBar();
        moviesListViewModel.getFavoriteMovies()
                .observe(this, movies -> {
                    if (movies != null) {
                        Log.d(TAG, "showFavoriteMovies: is not null");
                        moviesAdapter.setMovies(movies);
                        Log.d(TAG, "showFavoriteMovies: " + movies.size());
                    } else {
                        Log.d(TAG, "showFavoriteMovies: is null");
                        hideProgressBar();
                        actionBar.setTitle(getResources().getString(R.string.favorite));
                        showNoFavoriteMoviesError();
                    }
                });
    }

    private void showNoFavoriteMoviesError() {
        progressBar.setVisibility(View.INVISIBLE);
        connectionError.setText("No Favorite Movies Found");
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
