package com.marannix.android.capstone.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.adapter.HomeMoviePageAdapter;
import com.marannix.android.capstone.fragment.NowPlayingMovieFragment;
import com.marannix.android.capstone.fragment.UpcomingMovieFragment;
import com.marannix.android.capstone.repository.MovieRepository;

public class HomeActivity extends BaseActivity {

  //TODO: Create Utils
  // https://stackoverflow.com/questions/37071342/how-to-create-rtl-viewpager
  // Set up RTL for viewpager
  // https://www.youtube.com/watch?v=fGcMLu1GJEc  (NavigationDrawer)
  @BindView(R.id.tabs) TabLayout tabs;
  @BindView(R.id.viewPager) ViewPager viewPager;

  private UpcomingMovieFragment upcomingMovieFragment;
  private NowPlayingMovieFragment nowPlayingMovieFragment;
  private HomeMoviePageAdapter homeMoviePageAdapter;
  private MovieRepository movieRepository;
  private FirebaseAnalytics firebaseAnalytics;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle(getString(R.string.home_movies_title));
    firebaseAnalytics = FirebaseAnalytics.getInstance(this);
    ButterKnife.bind(this, getViewGroup());
    initMovieRepository();
    initView();

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

  private void initView() {
    homeMoviePageAdapter = new HomeMoviePageAdapter(getSupportFragmentManager());
    initPagerFragments();
    viewPager.setAdapter(homeMoviePageAdapter);
    tabs.setupWithViewPager(viewPager);
  }

  public void tracking(String title) {
    Bundle params = new Bundle();
    params.putString("movie_title", title);
    firebaseAnalytics.logEvent("opened_movie", params);
  }
}
