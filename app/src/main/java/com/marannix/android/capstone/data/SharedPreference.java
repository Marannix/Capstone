package com.marannix.android.capstone.data;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.marannix.android.capstone.data.model.Movie;
import com.marannix.android.capstone.widget.FavouriteMovieWidgetProvider;
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

    Intent intent = new Intent(context, FavouriteMovieWidgetProvider.class);
    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
    int[] ids = AppWidgetManager.getInstance(context)
        .getAppWidgetIds(new ComponentName(context, FavouriteMovieWidgetProvider.class));
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
    context.sendBroadcast(intent);
  }
}
