package com.marannix.android.capstone.utils;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

public class CreateApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();
    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
  }
}
