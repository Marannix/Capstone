package com.marannix.android.capstone.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieHeaderView {

  @BindView(R.id.movie_header_poster) ImageView poster;
  @BindView(R.id.movie_header_title) TextView title;

  private String movieUrl = "https://image.tmdb.org/t/p/";
  private String phoneSize = "w342";

  public MovieHeaderView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
  }

  public void setContent(Movie movie) {
    final String path = movieUrl + phoneSize + movie.getPosterPath();
    title.setText(movie.getTitle());
    Picasso.get().load(path).into(poster);
  }
}
