package com.gemy.ahmed.tmas2.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.tmas2.R;
import com.gemy.ahmed.tmas2.adapters.ReviewsAdapter;
import com.gemy.ahmed.tmas2.adapters.TrailersAdapter;
import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.utilities.Consts;
import com.gemy.ahmed.tmas2.viewmodels.MovieViewModel;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity implements TrailersAdapter.OnTrailerClickListener {


    private ImageView movieImage;
    private TextView movieTitle;
    private TextView releaseDate;
    private RatingBar movieRatingBar;
    private TextView movieReview;
    private TextView movieRating;
    private Movie movie;
    private Button addToFavorite;
    ActionBar actionBar;
    private MovieViewModel movieViewModel;
    RecyclerView reviewsRecyclerView, trailersRecyclerView;
    LinearLayoutManager reviewsLayoutManager, trailersLayoutManager;
    ReviewsAdapter reviewsAdapter;
    TrailersAdapter trailersAdapter;
    TextView viewReviews, viewTrailers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        movie = getIntent().getParcelableExtra("Movie");
        initComponents();
        setComponentsValues();
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(movie.getTitle());
        }
        viewTrailers = findViewById(R.id.trailers);
        viewReviews = findViewById(R.id.reviews);

        trailersLayoutManager = new LinearLayoutManager(this);
        trailersRecyclerView = findViewById(R.id.rv_trailers_recyclerview);
        trailersAdapter = new TrailersAdapter(this);
        trailersRecyclerView.setAdapter(trailersAdapter);
        trailersRecyclerView.setLayoutManager(trailersLayoutManager);
        trailersRecyclerView.setHasFixedSize(true);

        reviewsLayoutManager = new LinearLayoutManager(this);
        reviewsRecyclerView = findViewById(R.id.rv_reviews_recyclerview);
        reviewsAdapter = new ReviewsAdapter();
        reviewsRecyclerView.setAdapter(reviewsAdapter);
        reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);
        reviewsRecyclerView.setHasFixedSize(true);


        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        viewTrailers.setOnClickListener(v -> {
            movieViewModel.getTrailers(movie.getId()).observe(this, trailers -> {
                assert trailers != null;
                trailersAdapter.setTrailersList(trailers);
            });
        });
        viewReviews.setOnClickListener(v -> {
            movieViewModel.getReviews(movie.getId()).observe(this, reviews -> {
                assert reviews != null;
                Toast.makeText(this, "" + reviews.size(), Toast.LENGTH_LONG).show();
                reviewsAdapter.setReviewsList(reviews);
            });
        });


        addToFavorite.setOnClickListener(v -> {
            movieViewModel.addToFavorite(movie);
            Toast.makeText(this, "Add To Favorite", Toast.LENGTH_LONG).show();
        });
    }

    private void initComponents() {
        movieImage = findViewById(R.id.iv_movie_image);
        movieTitle = findViewById(R.id.tv_movie_title);
        releaseDate = findViewById(R.id.tv_movie_release_date);
        movieReview = findViewById(R.id.tv_movie_review);
        movieRatingBar = findViewById(R.id.rb_movie_ratingBar);
        movieRating = findViewById(R.id.tv_movie_rating);
        addToFavorite = findViewById(R.id.btn_add_to_favorite);
    }

    private void setComponentsValues() {
        Picasso.get().load(Consts.IMAGE_BASE_URL + movie.getPoster_path()).into(movieImage);
        movieTitle.setText(movie.getTitle());
        releaseDate.setText(getString(R.string.release_at).concat(movie.getRelease_date()));
        movieReview.setText(movie.getOverview());
        movieRatingBar.setRating((float) movie.getVote_average());
        movieRating.setText(String.format("%s/10", movie.getVote_average()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onVideoClick(String trailerKey) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerKey));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + trailerKey));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }
}
