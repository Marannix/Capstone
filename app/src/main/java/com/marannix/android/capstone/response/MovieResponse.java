package com.marannix.android.capstone.response;

import com.marannix.android.capstone.data.model.Movie;
import java.util.List;

public class MovieResponse {

  public List<Movie> results;

  public List<Movie> getMovies() {
    return results;
  }
}

