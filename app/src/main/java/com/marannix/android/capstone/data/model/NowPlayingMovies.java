package com.marannix.android.capstone.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class NowPlayingMovies implements Parcelable{

  private int id;
  private String title;
  private String overview;
  @SerializedName("poster_path") private String posterPath;
  @SerializedName("backdrop_path") private String backdropPath;
  @SerializedName("vote_count") private int voteCount;
  @SerializedName("vote_average") private float voteAverage;
  @SerializedName("release_date") private String releaseDate;

  public NowPlayingMovies(int id, String title, String overview, String posterPath, String backdropPath,
      int voteCount, float voteAverage, String releaseDate) {
    this.id = id;
    this.title = title;
    this.overview = overview;
    this.posterPath = posterPath;
    this.backdropPath = backdropPath;
    this.voteCount = voteCount;
    this.voteAverage = voteAverage;
    this.releaseDate = releaseDate;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public void setVoteAverage(float voteAverage) {
    this.voteAverage = voteAverage;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getOverview() {
    return overview;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public float getVoteAverage() {
    return voteAverage;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public static Creator<NowPlayingMovies> getCREATOR() {
    return CREATOR;
  }

  protected NowPlayingMovies(Parcel in) {
    id = in.readInt();
    title = in.readString();
    overview = in.readString();
    posterPath = in.readString();
    backdropPath = in.readString();
    voteCount = in.readInt();
    voteAverage = in.readFloat();
    releaseDate = in.readString();
  }

  public static final Creator<NowPlayingMovies> CREATOR = new Creator<NowPlayingMovies>() {
    @Override public NowPlayingMovies createFromParcel(Parcel in) {
      return new NowPlayingMovies(in);
    }

    @Override public NowPlayingMovies[] newArray(int size) {
      return new NowPlayingMovies[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(title);
    dest.writeString(overview);
    dest.writeString(posterPath);
    dest.writeString(backdropPath);
    dest.writeInt(voteCount);
    dest.writeFloat(voteAverage);
    dest.writeString(releaseDate);
  }
}
