package com.marannix.android.capstone.data.model;

import com.google.gson.annotations.SerializedName;

public class Poster {

  @SerializedName("file_path") private String path;

  public Poster(String path) {
    this.path = path;
  }

  public String getPosterPath() {
    return path;
  }
}
