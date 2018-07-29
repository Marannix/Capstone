package com.marannix.android.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marannix.android.capstone.R;
import com.marannix.android.capstone.data.model.Trailers;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

  private final static String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
  private final static String YOUTUBE_THUMBNAIL = "http://img.youtube.com/vi/";
  private Context context;
  private List<Trailers> trailers;
  private String trailerUrl;

  public void setTrailer(List<Trailers> trailers, Context context) {
    this.context = context;
    this.trailers = trailers;
    this.notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.movie_trailer_item, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull TrailerAdapter.ViewHolder holder, int position) {
    final Trailers trailer = trailers.get(position);
    holder.title.setText(trailer.getName());
    trailerUrl = YOUTUBE_URL + trailer.getKey();
    String img_url = YOUTUBE_THUMBNAIL + trailer.getKey() + "/sddefault.jpg";

    Picasso.get().load(img_url).into(holder.thumbnail);

    holder.trailerLayout.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
        context.startActivity(viewIntent);
      }
    });
  }

  @Override public int getItemCount() {
    return trailers == null ? 0 : trailers.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.movie_thumbnail) ImageView thumbnail;
    @BindView(R.id.movie_thumbnail_title) TextView title;
    @BindView(R.id.movie_trailer_layout) ConstraintLayout trailerLayout;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
