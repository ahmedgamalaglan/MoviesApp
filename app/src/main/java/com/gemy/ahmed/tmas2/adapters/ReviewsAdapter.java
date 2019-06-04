package com.gemy.ahmed.tmas2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.tmas2.R;
import com.gemy.ahmed.tmas2.entities.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private List<Review.Reviews> reviewsList = new ArrayList<>();

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        //Review.Reviews currentReview = reviewsList.get(position);
        holder.reviewName.setText(reviewsList.get(position).getAuthor());
        holder.reviewText.setText(reviewsList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        if (reviewsList == null)
            return 0;
        return reviewsList.size();
    }

    public void setReviewsList(List<Review.Reviews> reviews) {
        reviewsList = reviews;
        notifyDataSetChanged();
    }

    class ReviewsViewHolder extends RecyclerView.ViewHolder {
        TextView reviewName;
        TextView reviewText;

        ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewName = itemView.findViewById(R.id.tv_movie_reviews_name);
            reviewText = itemView.findViewById(R.id.tv_movie_reviews_text);
        }
    }


}
