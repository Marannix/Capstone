package com.marannix.android.capstone.api;

import com.marannix.android.capstone.BuildConfig;
import com.marannix.android.capstone.response.CreditsResponse;
import com.marannix.android.capstone.response.ImageResponse;
import com.marannix.android.capstone.response.MovieResponse;
import com.marannix.android.capstone.response.ReviewResponse;
import com.marannix.android.capstone.response.TrailerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MovieApi {

  String authentication = "api_key=" + BuildConfig.API_KEY;

  @GET("movie/popular?" + authentication) Call<MovieResponse> getPopularMovies();

  @GET("movie/top_rated?" + authentication) Call<MovieResponse> getTopRatedMovies();

  @GET("movie/upcoming?" + authentication) Observable<MovieResponse> getUpcomingResponse();

  @GET("movie/now_playing?" + authentication) Observable<MovieResponse> getNowPlayingResponse();

  @GET("movie/{id}/videos?" + authentication) Observable<TrailerResponse> getMovieTrailers(
      @Path("id") int id);

  @GET("movie/{id}/reviews?" + authentication) Call<ReviewResponse> getMovieReviews(
      @Path("id") int id);

  @GET("movie/{id}/credits?" + authentication) Call<CreditsResponse> getCreditResponse(
      @Path("id") int id);

  @GET("movie/{id}/images?" + authentication) Observable<ImageResponse> getImagesResponse(
      @Path("id") int id);
}
