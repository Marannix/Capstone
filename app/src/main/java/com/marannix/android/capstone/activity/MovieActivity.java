package com.marannix.android.capstone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Movie;
import com.marannix.android.capstone.presenter.MoviePresenter;

public class MovieActivity extends BaseActivity {

  @BindView(R.id.movie_toolbar) Toolbar toolbar;

  private Movie movie;
  //TODO: https://www.themoviedb.org/talk/53c11d4ec3a3684cf4006400

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.movie_full_layout);
    ButterKnife.bind(this, getViewGroup());

    if (getIntent() != null && getIntent().getExtras() != null) {
      if (getIntent().getExtras().containsKey("widgetMovie")) {
        movie = getIntent().getParcelableExtra("widgetMovie");
      } else {
        movie = getIntent().getParcelableExtra("myDataKey");
      }
    }

    MoviePresenter moviePresenter = new MoviePresenter(getApplicationContext(), movie);
    setupToolbar(movie);
    moviePresenter.present(getViewGroup());
  }

  private void setupToolbar(Movie movie) {
    toolbar.setTitle(movie.getTitle());
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onStop() {
    super.onStop();
  }
}
