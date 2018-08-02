package com.marannix.android.capstone.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class WidgetUpdateService extends IntentService {

  private static final String ACTION_UPDATE_LIST_VIEW = "";
  private static final String ACTION_UPDATE_APP_WIDGETS = "";

  public WidgetUpdateService() {
    super("WidgetUpdateService");
  }

  @Override protected void onHandleIntent(@Nullable Intent intent) {

  }

  //public static void startActionUpdateAppWidgets(Context context, boolean forListView) {
  //  Intent intent = new Intent(context, WidgetUpdateService.class);
  //  if(forListView){
  //    intent.setAction(ACTION_UPDATE_LIST_VIEW);
  //  }else {
  //    intent.setAction(ACTION_UPDATE_APP_WIDGETS);
  //  }
  //  context.startService(intent);
  //}

  public static void startActionUpdateAppWidgets(Context context) {
    Intent intent = new Intent(context, WidgetUpdateService.class);
    intent.setAction(ACTION_UPDATE_APP_WIDGETS);
    context.startService(intent);
  }

  private void handleActionUpdateAppWidgets() {

    //You can do any task regarding this process you want to do here, then update the widget.

    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, FavouriteMovieWidgetProvider.class));

    FavouriteMovieWidgetProvider.updateAllAppWidget(this, appWidgetManager,appWidgetIds);
  }
}
