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

public class FavouriteMovieWidgetService extends RemoteViewsService {

  @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
    return new MyRemoteViewsFactory(getApplicationContext(),
        SharedPreference.getSharedPreferences(getApplicationContext()));
  }

  private class MyRemoteViewsFactory implements RemoteViewsFactory {

    private ArrayList<Movie> moviesList;
    private Context context;

    public MyRemoteViewsFactory(Context context, Movie[] sharedPreferences) {
      this.context = context;
      this.moviesList = new ArrayList<>(Arrays.asList(sharedPreferences));
    }

    @Override public void onCreate() {

    }

    @Override public void onDataSetChanged() {

    }

    @Override public void onDestroy() {

    }

    @Override public int getCount() {
      return moviesList.size();
    }

    @Override public RemoteViews getViewAt(int position) {
      RemoteViews remoteViews =
          new RemoteViews(context.getPackageName(), R.layout.favourite_movie_list_item_widget);
      remoteViews.setTextViewText(R.id.favourite_item_widget_text, moviesList.get(position).getTitle());

      //Intent fillInIntent = new Intent();
      //fillInIntent.putExtra("ItemId", moviesList.get(position).getId());
      //remoteViews.setOnClickFillInIntent(R.id.widget_layout, fillInIntent);

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
      return true;
    }

    //private void loadFavouriteMovies() {
    //  if (SharedPreference.getSharedPreferences(context) != null) {
    //    moviesList = Arrays.asList(SharedPreference.getSharedPreferences(context));
    //    for (Movie i : moviesList) {
    //      String title = i.getTitle();
    //      movies.add(title);
    //    }
    //  } else {
    //    movies.add("No ingredients");
    //  }
    //}
  }
}
