package com.marannix.android.capstone.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.activity.HomeActivity;
import com.marannix.android.capstone.activity.MovieActivity;

/**
 * Implementation of App Widget functionality.
 */
public class FavouriteMovieWidgetProvider extends AppWidgetProvider {

  //TODO: https://medium.com/@puruchauhan/android-widget-for-starters-5db14f23009b

  //static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
  //
  //  RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favourite_movie_widget);
  //
  //  if (SharedPreference.getSharedPreferences(context) != null) {
  //    views.setTextViewText(R.id.favourite_item_widget_title,
  //        "These are your Favourite Movies");
  //  } else {
  //    views.setTextViewText(R.id.favourite_item_widget_title,
  //        "You have no Favourite Movies");
  //  }
  //
  //  Intent intent = new Intent(context, FavouriteMovieWidgetService.class);
  //
  //  //TODO: I need to create a Favourite word activity.
  //  Intent activity = new Intent(context, HomeActivity.class);
  //
  //  views.setRemoteAdapter(R.id.widgetListView, intent);
  //
  //  PendingIntent pendingIntent =
  //      PendingIntent.getActivity(context, 0, activity, PendingIntent.FLAG_UPDATE_CURRENT);
  //  views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
  //
  //  appWidgetManager.updateAppWidget(appWidgetId, views);
  //}

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    //for (int appWidgetId : appWidgetIds) {
    //  updateAppWidget(context, appWidgetManager, appWidgetId);
    //}
    WidgetUpdateService.startActionUpdateAppWidgets(context, false);
  }

  //@Override public void onReceive(Context context, Intent intent) {
  //  super.onReceive(context, intent);

  //String action = intent.getAction();
  //if (action.equals("com.example.android.capstone.action.update.widget")) {
  //  AppWidgetManager appWidgetManager =
  //      AppWidgetManager.getInstance(context.getApplicationContext());
  //  ComponentName widget = new ComponentName(context.getApplicationContext(),
  //      FavouriteMovieWidgetProvider.class.getName());
  //  int[] appWidgetIds = appWidgetManager.getAppWidgetIds(widget);
  //  appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,
  //      R.id.favourite_item_widget_title);
  //  onUpdate(context, appWidgetManager, appWidgetIds);
  //}
  // }

  @Override
  public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
      int appWidgetId, Bundle newOptions) {
    Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
    int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
    if (width < 150) {
      WidgetUpdateService.startActionUpdateAppWidgets(context, false);
    } else {
      WidgetUpdateService.startActionUpdateAppWidgets(context, true);
    }
    super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
  }

  public static void updateAllAppWidget(Context context, AppWidgetManager appWidgetManager,
      int[] appWidgetIds) {
    for (int appWidgetId : appWidgetIds) {
      updateAppWidget(context, appWidgetManager, appWidgetId);
    }
  }

  static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

    Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
    int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
    RemoteViews remoteView;
    if (width < 150) {
      remoteView = getViewForSmallerWidget(context);
    } else {
      remoteView = getViewForBiggerWidget(context, options);
    }
    appWidgetManager.updateAppWidget(appWidgetId, remoteView);
  }

  private static RemoteViews getViewForBiggerWidget(Context context, Bundle options) {
    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favourite_movie_widget);

    int minHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
    if (minHeight < 100) {
      views.setViewVisibility(R.id.favourite_item_widget_text, View.GONE);
    } else {
      views.setViewVisibility(R.id.favourite_item_widget_text, View.VISIBLE);

      Intent intent = new Intent(context, HomeActivity.class);
      PendingIntent pendingIntent =
          PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

      views.setOnClickPendingIntent(R.id.favourite_item_widget_text, pendingIntent);
    }

    Intent intent = new Intent(context, FavouriteMovieWidgetService.class);
    views.setRemoteAdapter(R.id.widgetListView, intent);

    Intent appIntent = new Intent(context, MovieActivity.class);
    PendingIntent appPendingIntent =
        PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    views.setPendingIntentTemplate(R.id.widgetListView, appPendingIntent);
    // TODO: Favourite Activity
    // TODO: Or take them to detail activity

    return views;
  }

  private static RemoteViews getViewForSmallerWidget(Context context) {

    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget_simple);

    Intent intent1 = new Intent(context, HomeActivity.class);
    PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, intent1, 0);
    views.setOnClickPendingIntent(R.id.widgetImageView, pendingIntent1);

    // TODO: Favourite Activity
    // TODO: Or take them to detail activity
    Intent intent2 = new Intent(context, MovieActivity.class);
    PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
    views.setOnClickPendingIntent(R.id.clickTextView, pendingIntent2);

    return views;
  }
}

