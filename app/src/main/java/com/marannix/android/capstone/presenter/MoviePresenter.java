package com.marannix.android.capstone.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Movie;
import com.marannix.android.capstone.database.FavouriteDbHelper;
import com.marannix.android.capstone.repository.PosterRepository;
import com.marannix.android.capstone.repository.TrailerRepository;
import com.marannix.android.capstone.response.ImageResponse;
import com.marannix.android.capstone.response.TrailerResponse;
import com.marannix.android.capstone.view.MovieArtworkView;
import com.marannix.android.capstone.view.MovieDescriptionView;
import com.marannix.android.capstone.view.MovieEntireView;
import com.marannix.android.capstone.view.MovieHeaderView;
import com.marannix.android.capstone.view.MovieTrailerView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviePresenter {

  private Context context;
  private Movie movie;
  private TrailerRepository trailerRepository;
  private PosterRepository posterRepository;
  private MovieEntireView movieEntireView;
  private MovieHeaderView movieHeaderView;
  private MovieDescriptionView movieDescriptionView;
  private MovieArtworkView movieArtworkView;
  private MovieTrailerView movieTrailerView;
  private FavouriteDbHelper favouriteDbHelper;

  public MoviePresenter(Context context, Movie movie) {
    this.context = context;
    this.movie = movie;
  }

  public void present(ViewGroup viewGroup) {
    initRepository();
    initViews(viewGroup);
    retrieveTrailers();
    retrieveImages();
    setViews(movie);
    setFavouriteDbHelper();
    setFavouriteFabIcon();
    setFavouriteMovies();
  }

  private void initRepository() {
    trailerRepository = new TrailerRepository();
    posterRepository = new PosterRepository();
    trailerRepository.initApiModule();
    posterRepository.initApiModule();
  }

  private void initViews(ViewGroup viewGroup) {
    movieEntireView = new MovieEntireView(viewGroup);
    movieHeaderView = new MovieHeaderView(viewGroup);
    movieDescriptionView = new MovieDescriptionView(viewGroup);
    movieArtworkView = new MovieArtworkView(viewGroup);
    movieTrailerView = new MovieTrailerView(viewGroup);
  }

  private void setViews(Movie movie) {
    movieEntireView.loadBackdrop(movie);
    movieEntireView.setCollapsingToolbarLayout();
    movieHeaderView.setContent(movie);
    movieDescriptionView.setContent(movie.getOverview());
  }

  private void retrieveTrailers() {
    trailerRepository.fetchMovieTrailers(movie.getId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<TrailerResponse>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(TrailerResponse trailerResponse) {
            movieTrailerView.setVideos(trailerResponse.getTrailers(), context);
          }
        });
  }

  private void retrieveImages() {
    posterRepository.fetchMovieImages(movie.getId())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ImageResponse>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(ImageResponse imageResponse) {
            movieArtworkView.setArtwork(imageResponse.getPosters(), context);
          }
        });
  }

  private void setFavouriteDbHelper() {
    favouriteDbHelper = new FavouriteDbHelper(context);
  }

  private void setFavouriteMovies() {
    movieEntireView.getFab().setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (!favouriteDbHelper.isFavourite(String.valueOf((movie.getId())))) {
          addMovieToFavDatabase();
        } else {
          removeMovieFromDatabase();
        }
      }
    });
  }

  private void addMovieToFavDatabase() {
    SharedPreferences.Editor editor =
        context.getSharedPreferences(".MovieActivity", Context.MODE_PRIVATE).edit();
    editor.putBoolean("Favourite Added", true);
    editor.apply();
    favouriteDbHelper.addFavourite(movie);
    movieEntireView.getFab().setImageResource(R.drawable.ic_favorite_black_24dp);
    Toast.makeText(context, "Added to Favourite", Toast.LENGTH_SHORT).show();
  }

  private void removeMovieFromDatabase() {
    SharedPreferences.Editor editor =
        context.getSharedPreferences(".MovieActivity", Context.MODE_PRIVATE).edit();
    favouriteDbHelper = new FavouriteDbHelper(context);
    favouriteDbHelper.removeFavourite(movie.getId());
    movieEntireView.getFab().setImageResource(R.drawable.ic_favorite_border_black_24dp);
    editor.putBoolean("Favourite Removed", true);
    editor.apply();
    Toast.makeText(context, "Removed From Favourite", Toast.LENGTH_SHORT).show();
  }

  private void setFavouriteFabIcon() {
    if (favouriteDbHelper.isFavourite(String.valueOf(movie.getId()))) {
      movieEntireView.getFab().setImageResource(R.drawable.ic_favorite_black_24dp);
    } else {
      movieEntireView.getFab().setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }
  }
}
