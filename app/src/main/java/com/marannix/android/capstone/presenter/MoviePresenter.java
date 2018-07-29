package com.marannix.android.capstone.presenter;

import android.content.Context;
import android.view.ViewGroup;
import com.marannix.android.capstone.data.model.Movie;
import com.marannix.android.capstone.view.MovieArtworkView;
import com.marannix.android.capstone.view.MovieDescriptionView;
import com.marannix.android.capstone.view.MovieHeaderView;

public class MoviePresenter {

  private Context context;
  private Movie movie;
  private MovieHeaderView movieHeaderView;
  private MovieDescriptionView movieDescriptionView;
  private MovieArtworkView movieArtworkView;

  public MoviePresenter(Context context, Movie movie) {
    this.context = context;
    this.movie = movie;
  }

  public void present(ViewGroup viewGroup) {
    movieHeaderView = new MovieHeaderView(viewGroup);
    movieDescriptionView = new MovieDescriptionView(viewGroup);
    movieArtworkView = new MovieArtworkView(viewGroup);
    movieHeaderView.setContent(movie);
    movieDescriptionView.setContent(movie.getOverview());
    movieArtworkView.setArtworks(movie.getPosterPath());

  }
}
