package com.marannix.android.capstone.view;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieEntireView {

  @BindView(R.id.movieFAB) FloatingActionButton fab;
  @BindView(R.id.movie_artwork_backdrop) ImageView backdrop;
  @BindView(R.id.movie_collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;

  private static final String MOVIE_URL = "https://image.tmdb.org/t/p/";
  private static final String BACKDROP_SIZE = "original";

  public MovieEntireView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
  }

  public void loadBackdrop(Movie movie) {
    final String path = MOVIE_URL + BACKDROP_SIZE + movie.getPosterPath();
    Picasso.get().load(path).into(backdrop);
  }

  public void setCollapsingToolbarLayout() {
    collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
  }

  public FloatingActionButton getFab() {
    return fab;
  }

}
