package com.marannix.android.capstone.repository;

import com.marannix.android.capstone.api.ApiModule;
import com.marannix.android.capstone.response.ImageResponse;
import rx.Observable;

public class PosterRepository {

  private ApiModule apiModule;

  public void initApiModule() {
    apiModule = new ApiModule();
  }

  public Observable<ImageResponse> fetchMovieImages(int id) {
    return apiModule.movieApi().getImagesResponse(id);
  }

}
