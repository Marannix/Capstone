package com.marannix.android.capstone.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.adapter.ArtworkAdapter;
import com.marannix.android.capstone.data.model.Poster;
import java.util.List;

public class MovieArtworkView {

  private ViewGroup parent;
  private ArtworkAdapter artworkAdapter;

  @BindView(R.id.artwork_recycler_view) RecyclerView recyclerView;

  public MovieArtworkView(ViewGroup parent) {
    ButterKnife.bind(this, parent);
    this.parent = parent;
    initVideoAdapter();
  }

  public void setArtwork(List<Poster> posters, Context context, String title) {
    artworkAdapter.setArtworks(posters, context, title);
  }

  private void initVideoAdapter() {
    artworkAdapter = new ArtworkAdapter();
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager =
        new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(artworkAdapter);
  }
}
