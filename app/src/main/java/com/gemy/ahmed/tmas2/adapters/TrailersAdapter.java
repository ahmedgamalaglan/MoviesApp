package com.gemy.ahmed.tmas2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gemy.ahmed.tmas2.R;
import com.gemy.ahmed.tmas2.entities.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.gemy.ahmed.tmas2.utilities.Consts.VIDEO_IMAGE_PREFIX;
import static com.gemy.ahmed.tmas2.utilities.Consts.VIDEO_IMAGE_SUFFIX;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {

    private List<Trailer.Trailers> trailersList = new ArrayList<>();


    private OnTrailerClickListener onTrailerClickListener;

    public TrailersAdapter(OnTrailerClickListener clickListener) {
        onTrailerClickListener = clickListener;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailers_item, parent, false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Picasso.get().load(VIDEO_IMAGE_PREFIX +
                trailersList.get(position).getKey() +
                VIDEO_IMAGE_SUFFIX)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (trailersList == null)
            return 0;
        return trailersList.size();
    }


    public void setTrailersList(List<Trailer.Trailers> trailersList) {
        this.trailersList = trailersList;
        notifyDataSetChanged();
    }

    class TrailersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        private TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_movie_trailer_video_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Trailer.Trailers trailers = trailersList.get(getAdapterPosition());
            onTrailerClickListener.onVideoClick(trailers.getKey());

        }
    }


    public interface OnTrailerClickListener {
        void onVideoClick(String trailerKey);
    }

}
