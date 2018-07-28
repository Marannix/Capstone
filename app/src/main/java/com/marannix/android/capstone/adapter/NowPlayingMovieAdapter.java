package com.marannix.android.capstone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.NowPlayingMovies;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NowPlayingMovieAdapter
    extends RecyclerView.Adapter<NowPlayingMovieAdapter.ViewHolder> {

  private Context context;
  private List<NowPlayingMovies> nowPlayingMovies;
  private String movieUrl = "https://image.tmdb.org/t/p/";
  private String phoneSize = "w500";

  public void setListData(Context context, List<NowPlayingMovies> nowPlayingMovies) {
    this.context = context;
    this.nowPlayingMovies = nowPlayingMovies;
    this.notifyDataSetChanged();
  }

  @NonNull @Override
  public NowPlayingMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    return new NowPlayingMovieAdapter.ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.now_playing_movies_items, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull NowPlayingMovieAdapter.ViewHolder holder, int position) {
    final NowPlayingMovies nowPlayingMovie = nowPlayingMovies.get(position);
    final String path = movieUrl + phoneSize + nowPlayingMovie.getPosterPath();

    Picasso.get().load(path).into(holder.image);
    holder.title.setText(nowPlayingMovie.getTitle());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        // TODO: Take to movie activity
        Log.d("Joshua1", "onClick: I've been clicked NowPlaying");
      }
    });
  }

  @Override public int getItemCount() {
    return nowPlayingMovies != null ? nowPlayingMovies.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView3) ImageView image;
    @BindView(R.id.textView1) TextView title;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
