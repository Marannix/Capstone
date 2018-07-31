package com.marannix.android.capstone.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.marannix.android.capstone.data.model.FavouriteContract;
import com.marannix.android.capstone.data.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class FavouriteDbHelper extends SQLiteOpenHelper {

  private static final String TAG = "FAVOURITE";
  private static final String DATABASE_NAME = "favourite.db";
  private static final int DATABASE_VERSION = 1;

  private SQLiteOpenHelper helper;
  private SQLiteDatabase database;

  public FavouriteDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public void open() {
    Log.i(TAG, "open: Database Opened");
    database = helper.getWritableDatabase();
  }

  public void close() {
    Log.i(TAG, "close: Database Closed");
    helper.close();
  }

  @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
    final String SQL_CREATE_FAVOURITE_TABLE = "CREATE TABLE "
        + FavouriteContract.FavouriteEntry.TABLE_NAME
        + " ("
        + FavouriteContract.FavouriteEntry._ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + FavouriteContract.FavouriteEntry.COLUMN_MOVIE_ID
        + " INTEGER, "
        + FavouriteContract.FavouriteEntry.COLUMN_MOVIE_TITLE
        + " TEXT NOT NULL, "
        + FavouriteContract.FavouriteEntry.COLUMN_MOVIE_OVERVIEW
        + " TEXT NOT NULL,"
        + FavouriteContract.FavouriteEntry.COLUMN_POSTER_PATH
        + " TEXT NOT NULL, "
        + FavouriteContract.FavouriteEntry.COLUMN_BACKDROP_PATH
        + " TEXT NOT NULL, "
        + FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_COUNT
        + " REAL NOT NULL, "
        + FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_AVERAGE
        + " REAL NOT NULL, "
        + FavouriteContract.FavouriteEntry.COLUMN_MOVIE_RELEASE_DATE
        + " TEXT NOT NULL"
        + "); ";
    sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITE_TABLE);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + FavouriteContract.FavouriteEntry.TABLE_NAME);
    onCreate(db);
  }

  public void addFavourite(Movie movie) {
    SQLiteDatabase database = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    values.put(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_ID, movie.getId());
    values.put(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_TITLE, movie.getTitle());
    values.put(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_OVERVIEW, movie.getOverview());
    values.put(FavouriteContract.FavouriteEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
    values.put(FavouriteContract.FavouriteEntry.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
    values.put(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_COUNT, movie.getVoteCount());
    values.put(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_AVERAGE, movie.getVoteAverage());
    values.put(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_RELEASE_DATE, movie.getReleaseDate());

    database.insert(FavouriteContract.FavouriteEntry.TABLE_NAME, null, values);
    database.close();
  }

  public void removeFavourite(int id) {
    SQLiteDatabase database = this.getWritableDatabase();
    database.delete(FavouriteContract.FavouriteEntry.TABLE_NAME,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_ID + "=" + id, null);
  }

  public List<Movie> getAllFavourite() {
    String[] columns = {
        FavouriteContract.FavouriteEntry._ID, FavouriteContract.FavouriteEntry.COLUMN_MOVIE_ID,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_TITLE,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_OVERVIEW,
        FavouriteContract.FavouriteEntry.COLUMN_POSTER_PATH,
        FavouriteContract.FavouriteEntry.COLUMN_BACKDROP_PATH,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_COUNT,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_AVERAGE,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_RELEASE_DATE,
    };

    String sortOrder = FavouriteContract.FavouriteEntry._ID + " ASC";
    List<Movie> favouriteList = new ArrayList<>();

    SQLiteDatabase database = this.getReadableDatabase();

    Cursor cursor =
        database.query(FavouriteContract.FavouriteEntry.TABLE_NAME, columns, null, null, null, null,
            sortOrder);

    if (cursor.moveToFirst()) {
      do {
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_ID))));
        movie.setTitle(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_TITLE)));
        movie.setOverview(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_OVERVIEW)));
        movie.setPosterPath(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_POSTER_PATH)));
        movie.setBackdropPath(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_BACKDROP_PATH)));
        movie.setVoteCount(Integer.parseInt(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_COUNT))));
        movie.setVoteAverage(Float.parseFloat(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_AVERAGE))));
        movie.setReleaseDate(cursor.getString(
            cursor.getColumnIndex(FavouriteContract.FavouriteEntry.COLUMN_MOVIE_RELEASE_DATE)));
        favouriteList.add(movie);
      } while (cursor.moveToNext());
    }

    cursor.close();
    database.close();

    Log.d(TAG, "getAllFavourite: " + favouriteList);
    return favouriteList;
  }

  public boolean isFavourite(String id) {
    SQLiteDatabase database = this.getReadableDatabase();

    String[] columns = {
        FavouriteContract.FavouriteEntry._ID, FavouriteContract.FavouriteEntry.COLUMN_MOVIE_ID,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_TITLE,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_OVERVIEW,
        FavouriteContract.FavouriteEntry.COLUMN_POSTER_PATH,
        FavouriteContract.FavouriteEntry.COLUMN_BACKDROP_PATH,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_COUNT,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_VOTE_AVERAGE,
        FavouriteContract.FavouriteEntry.COLUMN_MOVIE_RELEASE_DATE
    };

    String selection = FavouriteContract.FavouriteEntry.COLUMN_MOVIE_ID + " =?";
    String[] selectionArgs = new String[] { id };

    Cursor cursor = database.query(FavouriteContract.FavouriteEntry.TABLE_NAME, columns, selection,
        selectionArgs, null, null, null);

    if (cursor.getCount() <= 0) {
      cursor.close();
      database.close();
      return false;
    }

    cursor.close();
    database.close();
    return true;
  }
}
