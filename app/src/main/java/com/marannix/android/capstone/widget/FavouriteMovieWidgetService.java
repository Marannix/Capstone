package com.marannix.android.capstone.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.marannix.android.capstone.data.model.Movie;
import java.util.ArrayList;
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
      return movies != null ? movies.size() : 0;
    }

    @Override public RemoteViews getViewAt(int position) {
      return null;
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

    }
  }
}
