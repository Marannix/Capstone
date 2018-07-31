package com.marannix.android.capstone.data;

import android.provider.BaseColumns;

public class FavouriteContract {
  public static final class FavouriteEntry implements BaseColumns {
    public static final String TABLE_NAME = "favourite";
    public static final String COLUMN_MOVIE_ID = "movieid";
    public static final String COLUMN_MOVIE_TITLE = "title";
    public static final String COLUMN_MOVIE_OVERVIEW = "overview";
    public static final String COLUMN_POSTER_PATH = "posterpath";
    public static final String COLUMN_BACKDROP_PATH = "backdropPath";
    public static final String COLUMN_MOVIE_VOTE_COUNT = "voteCount";
    public static final String COLUMN_MOVIE_VOTE_AVERAGE = "voteAverage";
    public static final String COLUMN_MOVIE_RELEASE_DATE = "releaseDate";
  }
}
