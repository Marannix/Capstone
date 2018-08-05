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

  private final static int WIDGET_MIN_WIDTH = 150;
  private final static int WIDGET_MIN_HEIGHT = 300;

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    WidgetUpdateService.startActionUpdateAppWidgets(context, true);
  }

  @Override
  public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
      int appWidgetId, Bundle newOptions) {
    Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
    int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
    if (width < WIDGET_MIN_WIDTH) {
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
    if (width < WIDGET_MIN_WIDTH) {
      remoteView = getViewForSmallerWidget(context);
    } else {
      remoteView = getViewForBiggerWidget(context, options);
    }
    appWidgetManager.updateAppWidget(appWidgetId, remoteView);
  }

  private static RemoteViews getViewForBiggerWidget(Context context, Bundle options) {
    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favourite_movie_widget);

    int minHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
    if (minHeight < WIDGET_MIN_HEIGHT) {
      views.setViewVisibility(R.id.favourite_item_widget_text, View.GONE);
    } else {
      views.setViewVisibility(R.id.favourite_item_widget_text, View.VISIBLE);

      Intent intent = new Intent(context, HomeActivity.class);
      PendingIntent pendingIntent =
          PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

      views.setOnClickPendingIntent(R.id.favourite_item_widget_text, pendingIntent);
    }

    Intent intent = new Intent(context, FavouriteMovieWidgetService.class);
    views.setRemoteAdapter(R.id.widget_list_view, intent);

    Intent appIntent = new Intent(context, MovieActivity.class);
    PendingIntent appPendingIntent =
        PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    views.setPendingIntentTemplate(R.id.widget_list_view, appPendingIntent);
    return views;
  }

  private static RemoteViews getViewForSmallerWidget(Context context) {

    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_small_layout);

    Intent homeIntent = new Intent(context, HomeActivity.class);
    PendingIntent homePendingIntent = PendingIntent.getActivity(context, 0, homeIntent, 0);
    views.setOnClickPendingIntent(R.id.widget_image_view, homePendingIntent);

    Intent movieIntent = new Intent(context, MovieActivity.class);
    PendingIntent moviePendingIntent = PendingIntent.getActivity(context, 0, movieIntent, 0);
    views.setOnClickPendingIntent(R.id.widget_click_text_view, moviePendingIntent);

    return views;
  }
}

