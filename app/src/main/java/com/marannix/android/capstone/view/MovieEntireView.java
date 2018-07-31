package com.marannix.android.capstone.view;

import android.support.design.widget.FloatingActionButton;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;

public class MovieEntireView {

  @BindView(R.id.movieFAB) FloatingActionButton fab;

  public MovieEntireView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
  }

  public FloatingActionButton getFab() {
    return fab;
  }
}
