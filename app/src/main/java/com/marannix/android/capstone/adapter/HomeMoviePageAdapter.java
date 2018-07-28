package com.marannix.android.capstone.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeMoviePageAdapter extends FragmentPagerAdapter {
  private final List<Fragment> fragmentList = new ArrayList<>();
  private final List<String> fragmentTitleList = new ArrayList<>();

  public HomeMoviePageAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  public void addFragment(Fragment fragment, String title) {
    fragmentList.add(fragment);
    fragmentTitleList.add(title);
  }

  @Nullable @Override public CharSequence getPageTitle(int position) {
    return fragmentTitleList.get(position);
  }

  @Override public Fragment getItem(int position) {
    return fragmentList.get(position);
  }

  @Override public int getCount() {
    return fragmentList.size();
  }
}
