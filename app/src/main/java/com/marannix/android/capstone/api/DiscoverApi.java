package com.marannix.android.capstone.api;

import com.marannix.android.capstone.response.DiscoverResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscoverApi {
  //https://developers.themoviedb.org/3/discover/movie-discover
  //https://stackoverflow.com/questions/36730086/retrofit-2-url-query-parameter
  //https://developers.themoviedb.org/3/discover/movie-discover

  String apiKey = "";

  String authentication = "api_key=" + apiKey;

  // genre id = 10752 (string)
  //TODO: I need to either query or pass the horror id to the discover
  //TODO: Rename to get genreMovies, as I want to get a movie or a specific genre
  // language = en-US
  // sort_by = popularity.desc
  // include_adult = false
  // include_video = false
  // with_genres = example 10752 (horror)
  @GET("/discover/movie?" + authentication) Call<DiscoverResponse> getSpecificGenre(
      @Query("language") String language, @Query("sort_by") String sortBy,
      @Query("include_adult") boolean includeAdult, @Query("include_video") boolean includeVideo,
      @Query("with_genres") String withGenre);
}
