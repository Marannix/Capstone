package com.marannix.android.capstone.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.SharedPreference;
import com.marannix.android.capstone.data.model.Movie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavouriteMovieWidgetService extends RemoteViewsService {

  @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
    return new MyRemoteViewsFactory(getApplicationContext());
  }

  private class MyRemoteViewsFactory implements RemoteViewsFactory {

    private List<Movie> moviesList;
    private Context context;
    private ArrayList<String> movies;

    public MyRemoteViewsFactory(Context context) {
      this.context = context;
      movies = new ArrayList<>();
    }

    @Override public void onCreate() {
      loadFavouriteMovies();
    }

    @Override public void onDataSetChanged() {
      loadFavouriteMovies();
    }

    @Override public void onDestroy() {
      if (movies != null) {
        movies.clear();
      }
    }

    @Override public int getCount() {
      return moviesList != null ? moviesList.size() : 0;
    }

    @Override public RemoteViews getViewAt(int position) {
      RemoteViews remoteViews =
          new RemoteViews(getPackageName(), R.layout.favourite_movie_list_item_widget);
      remoteViews.setTextViewText(R.id.favourite_item_widget_text, movies.get(position));
      return remoteViews;
    }

    @Override public RemoteViews getLoadingView() {
      return null;
    }

    @Override public int getViewTypeCount() {
      return 1;
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public boolean hasStableIds() {
      return false;
    }

    private void loadFavouriteMovies() {
      if (SharedPreference.getSharedPreferences(context) != null) {
        moviesList = Arrays.asList(SharedPreference.getSharedPreferences(context));
        for (Movie i : moviesList) {
          String title = i.getTitle();
          movies.add(title);
        }
      } else {
        movies.add("No ingredients");
      }
    }
  }
}
