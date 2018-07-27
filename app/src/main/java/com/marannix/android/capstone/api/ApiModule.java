package com.marannix.android.capstone.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiModule {

  String BASE_URL = "https://api.themoviedb.org/3/";

  Retrofit provideRetrofit() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    return retrofit;
  }

  MovieApi provideMovieApi(Retrofit retrofit) {
    return retrofit.create(MovieApi.class);
  }

  GenresApi provideGenresApi(Retrofit retrofit) {
    return retrofit.create(GenresApi.class);
  }

  DiscoverApi provideDiscover(Retrofit retrofit) {
    return retrofit.create(DiscoverApi.class);
  }

  public MovieApi movieApi() {
    return provideMovieApi(provideRetrofit());
  }

  public GenresApi genresApi() {
    return provideGenresApi(provideRetrofit());
  }

  public DiscoverApi discoverApi() {
    return provideDiscover(provideRetrofit());
  }
}
