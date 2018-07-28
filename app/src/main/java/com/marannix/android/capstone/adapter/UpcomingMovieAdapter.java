package com.marannix.android.capstone.adapter;

import android.content.Context;
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
import com.marannix.android.capstone.data.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.ViewHolder> {

  private Context context;
  private List<Movie> upcomingMovies;
  private String movieUrl = "https://image.tmdb.org/t/p/";
  private String phoneSize = "w500";

  public UpcomingMovieAdapter(Context context, List<Movie> upcomingMovies) {
    this.context = context;
    this.upcomingMovies = upcomingMovies;
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.upcoming_movies_items, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Movie upcomingMovie = upcomingMovies.get(position);
    final String path = movieUrl + phoneSize + upcomingMovie.getPosterPath();
    // TODO add picasso

    Picasso.get().load(path).into(holder.image);
    holder.title.setText(upcomingMovie.getTitle());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // TODO: Take to movie activity
        Log.d("Joshua1", "onClick: I've been clicked");
      }
    });
  }

  @Override public int getItemCount() {
    return upcomingMovies != null ? upcomingMovies.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView2) ImageView image;
    @BindView(R.id.textView) TextView title;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
