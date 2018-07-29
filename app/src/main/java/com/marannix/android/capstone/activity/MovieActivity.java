package com.marannix.android.capstone.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Movie;
import com.marannix.android.capstone.presenter.MoviePresenter;
import com.squareup.picasso.Picasso;

public class MovieActivity extends BaseActivity {

  @BindView(R.id.movie_artwork_backdrop) ImageView backdrop;
  @BindView(R.id.movie_collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
  @BindView(R.id.movie_toolbar) Toolbar toolbar;
  private static final String MOVIE_URL = "https://image.tmdb.org/t/p/";
  private static final String BACKDROP_SIZE = "original";

  //TODO: https://www.themoviedb.org/talk/53c11d4ec3a3684cf4006400

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.movie_full_layout);
    ButterKnife.bind(this, getViewGroup());
    Movie movie = getIntent().getParcelableExtra("myDataKey");
    MoviePresenter moviePresenter = new MoviePresenter(getApplicationContext(), movie);
    setupToolbar(movie);
    moviePresenter.present(getViewGroup());
  }

  private void loadBackdrop(Movie movie) {
    final String path = MOVIE_URL + BACKDROP_SIZE + movie.getPosterPath();
    Picasso.get().load(path).into(backdrop);
  }

  private void setupToolbar(Movie movie) {
    loadBackdrop(movie);
    toolbar.setTitle(movie.getTitle());
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
