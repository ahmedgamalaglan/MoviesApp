package com.gemy.ahmed.tmas2.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.tmas2.R;
import com.gemy.ahmed.tmas2.adapters.MoviesAdapter;
import com.gemy.ahmed.tmas2.viewmodels.MoviesViewModel;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MoviesViewModel moviesViewModel;
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerView();
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getPopularMovies().observe(this, movies -> {
            moviesAdapter.setMovies(movies);
        });

    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.rv_movies_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter(this);
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                moviesViewModel.getFavoriteMovies().observe(this, movies -> {
                    moviesAdapter.setMovies(movies);
                });
            case R.id.popular:
                moviesViewModel.getPopularMovies().observe(this, movies -> {
                    moviesAdapter.setMovies(movies);
                });
            case R.id.top_rated:
                moviesViewModel.getTopRatedMovies().observe(this, movies -> {
                    moviesAdapter.setMovies(movies);
                });
        }
        return super.onOptionsItemSelected(item);
    }
}
