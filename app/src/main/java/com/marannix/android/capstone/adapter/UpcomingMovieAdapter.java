package com.marannix.android.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.activity.HomeActivity;
import com.marannix.android.capstone.activity.MovieActivity;
import com.marannix.android.capstone.data.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.ViewHolder> {

  private static final String MOVIE_URL = "https://image.tmdb.org/t/p/";
  private static final String POSTER_SIZE = "w500";
  private Context context;
  private List<Movie> upcomingMovies;
  private HomeActivity activity;

  public UpcomingMovieAdapter(HomeActivity activity) {
    this.activity = activity;
  }

  public void setListData(Context context, List<Movie> upcomingMovies) {
    this.context = context;
    this.upcomingMovies = upcomingMovies;
    this.notifyDataSetChanged();
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.upcoming_movies_items, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Movie movie = upcomingMovies.get(position);
    final String path = MOVIE_URL + POSTER_SIZE + movie.getPosterPath();
    Log.d("joshua1", "onBindViewHolder: " + movie.getId());
    Picasso.get().load(path).into(holder.image);
    holder.title.setText(movie.getTitle());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Movie data =
            new Movie(movie.getId(), movie.getTitle(), movie.getOverview(), movie.getPosterPath(),
                movie.getBackdropPath(), movie.getVoteCount(), movie.getVoteAverage(),
                movie.getReleaseDate());
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra("myDataKey", data);
        activity.tracking(movie.getTitle());
        context.startActivity(intent);
      }
    });
  }

  @Override public int getItemCount() {
    return upcomingMovies != null ? upcomingMovies.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.upcoming_movies_poster) ImageView image;
    @BindView(R.id.upcoming_movies_title) TextView title;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
