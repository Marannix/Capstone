package com.marannix.android.capstone.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.adapter.HomeMoviePageAdapter;
import com.marannix.android.capstone.data.model.UpcomingMovies;
import com.marannix.android.capstone.fragment.UpcomingMovieFragment;
import com.marannix.android.capstone.repository.MovieRepository;
import com.marannix.android.capstone.response.UpcomingResponse;
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
  private List<UpcomingMovies> upcomingMoviesList;
  private HomeMoviePageAdapter homeMoviePageAdapter;
  private MovieRepository movieRepository;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this, getViewGroup());
    initMovieRepository();
    retrieveUpcomingMovies();
  }

  private void initMovieRepository() {
    movieRepository = new MovieRepository();
    movieRepository.initApiModule();
  }

  private void initPagerFragments() {
    initUpcomingMovieFragment();
  }

  private void initUpcomingMovieFragment() {
    upcomingMovieFragment = UpcomingMovieFragment.newIngredientsInstance(
        (ArrayList<UpcomingMovies>) upcomingMoviesList);
    homeMoviePageAdapter.addFragment(upcomingMovieFragment, "Upcoming Movies");
  }

  private void retrieveUpcomingMovies() {
    movieRepository.fetchUpcomingMovies()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<UpcomingResponse>() {
          @Override public void onCompleted() {
           initView();
          }

          @Override public void onError(Throwable e) {
            //TODO Show error message
          }

          @Override public void onNext(UpcomingResponse upcomingResponse) {
            upcomingMoviesList = upcomingResponse.getUpcomingMovies();
            for (int i = 0; i < upcomingMoviesList.size(); i++) {
              Log.d("Joshua1", "onNext: " + upcomingMoviesList.get(i).getTitle());
            }
          }
        });
  }

  private void initView() {
    homeMoviePageAdapter = new HomeMoviePageAdapter(getSupportFragmentManager());
    initPagerFragments();
    viewPager.setAdapter(homeMoviePageAdapter);
    tabs.setupWithViewPager(viewPager);
  }
}
