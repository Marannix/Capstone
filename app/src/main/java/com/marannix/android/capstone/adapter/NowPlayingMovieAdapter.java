package com.marannix.android.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.activity.MovieActivity;
import com.marannix.android.capstone.data.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NowPlayingMovieAdapter
    extends RecyclerView.Adapter<NowPlayingMovieAdapter.ViewHolder> {

  private Context context;
  private List<Movie> nowPlayingMovies;
  private String movieUrl = "https://image.tmdb.org/t/p/";
  private String phoneSize = "w500";

  public void setListData(Context context, List<Movie> nowPlayingMovies) {
    this.context = context;
    this.nowPlayingMovies = nowPlayingMovies;
    this.notifyDataSetChanged();
  }

  @NonNull @Override
  public NowPlayingMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    return new NowPlayingMovieAdapter.ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.now_playing_movies_items, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull NowPlayingMovieAdapter.ViewHolder holder, int position) {
    final Movie movie = nowPlayingMovies.get(position);
    final String path = movieUrl + phoneSize + movie.getPosterPath();

    Picasso.get().load(path).into(holder.image);
    holder.title.setText(movie.getTitle());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // TODO: Take to movie activity
        Movie data =
            new Movie(movie.getId(), movie.getTitle(), movie.getOverview(), movie.getPosterPath(),
                movie.getBackdropPath(), movie.getVoteCount(), movie.getVoteAverage(),
                movie.getReleaseDate());
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra("myDataKey", data);
        context.startActivity(intent);
      }
    });
  }

  @Override public int getItemCount() {
    return nowPlayingMovies != null ? nowPlayingMovies.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.now_playing_image) ImageView image;
    @BindView(R.id.now_playing_title) TextView title;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
