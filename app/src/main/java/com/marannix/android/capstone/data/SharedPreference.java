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
    PreferenceManager.getDefaultSharedPreferences(context).edit().putString(MOVIE_KEY, json).apply();

    //Intent widgetIntent = new Intent(context, FavouriteMovieWidgetProvider.class);
    //widgetIntent.setAction("com.example.android.capstone.action.update.widget");
    //int[] ids = AppWidgetManager.getInstance(context)
    //    .getAppWidgetIds(new ComponentName(context, FavouriteMovieWidgetProvider.class));
    //widgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
    //context.sendBroadcast(widgetIntent);
  }

  //public static void setSharedPreferences(Context context, List<Movie> movies) {
  //  SharedPreferences preferences =
  //      context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
  //  preferences.edit().putString(MOVIE_KEY, movies.toString()).apply();
  //}
  //
  ////TODO : Simplify this
  //public static ArrayList<Movie> getSharedPreferences(Context context) {
  //  ArrayList<Movie> list = new ArrayList<>();
  //  SharedPreferences preferences =
  //      context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE);
  //  String json = preferences.getString(MOVIE_KEY, "[]");
  //
  //  if (list.isEmpty()) {
  //    try {
  //      JSONArray jsonArray = new JSONArray(json);
  //      for (int i = 0; i < jsonArray.length(); i++) {
  //        JSONObject object = jsonArray.getJSONObject(i);
  //
  //        Movie movie = new Movie();
  //        movie.setTitle(object.getString("title"));
  //        list.add(movie);
  //      }
  //    } catch (JSONException e) {
  //      e.printStackTrace();
  //    }
  //  }
  //
  //  return list;
  //}

}
