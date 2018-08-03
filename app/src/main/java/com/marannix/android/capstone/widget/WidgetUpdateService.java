package com.marannix.android.capstone.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.marannix.android.capstone.R;

public class WidgetUpdateService extends IntentService {

  private static final String ACTION_UPDATE_APP_WIDGETS =
      "com.marannix.android.capstone.widgetupdateservice.update_app_widget";
  private static final String ACTION_UPDATE_LIST_VIEW =
      "com.marannix.android.capstone.widgetupdateservice.update_app_widget_list";

  public WidgetUpdateService() {
    super("WidgetUpdateService");
  }

  @Override protected void onHandleIntent(@Nullable Intent intent) {
    if (intent != null) {
      final String action = intent.getAction();
      if (ACTION_UPDATE_APP_WIDGETS.equals(action)) {
        handleActionUpdateAppWidgets();
      } else if (ACTION_UPDATE_LIST_VIEW.equals(action)) {
        handleActionUpdateListView();
      }
    }
  }

  private void handleActionUpdateAppWidgets() {
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
        new ComponentName(this, FavouriteMovieWidgetProvider.class));

    FavouriteMovieWidgetProvider.updateAllAppWidget(this, appWidgetManager, appWidgetIds);
  }

  public static void startActionUpdateAppWidgets(Context context, boolean isListView) {
    Intent intent = new Intent(context, WidgetUpdateService.class);
    if (isListView) {
      intent.setAction(ACTION_UPDATE_LIST_VIEW);
    } else {
      intent.setAction(ACTION_UPDATE_APP_WIDGETS);
    }
    context.startService(intent);
  }

  private void handleActionUpdateListView() {

    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
        new ComponentName(this, FavouriteMovieWidgetProvider.class));

    FavouriteMovieWidgetProvider.updateAllAppWidget(this, appWidgetManager, appWidgetIds);

    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_view);
  }
}
