package com.marannix.android.capstone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Poster;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ViewHolder> {

  private static final String MOVIE_URL = "https://image.tmdb.org/t/p/";
  private static final String POSTER_SIZE = "w500";
  private Context context;
  private List<Poster> images;
  private String title;

  public void setArtworks(List<Poster> images, Context context, String title) {
    this.images = images;
    this.context = context;
    this.title = title;
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ArtworkAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.movie_artwork_item, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Poster poster = images.get(position);
    final String path = MOVIE_URL + POSTER_SIZE + poster.getPosterPath();
    Picasso.get().load(path).into(holder.posterImage);
    holder.posterImage.setContentDescription(String.format(title, R.string.artwork_content_description));
  }

  @Override public int getItemCount() {
    return images != null ? images.size() : 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.movie_artwork_poster) ImageView posterImage;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
