package com.marannix.android.capstone.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.adapter.UpcomingMovieAdapter;
import com.marannix.android.capstone.data.model.UpcomingMovies;
import java.util.ArrayList;
import java.util.List;

//TODO Saturday, call fragment and pass it the list of upcoming movies
public class UpcomingMovieFragment extends Fragment {

  @BindView(R.id.upcomingMovieRecyclerView) RecyclerView recyclerView;

  private static final String UPCOMING_LIST = "upcoming";
  private List<UpcomingMovies> upcomingMovies;

  public UpcomingMovieFragment() {
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null && getArguments().containsKey(UPCOMING_LIST)) {
      upcomingMovies = getArguments().getParcelableArrayList(UPCOMING_LIST);
    }
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_upcoming_movies_page, container, false);
    ButterKnife.bind(this, rootView);
    initUpcomingMovieAdapter();
    return rootView;
  }

  public static UpcomingMovieFragment newIngredientsInstance(ArrayList<UpcomingMovies> ingredients) {
    UpcomingMovieFragment upcomingMovieFragment = new UpcomingMovieFragment();
    Bundle args = new Bundle();
    args.putParcelableArrayList(UPCOMING_LIST, ingredients);
    upcomingMovieFragment.setArguments(args);
    return upcomingMovieFragment;
  }

  private void initUpcomingMovieAdapter() {
    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);

    UpcomingMovieAdapter upcomingMovieAdapter =
        new UpcomingMovieAdapter(getContext(), upcomingMovies);
    recyclerView.setAdapter(upcomingMovieAdapter);
  }
}
