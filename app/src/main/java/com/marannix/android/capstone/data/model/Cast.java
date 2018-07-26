package com.marannix.android.capstone.data.model;

import com.google.gson.annotations.SerializedName;

public class Cast {

  @SerializedName("cast_id") private int id;
  private String character;
  private String name;
  @SerializedName("profile_path") private String image;

  public Cast(int id, String character, String name, String image) {
    this.id = id;
    this.character = character;
    this.name = name;
    this.image = image;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public String getCharacter() {
    return character;
  }

  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }
}
