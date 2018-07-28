package com.marannix.android.capstone.repository;

import com.marannix.android.capstone.api.ApiModule;
import com.marannix.android.capstone.response.MovieResponse;
import com.marannix.android.capstone.response.NowPlayingResponse;
import rx.Observable;

public class MovieRepository {

  private ApiModule apiModule;

  public void initApiModule() {
    apiModule = new ApiModule();
  }

  public Observable<MovieResponse> fetchUpcomingMovies() {
    return apiModule.movieApi().getUpcomingResponse();
  }

  public Observable<NowPlayingResponse> fetchNowPlayingMovies() {
    return apiModule.movieApi().getNowPlayingResponse();
  }
}
