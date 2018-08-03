package com.marannix.android.capstone.data;

import android.content.Context;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.marannix.android.capstone.data.model.Movie;
import java.util.List;

public class SharedPreference {

  private final static String MOVIE_KEY = "MOVIE";

  public static Movie[] getSharedPreferences(Context context) {
    Gson gson = new Gson();
    String json = PreferenceManager.getDefaultSharedPreferences(context).getString(MOVIE_KEY, "");
    return gson.fromJson(json, Movie[].class);
  }

  public static void setSharedPreferences(Context context, List<Movie> movie) {
    Gson gson = new Gson();
    String json = gson.toJson(movie);
    PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
        .putString(MOVIE_KEY, json)
        .apply();
  }
}
