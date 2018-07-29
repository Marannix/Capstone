package com.marannix.android.capstone.repository;

import com.marannix.android.capstone.api.ApiModule;
import com.marannix.android.capstone.response.TrailerResponse;
import rx.Observable;

public class TrailerRepository {

  private ApiModule apiModule;

  public void initApiModule() {
    apiModule = new ApiModule();
  }

  public Observable<TrailerResponse> fetchMovieTrailers(int id) {
    return apiModule.movieApi().getMovieTrailers(id);
  }

}
