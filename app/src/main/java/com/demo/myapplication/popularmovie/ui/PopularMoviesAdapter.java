package com.demo.myapplication.popularmovie.ui;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.demo.myapplication.MoviesApplication;
import com.demo.myapplication.R;
import com.demo.myapplication.network.response.Movies;

import javax.inject.Inject;

public class PopularMoviesAdapter extends PagedListAdapter<Movies, PopularMoviesAdapter.MoviesViewHolder> {

    @Inject
    RequestManager glide;

    public static final String POSTER_URL = "https://image.tmdb.org/t/p/w342";


    protected PopularMoviesAdapter() {
        super(diffCallback);
        MoviesApplication.getAppComponent().inject(this);
    }

    private static DiffUtil.ItemCallback<Movies> diffCallback = new DiffUtil.ItemCallback<Movies>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    };

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_view, parent, false);
        return new MoviesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movies movie = getItem(position);
        if (movie != null) {
            String posterPath = POSTER_URL + movie.getPosterPath();
            glide.load(Uri.parse(posterPath))
                    .fitCenter()
                    .into(holder.movieImage);
            holder.movieName.setText(movie.getTitle());
            holder.movieReleaseYear.setText(movie.getTitle());
        }
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImage;
        private TextView movieName;
        private TextView movieReleaseYear;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieName = itemView.findViewById(R.id.movie_title);
            movieReleaseYear = itemView.findViewById(R.id.movie_year);
        }
    }
}
