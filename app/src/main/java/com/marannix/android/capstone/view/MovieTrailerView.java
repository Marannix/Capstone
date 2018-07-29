package com.marannix.android.capstone.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.adapter.TrailerAdapter;
import com.marannix.android.capstone.data.model.Trailers;
import java.util.List;

public class MovieTrailerView {

  private ViewGroup parent;
  private TrailerAdapter trailerAdapter;

  @BindView(R.id.trailer_recycler_view) RecyclerView recyclerView;

  public MovieTrailerView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
    this.parent = parent;
    initVideoAdapter();
  }

  public void setVideos(List<Trailers> videos, Context context) {
    trailerAdapter.setTrailer(videos, context);
  }

  private void initVideoAdapter() {
    trailerAdapter = new TrailerAdapter();
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager =
        new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(trailerAdapter);
  }
}
