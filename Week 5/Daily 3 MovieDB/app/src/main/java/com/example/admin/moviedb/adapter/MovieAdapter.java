package com.example.admin.moviedb.adapter;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.moviedb.R;
import com.example.admin.moviedb.activity.DetailActivity;
import com.example.admin.moviedb.model.Movie;
import com.example.admin.moviedb.model.QueryResult;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    // OnScrollListener will listen for when the scroll reaches a certain position. You can then
    // call to the api to get more data. The example on manny's github for recyclerview should have it

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        final Movie movie = movieList.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO This should call presenter.navigateToDetails

                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("title", movie.getTitle());
                intent.putExtra("release_date", movie.getReleaseDate());
                intent.putExtra("popularity", movie.getPopularity());
                intent.putExtra("vote_average", movie.getVoteAverage());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public MovieViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }
}
