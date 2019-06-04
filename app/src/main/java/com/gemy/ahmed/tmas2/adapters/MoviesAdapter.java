package com.gemy.ahmed.tmas2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.tmas2.R;
import com.gemy.ahmed.tmas2.entities.Movie;
import com.gemy.ahmed.tmas2.utilities.Consts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnListItemClickListener onListItemClickListener;

    public MoviesAdapter(OnListItemClickListener listener) {
        this.onListItemClickListener = listener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (movies == null)
            return 0;
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        private MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie_item);
            itemView.setOnClickListener(this);
        }

        void bind(int index) {
            Picasso.get()
                    .load(Consts.IMAGE_BASE_URL + movies.get(index).getPoster_path())
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getAdapterPosition();
            Movie movie = movies.get(itemPosition);
            onListItemClickListener.onItemClick(movie);

        }
    }


    public interface OnListItemClickListener {
        void onItemClick(Movie movie);
    }
}
