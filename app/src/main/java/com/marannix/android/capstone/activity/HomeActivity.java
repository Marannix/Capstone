package com.marannix.android.capstone.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.adapter.HomeMoviePageAdapter;
import com.marannix.android.capstone.data.model.Movie;
import com.marannix.android.capstone.data.model.NowPlayingMovies;
import com.marannix.android.capstone.fragment.NowPlayingMovieFragment;
import com.marannix.android.capstone.fragment.UpcomingMovieFragment;
import com.marannix.android.capstone.repository.MovieRepository;
import com.marannix.android.capstone.response.MovieResponse;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends BaseActivity {

  //TODO: Create Utils
  // https://stackoverflow.com/questions/37071342/how-to-create-rtl-viewpager
  // Set up RTL for viewpager

  @BindView(R.id.tabs) TabLayout tabs;
  @BindView(R.id.viewPager) ViewPager viewPager;

  private UpcomingMovieFragment upcomingMovieFragment;
  private NowPlayingMovieFragment nowPlayingMovieFragment;

  private List<Movie> upcomingMoviesList;
  private List<NowPlayingMovies> nowPlayingMoviesList;
  private HomeMoviePageAdapter homeMoviePageAdapter;
  private MovieRepository movieRepository;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this, getViewGroup());
    initMovieRepository();
    initView();
    //retrieveUpcomingMovies();
    //retrieveNowPlayingMovies();
  }

  private void initMovieRepository() {
    movieRepository = new MovieRepository();
    movieRepository.initApiModule();
  }

  private void initPagerFragments() {
    initUpcomingMovieFragment();
    initNowPlayingMovieFragment();
  }

  private void initUpcomingMovieFragment() {
    upcomingMovieFragment =
        UpcomingMovieFragment.newUpcomingMovieInstance();
    homeMoviePageAdapter.addFragment(upcomingMovieFragment, "Latest");
  }

  private void initNowPlayingMovieFragment() {
    nowPlayingMovieFragment =
        NowPlayingMovieFragment.nowPlayingMovieInstance();
    homeMoviePageAdapter.addFragment(nowPlayingMovieFragment, "Now Playing");
  }

  //private void retrieveUpcomingMovies() {
  //  movieRepository.fetchUpcomingMovies()
  //      .subscribeOn(Schedulers.io())
  //      .observeOn(AndroidSchedulers.mainThread())
  //      .subscribe(new Subscriber<MovieResponse>() {
  //        @Override public void onCompleted() {
  //          initView();
  //        }
  //
  //        @Override public void onError(Throwable e) {
  //          //TODO Show error message
  //        }
  //
  //        @Override public void onNext(MovieResponse upcomingResponse) {
  //          upcomingMoviesList = upcomingResponse.getMovies();
  //        }
  //      });
  //}

  //private void retrieveNowPlayingMovies() {
  //  movieRepository.fetchNowPlayingMovies()
  //      .subscribeOn(Schedulers.io())
  //      .observeOn(AndroidSchedulers.mainThread())
  //      .subscribe(new Subscriber<NowPlayingResponse>() {
  //        @Override public void onCompleted() {
  //
  //        }
  //
  //        @Override public void onError(Throwable e) {
  //
  //        }
  //
  //        @Override public void onNext(NowPlayingResponse nowPlayingResponse) {
  //          nowPlayingMoviesList = nowPlayingResponse.getResults();
  //        }
  //      });
  //}

  private void initView() {
    homeMoviePageAdapter = new HomeMoviePageAdapter(getSupportFragmentManager());
    initPagerFragments();
    viewPager.setAdapter(homeMoviePageAdapter);
    tabs.setupWithViewPager(viewPager);
  }
}