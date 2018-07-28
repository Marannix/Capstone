package com.marannix.android.capstone.api;

import com.marannix.android.capstone.response.CreditsResponse;
import com.marannix.android.capstone.response.MovieResponse;
import com.marannix.android.capstone.response.NowPlayingResponse;
import com.marannix.android.capstone.response.ReviewResponse;
import com.marannix.android.capstone.response.VideoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MovieApi {

  //TODO: Refactor id to movie_id

  String apiKey = "";

  String authentication = "api_key=" + apiKey;

  @GET("movie/popular?" + authentication) Call<MovieResponse> getPopularMovies();

  @GET("movie/top_rated?" + authentication) Call<MovieResponse> getTopRatedMovies();

  @GET("movie/{id}/videos?" + authentication) Call<VideoResponse> getMovieVideos(
      @Path("id") int id);

  //TODO CHANGE TO MOVIERESPONSE
  @GET("movie/upcoming?" + authentication) Observable<MovieResponse> getUpcomingResponse();


  @GET("movie/{id}/reviews?" + authentication) Call<ReviewResponse> getMovieReviews(
      @Path("id") int id);

  //TODO CHANGE TO MOVIERESPONSE
  @GET("movie/now_playing?" + authentication) Observable<NowPlayingResponse> getNowPlayingResponse();

  @GET("movie/{id}/credits?" + authentication) Call<CreditsResponse> getCreditResponse();

}
