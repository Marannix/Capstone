package com.marannix.android.capstone.response;

import com.marannix.android.capstone.data.model.Trailers;
import java.util.List;

public class TrailerResponse {
  public List<Trailers> results;

  public List<Trailers> getTrailers() {
    return results;
  }
}
