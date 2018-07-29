package com.marannix.android.capstone.view;

import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;

public class MovieDescriptionView {

  @BindView(R.id.movie_description) TextView description;

  public MovieDescriptionView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
  }

  public void setContent(String overview) {
    description.setText(overview);
  }
}
