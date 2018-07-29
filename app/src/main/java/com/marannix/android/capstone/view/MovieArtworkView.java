package com.marannix.android.capstone.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.squareup.picasso.Picasso;

public class MovieArtworkView {

  @BindView(R.id.movie_artwork_poster) ImageView posterImage;

  private String movieUrl = "https://image.tmdb.org/t/p/";
  private String POSTER_SIZE = "w342";

  public MovieArtworkView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
  }

  public void setArtworks(String poster) {
    final String posterPath = movieUrl + POSTER_SIZE + poster;
    Picasso.get().load(posterPath).into(posterImage);
  }

}
