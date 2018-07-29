package com.marannix.android.capstone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Movie;
import com.marannix.android.capstone.presenter.MoviePresenter;

public class MovieActivity extends BaseActivity {

  //TODO: https://www.themoviedb.org/talk/53c11d4ec3a3684cf4006400

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.movie_full_layout);
    Movie movie = getIntent().getParcelableExtra("myDataKey");

    MoviePresenter moviePresenter = new MoviePresenter(getApplicationContext(), movie);
    moviePresenter.present(getViewGroup());

  }
}
