package com.marannix.android.capstone.api;

import com.marannix.android.capstone.response.GenresResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GenresApi {

  String apiKey = "";

  String authentication = "api_key=" + apiKey;

  @GET("/genre/movie/list?" + authentication) Call<GenresResponse> getGenres();
}
