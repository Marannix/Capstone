package com.marannix.android.capstone.repository;

import com.marannix.android.capstone.api.ApiModule;
import com.marannix.android.capstone.response.UpcomingResponse;
import rx.Observable;

public class MovieRepository {

  private ApiModule apiModule;

  public void initApiModule() {
    apiModule = new ApiModule();
  }

  public Observable<UpcomingResponse> fetchUpcomingMovies() {
    return apiModule.movieApi().getUpcomingResponse();
  }
}
