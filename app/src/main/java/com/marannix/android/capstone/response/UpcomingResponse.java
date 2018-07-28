package com.marannix.android.capstone.response;

import com.marannix.android.capstone.data.model.UpcomingMovies;
import java.util.List;

public class UpcomingResponse {

  public List<UpcomingMovies> results;

  public List<UpcomingMovies> getUpcomingMovies() {
    return results;
  }
}
