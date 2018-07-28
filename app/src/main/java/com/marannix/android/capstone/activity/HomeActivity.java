package com.marannix.android.capstone.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.adapter.HomeMoviePageAdapter;
import com.marannix.android.capstone.data.model.UpcomingMovies;
import com.marannix.android.capstone.fragment.UpcomingMovieFragment;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

  @BindView(R.id.tabs) TabLayout tabs;
  @BindView(R.id.viewPager) ViewPager viewPager;

  private UpcomingMovieFragment upcomingMovieFragment;
  private List<UpcomingMovies> upcomingMoviesList;
  private HomeMoviePageAdapter homeMoviePageAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this, getViewGroup());

    homeMoviePageAdapter = new HomeMoviePageAdapter(getSupportFragmentManager());
    initPagerFragments();
    viewPager.setAdapter(homeMoviePageAdapter);
    tabs.setupWithViewPager(viewPager);
  }

  private void initPagerFragments() {
    initUpcomingMovieFragment();
  }

  private void initUpcomingMovieFragment() {
    upcomingMovieFragment = UpcomingMovieFragment.newIngredientsInstance(
        (ArrayList<UpcomingMovies>) upcomingMoviesList);
    homeMoviePageAdapter.addFragment(upcomingMovieFragment, "Upcoming Movies");
  }
}
